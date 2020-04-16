package ast;

import codegen.FunctionCode;
import symboltable.SymbolTable;
import throwable.MultipleIDException;
import throwable.TypeException;
import type.FunType;
import type.Type;
import type.VoidType;

import java.util.ArrayList;
import java.util.List;

public class LetInStmsNode implements Node {
    private List<Node> declarations;
    private List<Node> stms;

    public LetInStmsNode(List<Node> decs, List<Node> stms) {
        declarations = decs;
        this.stms = stms;
    }

    @Override
    public Type typeCheck() throws TypeException {
        for (Node dec : declarations)
            dec.typeCheck();
        for (Node stm : stms)
            stm.typeCheck();

        return new VoidType();
    }


    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();
        env.newScope();

        if (declarations.size() > 0) {
            env.setOffset(-1);
        }

        for (Node node : declarations) {
            if (node instanceof FunNode) {
                FunNode f = (FunNode) node;
                List<Type> paramsType = new ArrayList<>();
                for (ParameterNode p : f.getParameterNodeArrayList()) {
                    paramsType.add(p.getType());
                }
                try {
                    env.insertDeclaration(f.ID, new FunType(paramsType, f.returnType), env.getOffset());
                    env.decreaseOffset();
                } catch (MultipleIDException e) {
                    res.add("La funzione '" + f.ID + "' è già stata dichiarata!\n");
                    return res;
                }
            }
        }

        for (Node node : declarations) {
            res.addAll(node.checkSemantics(env));
        }

        if (stms.size() > 0) {
            for (Node node : stms) {
                res.addAll(node.checkSemantics(env));
            }
        }

        env.exitScope();

        return res;

    }

    @Override
    public String codeGeneration() {
        StringBuilder declCode = new StringBuilder();
        for (Node dec : declarations) {
            if (dec instanceof FunNode) {
                declCode.append(dec.codeGeneration());
            }
        }

        for (Node dec : declarations) {
            if (!(dec instanceof FunNode)) {
                declCode.append(dec.codeGeneration());
            }
        }

        StringBuilder stmsCode = new StringBuilder();
        for (Node stm : stms) {
            stmsCode.append(stm.codeGeneration());
        }
        return declCode.toString() +
                stmsCode + "halt\n" +
                FunctionCode.getFunctionsCode();
        //add dispatcher
    }
}