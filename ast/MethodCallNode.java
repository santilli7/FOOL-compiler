package ast;

import parser.FOOLParser;
import symboltable.Entry;
import symboltable.SymbolTable;
import throwable.TypeException;
import throwable.UndecIDException;
import type.ClassType;
import type.FunType;
import type.ObjectType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class MethodCallNode extends FunCallNode {

    private String ID; //id della var su cui viene invocato il metodo
    private String methodID; //id del metodo invocato
    private Type methodType; //tipo del metodo
    private int objectOffset; //offset dell'istanza
    private int objectNestingLevel; //nestinlevel dell'istanza
    private int methodOffset; //offset del metodo
    private int nestinglevelCall; //level della chiamata

    public MethodCallNode(String id, String method, List<Node> arguments, FOOLParser.MetExpContext ctx) {
        super(method, arguments, null);
        ID = id;
        methodID = method;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();
        nestinglevelCall = env.getNestingLevel(); //level della chiamata del metodo
        try {
            ClassType classType = null;
            //ricavo informazioni del metodo per accedere alla DT
            if (ID.equals("this")) {
                Type objectType = env.getLastEntryIstance().getType();
                // se si sta utilizzando this ci si riferisce per forza alla classe corrente
                // perciò il valore di $fp è sempre 0
                objectOffset = 0;
                if (objectType instanceof ClassType) {
                    classType = (ClassType) objectType;
                    // l'oggetto si trova ad offset per 3, ovvero dopo l'indirizzo della dispatch
                    // table, il numero di parametri e i parametri
                    objectNestingLevel = 3;
                }
            } else {
                Entry objectEntry = env.checkID(ID);

                if (!objectEntry.isInitialized()) {

                    System.out.println("L'oggetto non è stato ancora inizializzato!\n");
                    System.exit(-1);
                }
                Type objectType = objectEntry.getType();
                objectOffset = objectEntry.getOffset();
                objectNestingLevel = objectEntry.getNestingLevel();
                try {
                    env.checkID("this");
                    // controllo se c'è this, se c'è sono in un metodo e decremento il nesting level
                    nestinglevelCall--;
                } catch (UndecIDException e) { }
                // check che il metodo sia stato invocato da un oggetto
                if (objectType instanceof ObjectType) {
                    classType = ((ObjectType) objectType).getClassType();
                } else {
                    res.add("Il metodo " + methodID + " è invocato da un tipo che non è un oggetto\n");
                    return res;
                }
            }

            // se il metodo è invocato con this allora significa che la classe a cui il metodo
            // appartiene è nell'ultima entry inserita nella SymbolTable

            Entry classEntry;
            if (ID.equals("this")) {
                classEntry = env.getLastEntryIstance();
            } else {
                classEntry = env.checkID(classType.getClassID());
            }

            ClassType objectClass = (ClassType) classEntry.getType();
            methodOffset = objectClass.getOffsetOfMethod(methodID);
//            System.err.println(classType.getClassID() + " _ " +methodID + " con offset " + methodOffset);

            methodType = objectClass.getTypeOfMethod(methodID);

            // controllo che il metodo sia dichiarato all'interno della classe
            if (methodType == null) {
                res.add("L'oggetto " + ID + " non ha il metodo " + methodID + "\n");
            }

            //CheckSemantic per ogni argomento
            for (Node node : args)
                res.addAll(node.checkSemantics(env));


        } catch (UndecIDException e) {
            res.add(e.getMessage());
        }
        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {
        FunType retType = (FunType) methodType;
        List<Type> retTypeParams = retType.getParameters();

        //controllo che il metodo abbia il numero di parametri uguale a quello degli argomenti
        if (!(retTypeParams.size() == args.size())) {
            throw new TypeException("Numero errato di argomenti nell'invocazione di '" + methodID + "'");
        }
        for (int i = 0; i < args.size(); i++) {
            Type t = args.get(i).typeCheck();
            if (args.get(i).typeCheck() instanceof ObjectType) {
                IdNode id = (IdNode) args.get(i);
                if (!id.getEntry().isInitialized())
                    throw new TypeException("Il parametro non è stato inizializzato!");
            }

            if (!args.get(i).typeCheck().isSubType(retTypeParams.get(i)))
                throw new TypeException("Tipo errato per il parametro " + (i + 1) + " nell'invocazione di '" + methodID+"'");
        }
            return retType.getReturnType();
    }

    @Override
    public String codeGeneration() {
        StringBuilder parameterCode = new StringBuilder();
        for (int i = args.size() - 1; i >= 0; i--)
            parameterCode.append(args.get(i).codeGeneration());

        StringBuilder getActivationRecord = new StringBuilder();

        for (int i = 0; i < nestinglevelCall - objectNestingLevel; i++)
            getActivationRecord.append("lw\n");

        return
                "lfp\n"                                  // pusho frame pointer e parametri
                        + parameterCode
                        + "push " + objectOffset + "\n"         // pusho l'offset logico dell'oggetto (dispatch table)
                        + "lfp\n"
                        + getActivationRecord                                 //pusho access link (lw consecutivamente)
                        // così si potrà risalire la catena statica
                        + "add\n"                               // $fp + offset
                        + "lw\n"                                // pusho indirizzo di memoria in cui si trova
                        // l'indirizzo della dispatch table
                        + "copy\n"                              // copio
                        + "lw\n"                                // pusho l'indirizzo della dispatch table
                        + "push " + (methodOffset - 1) + "\n"   // pusho l'offset di dove si trova metodo rispetto
                        // all'inizio della dispatch table
                        + "add" + "\n"                          // dispatch_table_start + offset
                        + "loadc\n"                             // pusho il codice del metodo
                        + "js\n";                               // jump all'istruzione dove e' definito il metodo e
        // salvo $ra
    }
}