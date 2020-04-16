package ast;

import symboltable.SymbolTable;
import throwable.TypeException;
import type.Type;
import type.VoidType;

import java.util.ArrayList;
import java.util.List;

public class NullNode implements Node {

    public String classID;

    public NullNode(String id){
        classID = id;
    }
    @Override
    public List<String> checkSemantics(SymbolTable env) {
        return new ArrayList<>();
    }

    @Override
    public Type typeCheck() throws TypeException {
        return new VoidType();
    }

    @Override
    public String codeGeneration() {
        return "push 0\n" +
                "push class"+classID+"\n" +
                "new\n";
    }
}