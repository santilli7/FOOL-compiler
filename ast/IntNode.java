package ast;

import symboltable.SymbolTable;
import throwable.TypeException;
import type.IntType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class IntNode implements Node {

    private int value;

    public IntNode(int val) {
        value = val;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        return new ArrayList<>();
    }

    @Override
    public Type typeCheck() throws TypeException {
        return new IntType();
    }

    @Override
    public String codeGeneration() {
        return "push " + value + "\n";
    }
}
