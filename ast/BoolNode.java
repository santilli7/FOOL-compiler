package ast;

import symboltable.SymbolTable;
import throwable.TypeException;
import type.BoolType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class BoolNode implements Node {

    private boolean value;

    public BoolNode(boolean val) {
        value = val;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        return new ArrayList<>(); //non crea livelli di scope
    }


    @Override
    public Type typeCheck() throws TypeException {
        return new BoolType(); //tipo primitivo
    }

    @Override
    public String codeGeneration() {
        if (value)
            return "push " + 1 + "\n";
        else {
            return "push " + 0 + "\n";
        }
    }

}
