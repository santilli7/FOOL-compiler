package ast;

import parser.FOOLParser;
import symboltable.Entry;
import symboltable.SymbolTable;
import throwable.TypeException;
import throwable.UndecIDException;
import type.ObjectType;
import type.Type;
import type.VoidType;

import java.util.ArrayList;
import java.util.List;

public class AsgmNode implements Node {
    private String ID;
    private Node exp;
    private int calledNestingLevel;
    private FOOLParser.VarAsmStmContext ctx;
    private Entry entry;

    public AsgmNode(String id, Node expression, FOOLParser.VarAsmStmContext ctxA) {
        ID = id;
        exp = expression;
        ctx = ctxA;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();

        res.addAll(exp.checkSemantics(env));

        try {
            entry = env.checkID(ID);
            calledNestingLevel = env.getNestingLevel();
        } catch (UndecIDException e) {
            res.add("Tentativo di assegnamento Fallito: " + e.getMessage() + "\n");
        }
        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {
        //Se la variabile è di tipo oggetto e l'espressione che le sto assegnando è null posso assegnarlo, altrimenti no
        if (entry.getType() instanceof ObjectType && exp instanceof NullNode) {
            throw new TypeException("Utilizzo non permesso di NULL per la variabile: '" + ID + "'");
        }
        Type tExp = exp.typeCheck();
        Type tID = entry.getType();
        if (!tExp.isSubType(tID))
            throw new TypeException("Valore incompatibile per la variabile: '" + ID + "'");

        return new VoidType();
    }

    @Override
    public String codeGeneration() {
        StringBuilder getActivationRecord = new StringBuilder();
        for (int i = 0; i < calledNestingLevel - entry.getNestingLevel(); i++)
            getActivationRecord.append("lw\n");

        return exp.codeGeneration() +
                "push " + entry.getOffset() + "\n" + //metto offset sullo stack
                "lfp\n" + getActivationRecord + //risalgo la catena statica
                "add\n" +
                //carico sullo stack il valore all'indirizzo ottenuto
                "sw\n";
    }
}
