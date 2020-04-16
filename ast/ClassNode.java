package ast;

import codegen.DTentry;
import codegen.DispatchTable;
import symboltable.Entry;
import symboltable.SymbolTable;
import throwable.MultipleIDException;
import throwable.TypeException;
import throwable.UndecIDException;
import type.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassNode implements Node {

    private String classID;
    private String superClassID;

    private HashMap<String, Type> fieldHashMap = new HashMap<>();
    private HashMap<String, FunType> methodHashMap = new HashMap<>();

    private List<ParameterNode> fieldList;
    private List<MethodNode> methodList;

    private ClassType classType;
    private ClassType superClassType;

    public ClassNode(String ID, String superID, List<ParameterNode> fieldDec, List<MethodNode> metDec) {
        classID = ID;
        superClassID = superID;
        fieldList = fieldDec;
        methodList = metDec;
    }

    public String getClassID() {
        return classID;
    }

    public String getSuperClassID() {
        return superClassID;
    }

    public List<ParameterNode> getFieldDeclarationArrayList() {
        return fieldList;
    }

    public List<MethodNode> getMethodDeclarationArrayList() {
        return methodList;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();

        //vengono prima controllati i campi ed i metodi della classe senza considerare l'extends
        List<Field> fieldArrayList = new ArrayList<>();
        List<Method> methodArrayList = new ArrayList<>();

        //CAMPI DELLA CLASSE CORRENTE
        for (ParameterNode parameterNode : fieldList) {
            fieldArrayList.add(new Field(parameterNode.getID(), parameterNode.getType()));
            fieldHashMap.put(parameterNode.getID(), parameterNode.getType());
        }
        //METODI DELLA CLASSE CORRENTE
        for (MethodNode methodNode : methodList) {
            List<Type> parameterTypeArrayList = new ArrayList<>();
            //PARAMETRI DEL METODO CORRENTE
            for (ParameterNode parameterNode : methodNode.getParameterNodeArrayList()) {
                // controlla i parametri di ogni metodo
                // se sono degli oggetti si controlla che siano definiti, altrimenti eccezione
                if (parameterNode.getType() instanceof ObjectType) {
                    ObjectType paramType = (ObjectType) parameterNode.getType();
                    String declaredClass = paramType.getClassType().getClassID();
                    try {
                        ClassType paramClassType = (ClassType) env.checkID(declaredClass).getType();
                        parameterTypeArrayList.add(new ObjectType(paramClassType));
                    } catch (UndecIDException e) {
                        res.add("La classe '" + declaredClass + " non è stata dichiarata!\n");
                    }
                } else { //se i parametri sono "base", non oggetti
                    parameterTypeArrayList.add(parameterNode.getType());
                }
            }

            //prende i campi passati in input e li inserisce in methodArrayList per la STentry e in
            //methodHashMap per accedervi velocemente
            methodArrayList.add(new Method(methodNode.getID(), new FunType(parameterTypeArrayList, methodNode.getReturnType())));
            methodHashMap.put(methodNode.getID(), new FunType(parameterTypeArrayList, methodNode.getReturnType()));
        }

        superClassType = null;

        //lista dei metodi ereditati dalla superClasse
        List<String> metsEred = new ArrayList<>();
        if (!superClassID.isEmpty()) {
            try {
                superClassType = (ClassType) env.checkID(superClassID).getType();
                //insert dei metodi non ridefiniti e da ereditare:
                for (String mEred : superClassType.getMethodsMap().keySet()) {
                    if (!methodHashMap.containsKey(mEred)) metsEred.add(mEred);
                }

            } catch (UndecIDException e) {
                res.add("La superclasse '" + superClassID + "' non è stata dichiarata!\n");
                return res;
            }
        }

        //ST viene aggiornata
        try {
            classType = new ClassType(classID, superClassType, fieldArrayList, methodArrayList);
            env.setDeclarationType(classID, classType, 0);
        } catch (UndecIDException e) {
            res.add(e.getMessage());
        }


        env.newScope(); //scope per il metodo


        for (String m : metsEred) {
            try {
                env.insertDeclaration(m, superClassType.getMethodsMap().get(m), superClassType.getOffsetOfMethod(m));
            } catch (MultipleIDException | UndecIDException e) {
                e.printStackTrace();
            }
        }
        for (MethodNode methodNode : methodList) {
            try {
                List<Type> tList = new ArrayList<>();
                for (ParameterNode t : methodNode.getParameterNodeArrayList()) {
                    tList.add(t.getType());
                }

                env.insertDeclaration(
                        methodNode.ID,
                        new FunType(tList, methodNode.getReturnType()),
                        Math.abs(env.getOffset()) + 1);
                env.decreaseOffset();
            } catch (MultipleIDException e) {
                res.add("Metodo '" + methodNode.ID + "' già dichiarato!\n");
                return res;
            }
        }

        env.exitScope();


        //controllo tra superclasse e sottoclasse (se estende)
        if (!superClassID.isEmpty()) {
            checkSuperClassValidation(env, res);
        }


        printClassEntry(env, classID);
        return res;
    }

    public List<String> chechMethods(SymbolTable env) {
        List<String> res = new ArrayList<>();
        //Dento lo scope della classe
        env.newScope();

        for (ParameterNode parameterNode : fieldList) {
            //non si possono passare sottotipo della classe stessa nel costruttore
            if (parameterNode.getType() instanceof ObjectType) {

                //controllo che il tipo del parametro sia definito
                String paramClass = "";
                try {
                    paramClass = ((ObjectType) parameterNode.getType()).getClassType().getClassID();
                    env.checkID(paramClass);
                } catch (UndecIDException e) {
                    res.add("Classe '" + paramClass + "' non definita!\n");
                }

                //sottoclassi non possono essere parametri di superclassi
                ClassType subClassAsParam = ((ObjectType) parameterNode.getType()).getClassType();
                if (subClassAsParam.isSubType(this.classType))
                    res.add("Non si può usare una sottoclasse nel costruttore della superclasse!\n");
            }
            res.addAll(parameterNode.checkSemantics(env));
        }

        // checkSemantic di ogni metodo
        for (MethodNode methodNode : methodList) {
            res.addAll(methodNode.checkSemantics(env));
        }
        env.exitScope(); //exit scope per la classe corrente

        return res;
    }

    private void printClassEntry(SymbolTable env, String classID) {
        try {
            //DEBUG
            ClassType t = (ClassType) env.checkID(classID).getType();
            ClassType st = t.getSuperClassType();
            String supClass = "";
            if (st != null) {
                supClass = st.getClassID();
            }

            StringBuilder sFun = new StringBuilder();
            for (String method : t.getMethodsMap().keySet()) {
                FunType current = t.getMethodsMap().get(method);
                StringBuilder sParams = new StringBuilder();
                sParams.append(" ");
                for (Type currentParam : current.getParameters()) {
                    sParams.append(currentParam.getID() + " ");
                }
                sFun.append("[" + method + "(o:" + t.getOffsetOfMethod(method) + "): (" + sParams.toString().toLowerCase() + ")->" + current.getReturnType().toPrint() + "] ");
            }
            System.out.println("\t\t\t" +
                    t.getClassID() + " {" + supClass + "} [ " +
                    t.getFields().size() + " | " + sFun
                    + "]"
            );
        } catch (UndecIDException e) {
            e.printStackTrace();
        }


    }


    private List<String> checkSuperClassValidation(SymbolTable env, List<String> res) {
        try {
            //controllo che la classe che estende sia una classe
            if (!(env.getTypeOf(superClassID) instanceof ClassType))
                res.add("La classe '" + superClassID + "' non è stata definita!\n");
        } catch (UndecIDException e) {
            res.add("La classe'" + superClassID + "' non è stata dichiarata!\n");
        }

        //superClassType è l'oggetto che contiene tutte le informazioni della superclasse
        // controllo che il numero di attributi del costruttore sia uguale a quello della superclasse
        if (fieldList.size() >= superClassType.getFields().size()) {
            for (int i = 0; i < superClassType.getFields().size(); i++) {
                ParameterNode currentParameterNode = fieldList.get(i);
                Field superClassField = superClassType.getFields().get(i);
                //tipo e id dei campi devono essere uguali (no override di campi)
                if (!superClassField.getID().equals(currentParameterNode.getID()) ||
                        !superClassField.getType().toPrint().equals(currentParameterNode.getType().toPrint())
                        ) {
                    res.add("Non è permesso l'override del campo '" + currentParameterNode.getID() + "' definito nella superclasse!\n");
                }
            }
        } else {
            res.add("La sottoclasse non ha lo stesso numero di parametri della superclasse\n");
        }

        try {
            //controllo ovveride se possibile
            //prendo entry e tipo della superclasse
            Entry superClassEntry = env.checkID(superClassID);
            ClassType superClassType = (ClassType) superClassEntry.getType();

            //se si trovano due metodi con lo stesso nome controllo che uno sia sottotipo dell'altro
            //altrimenti override non è compatibile
            HashMap<String, FunType> superClassMethodsHashMap = superClassType.getMethodsMap();
            for (String method : methodHashMap.keySet()) {
                if (superClassMethodsHashMap.containsKey(method)) {
                    if (!methodHashMap.get(method).isSubType(superClassMethodsHashMap.get(method))) {
                        res.add("L'override del '" + method + "' nella classe '" + classID + "' non è permesso!\n");
                    }
                }

                //per ogni metodo della superclasse non ridefinito:
            }

        } catch (UndecIDException e) {
            res.add("La superclasse '" + superClassID + "' non è definita!\n");
        }
        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {

        //typecheck di ogni parametro e di ogni metodo
        for (ParameterNode parameterNode : fieldList) {
            parameterNode.typeCheck();
        }
        for (MethodNode methodNode : methodList) {
            methodNode.typeCheck();
        }

        return classType;
    }

    @Override
    public String codeGeneration() {
        List<DTentry> dispatchTable;
        // Creo una nuova dispatch table da zero se la classe non ha superclasse
        if (superClassID.equals("")) {
            dispatchTable = new ArrayList<>();
        }
        // Altrimenti la copio come base
        else {
            dispatchTable = DispatchTable.getDispatchTable(superClassID);
        }

        //contiene i metodi della superclasse
        HashMap<String, String> superClassMethodsHashMap = new HashMap<>();
        //aggiungo i metodi della superclasse alla hashmap
        for (DTentry d : dispatchTable) {
            superClassMethodsHashMap.put(d.getMethodID(), d.getMethodLabel());
        }
        //contiene i metodi della classe attuale
        HashMap<String, String> currentClassMethodsHashMap = new HashMap<>();
        //aggiungo i metodi della classe attuale
        for (MethodNode m : methodList) {
            currentClassMethodsHashMap.put(m.getID(), m.codeGeneration());
        }
        //per ogni elemento della dispatch table:
        for (int i = 0; i < dispatchTable.size(); i++) {
            //gestione ovverride
            //prende il metodo dalla dispatch table, se presente
            String oldMethodID = dispatchTable.get(i).getMethodID();
            //lo sostituisce con il metodo proprio della classe
            String newMethodCode = currentClassMethodsHashMap.get(oldMethodID);
            //se l'ID esiste, vuol dire che è stato fatto override e la dispatch table viene aggiornata
            if (newMethodCode != null) {
                dispatchTable.set(i, new DTentry(oldMethodID, newMethodCode));
            }
        }
        //per ogni metodo:
        for (MethodNode m : methodList) {
            //gestisce le funzioni aggiuntive della sottoclasse rispetto alla superclasse
            //contiene l'ID del metodo corrente
            String currentMethodID = m.getID();
            //se la superclasse non ha il metodo che si sta esaminando, lo si aggiunge alla dispatch table.
            if (superClassMethodsHashMap.get(currentMethodID) == null) {
                dispatchTable.add(new DTentry(currentMethodID, currentClassMethodsHashMap.get(currentMethodID)));
            }
        }

        //viene aggiunta la dispatch table corrispondente alla classe esaminata
        //questa operazione viene eseguita anche se la dispatch table è vuota
        //in quanto può capitare di implementare una classe senza metodi
        DispatchTable.addDispatchTable(classID, dispatchTable);

        return "";


    }


}