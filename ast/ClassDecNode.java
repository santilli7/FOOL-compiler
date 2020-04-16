package ast;

import symboltable.SymbolTable;
import throwable.MultipleIDException;
import throwable.TypeException;
import type.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class ClassDecNode implements Node {

    //ArrayList contenente tutte le dichiarazioni di classe
    private List<ClassNode> classDecs;
    private Node body;

    public ClassDecNode(List<ClassNode> classDec, Node b) {
        this.classDecs = classDec;
        body = b;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();
        env.newScope();
        for (ClassNode classNode : classDecs) {
            try {
                //defisco la classe con tutti i suoi campi e metodi
                List<Field> fieldList = new ArrayList<>();
                List<Method> methodList = new ArrayList<>();
                for(MethodNode m : classNode.getMethodDeclarationArrayList()){
                    methodList.add(new Method(m.getID(),new FunType(new ArrayList<>(), m.getReturnType())));
                }

                //controllo se la classe è già stata definita
                ClassType classType = new ClassType(classNode.getClassID(), new ClassType(classNode.getSuperClassID()), fieldList, methodList);
                env.insertDeclaration(classNode.getClassID(), classType, 0);
            } catch (MultipleIDException e) {
                res.add("La classe '" + classNode.getClassID() + "' è stata già dichiarata!\n");
            }
        }

        //checkSemantic su ogni classe dichiarata
        for (ClassNode classNode : classDecs) {
            res.addAll(classNode.checkSemantics(env));
        }


        for (ClassNode classNode : classDecs){
            res.addAll(classNode.chechMethods(env));
        }
        //checksemantic sul letInExpNode || letInStmsNode
        res.addAll(body.checkSemantics(env));

        //esco dal livello di scope
        env.exitScope();

        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {
        for (ClassNode classNode : classDecs) {
            classNode.typeCheck();
        }
        return body.typeCheck();
    }

    @Override
    public String codeGeneration() {
        ArrayList<ClassNode> classNodeArrayList = new ArrayList<>();
        HashMap<String, ClassNode> classHashMap = new HashMap<>();
        String nameDeclaration = "";

        //in modo da inserire tutte le superclassi prima delle sotto classi
        ListIterator iterator = classDecs.listIterator();
        while (iterator.hasNext()) {
            ClassNode classDec = (ClassNode) iterator.next();
            //se la classe che sto esaminando non ha una superclasse allora:
            if (classDec.getSuperClassID() == null || classDec.getSuperClassID().isEmpty()) {
                //aggiungo alla lista di classi la dichiarazione della classe
                classNodeArrayList.add(classDec);
                //aggiungo alla hashmap l'identificatore della classe e la dichiarazione della classe
                //serve nel ciclo successivo per ottenere la superclasse
                classHashMap.put(classDec.getClassID(), classDec);
                //per scorrere
                iterator.remove();
            }
        }
        //se entro dentro a questo while, vuole dire che è presente almeno una classe che ne estende un'altra
        //in quanto tutte le superclassi sono state rimosse durente il while precedente
        while (classDecs.size() != 0) {
            iterator = classDecs.listIterator();
            while (iterator.hasNext()) {
                //contiene la classe sottoclasse
                ClassNode subClass = (ClassNode) iterator.next();
                //contiene l'identificatore della superclasse
                String superClassName = subClass.getSuperClassID();
                //contiene la superclasse
                ClassNode superClass = classHashMap.get(superClassName);
                //nel caso si estenda una classe ancora non dichiarata si entra in questo if
                if (superClass != null) {
                    //aggiungo alle classi dichiarati la sottoclasse
                    classNodeArrayList.add(subClass);
                    //aggiungo alla hashmap l'identificatore della sottoclasse e la sua dichiarazione
                    classHashMap.put(subClass.getClassID(), subClass);
                    iterator.remove();
                }

            }
        }

        //per ogni classe dichiarata, eseguo la code generation
        for (ClassNode cl : classNodeArrayList) {
            nameDeclaration += cl.codeGeneration();
        }


        return nameDeclaration + body.codeGeneration();
    }
}