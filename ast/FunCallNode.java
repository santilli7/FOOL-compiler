package ast;

import parser.FOOLParser;
import symboltable.Entry;
import symboltable.SymbolTable;
import throwable.TypeException;
import throwable.UndecIDException;
import type.FunType;
import type.ObjectType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class FunCallNode implements Node {

    protected String ID;
    protected List<Node> args;
    protected Entry entry = null;
    protected int calledNestingLevel;
    private FOOLParser.FuncallContext ctx;

    public FunCallNode(String id, List<Node> arguments, FOOLParser.FuncallContext ctx) {
        ID = id;
        args = arguments;
        this.ctx = ctx;
    }

    public FOOLParser.FuncallContext getContext() {
        return ctx;
    }

    public List<Node> getArgs(){
        return args;
    }

    public String getID(){
        return ID;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();
        try {
            entry = env.checkID(ID);
        } catch (UndecIDException e) {
            res.add("La funzione '" + ID + "()' non è dichiarata!\n");
        }

        calledNestingLevel = env.getNestingLevel();

        //CheckSemantic di ogni argomento dato in input
        for (Node argument : args)
            res.addAll(argument.checkSemantics(env));

        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {
        FunType retFun;
        if (entry.getType().getID().equals(Type.ID.RETURN)) {
            retFun = (FunType) entry.getType();
        } else {
            throw new TypeException("Invocazione di una non funzione " + ID);
        }

        //argomenti attesi
        List<Type> argsExpected = retFun.getParameters();
        //se il numero di argomenti attesi è diverso dal numero di argomenti presi dal programma input (args)
        if (!(argsExpected.size() == args.size())) {
            throw new TypeException("Numero errato di parametri nell'invocazione di " + ID);
        }

        //verifica sui tipi tra i paramentri attesi e quelli in input
        //nel caso di args di funzioni isSubType verifica che i due tipi siano uguali
        int index = 0;
        for (Type i : argsExpected) {
            if (!args.get(index).typeCheck().isSubType(i)){
                throw new TypeException("Tipo errato per il parametro " + (index + 1) + " nell'invocazione di " + ID);
            }
            index++;
        }

        return retFun.getReturnType();
    }

    @Override
    public String codeGeneration() {
        StringBuilder parameterCode = new StringBuilder();
        //parametri in ordine inverso
        //tipo BoolNode||IntNode
        for (int i = args.size() - 1; i >= 0; i--) {
            parameterCode.append(args.get(i).codeGeneration());
        }

        //utilizzato per gestire le variabili annidate
        StringBuilder getActivationRecord = new StringBuilder();
        if (calledNestingLevel - entry.getNestingLevel() > 0) {
            getActivationRecord.append("lfp\n");
            for (int i = 0; i < calledNestingLevel - entry.getNestingLevel(); i++)
                getActivationRecord.append("lw\n");
            getActivationRecord.append("sfp\n");
        } else {
            getActivationRecord.append("");
        }


        return "lfp\n" + //pusho frame pointer e parametri
                parameterCode +
                getActivationRecord + //pusho access link (lw consecutivamente)
                "lfp\n" +
                // così si potrà risalire la catena statica
                "push " + entry.getOffset() + "\n" + // pusho l'offset logico per
                // accedere al codice della funzione
                "lfp\n" + //risalgo la catena statica
                "add\n" + //$fp + offset
                "lw\n" + //tramite l'indirizzo accedo al codice della funzione
                "js\n";
    }
}
