package ast;

import codegen.FunctionCode;
import symboltable.SymbolTable;
import throwable.MultipleIDException;
import throwable.TypeException;
import type.FunType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class LetInExpNode implements Node {
    private List<Node> declarations;
    private Node exp;

    public LetInExpNode(List<Node> decs, Node exp) {

        declarations = decs;
        this.exp = exp;
    }

    @Override
    public Type typeCheck() throws TypeException {

        for (Node dec : declarations)
            dec.typeCheck();
        return exp.typeCheck();

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

        //Parte In: valutazione dell'exp oppure stms
        res.addAll(exp.checkSemantics(env));

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

        return declCode +
                exp.codeGeneration() + "halt\n" +
                FunctionCode.getFunctionsCode();

        //add dispatcher
    }
}
