package ast;

import parser.FOOLParser;
import symboltable.SymbolTable;
import throwable.MultipleIDException;
import throwable.TypeException;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class ParameterNode implements Node {
    private String ID;
    private Type type;
    private int offset;
    private boolean insideClass;
    private FOOLParser.VardecContext vardecContext;

    //PER LE FunCall
    public ParameterNode(String id, Type t, int o, FOOLParser.VardecContext context) {
        ID = id;
        type = t;
        offset = o;
        insideClass = false;
        vardecContext = context;
    }

    //PER I CAMPI DELLA CLASSI e per i paramentri della funzione (dichiarazione)
    public ParameterNode(String id, Type t, int o, boolean b, FOOLParser.VardecContext context) {
        ID = id;
        type = t;
        offset = o;
        insideClass = b;
        vardecContext = context;
    }

    public String getID() {
        return ID;
    }

    public Type getType() {
        return type;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();
        try {
            env.insertDeclarationFunClass(ID, type, offset, insideClass);
        } catch (MultipleIDException e) {
            res.add(e.getMessage());
        }
        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {
        return null;
    }

    @Override
    public String codeGeneration() {
        return "";
    }
}
