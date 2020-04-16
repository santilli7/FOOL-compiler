package ast;

import symboltable.SymbolTable;
import throwable.TypeException;
import type.Type;

import java.util.List;

public interface Node {

    //fa il type checking e ritorna:
    //  per una espressione, il suo tipo (oggetto BoolTypeNode o IntTypeNode)
    //  per una dichiarazione, "null"
    Type typeCheck() throws TypeException;

    String codeGeneration();

    List<String> checkSemantics(SymbolTable env);

}