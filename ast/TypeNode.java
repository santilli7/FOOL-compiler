package ast;

import symboltable.SymbolTable;
import throwable.TypeException;
import type.*;

import java.util.ArrayList;
import java.util.List;

public class TypeNode implements Node {

    String assignedType;
    Type type;

    public TypeNode(String type) {
        assignedType = type;
        switch (type) {
            case "int":
                this.type = new IntType();
                break;
            case "bool":
                this.type = new BoolType();
                break;
            case "void":
                this.type = new VoidType();
                break;
            default: //è una classe
                this.type = new ObjectType(new ClassType(assignedType));
                break;
        }
    }


    @Override
    public Type typeCheck() throws TypeException {
        return type;
    }

    @Override
    public String codeGeneration() {
        return "";
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        //non necessario
        return new ArrayList<>();
    }
}
