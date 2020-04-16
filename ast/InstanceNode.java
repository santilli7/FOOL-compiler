package ast;

import parser.FOOLParser;
import symboltable.SymbolTable;
import throwable.TypeException;
import throwable.UndecIDException;
import type.ClassType;
import type.Field;
import type.ObjectType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class InstanceNode implements Node {

    private String classID;
    private ClassType classType;
    private List<Node> argsList;
    private FOOLParser.NewExpContext ctx;

    public InstanceNode(String classID, List<Node> argsList, FOOLParser.NewExpContext ctx) {
        this.classID = classID;
        this.argsList = argsList;
        this.ctx = ctx;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();
        try {
            classType = (ClassType) env.checkID(classID).getType();
        } catch (UndecIDException e) {
            res.add("La classe '" + classID + "' non è dichiarata!\n");
            return res;
        }

        if (argsList.size() != classType.getFields().size()) {
            res.add("Numero di parametri errato nel costruttore della classe '" + classID + "'\n");
        }
        if (argsList.size() > 0) {
            for (Node node : argsList) {
                res.addAll(node.checkSemantics(env));
            }
        }

        return res;

    }

    @Override
    public Type typeCheck() throws TypeException {
        List<Field> fieldClass = classType.getFields();

        for (int i = 0; i < argsList.size(); i++) {
            //controllo sul costruttore che i tipi dei parametri siano gli stessi dei tipi dei campi
            Type argumentType = argsList.get(i).typeCheck();
            Type fieldType = fieldClass.get(i).getType();
            if (!argumentType.isSubType(fieldType)) {
                throw new TypeException("Tipo errato per il parametro " + (i + 1) + " nell'invocazione del costruttore di " + classID);
            }
        }
        return new ObjectType(classType);
    }

    @Override
    public String codeGeneration() {
        //new pusha, in ordine, gli argomenti, il numero di argomenti e la label della classe
        StringBuilder argsCode = new StringBuilder();
        for (Node arg : argsList) {
            argsCode.append(arg.codeGeneration());
        }
        return argsCode
                + "push " + argsList.size() + "\n"
                + "push class" + classID + "\n"
                + "new\n";
    }

}
