package ast;

import parser.FOOLParser;
import symboltable.SymbolTable;
import throwable.MultipleIDException;
import throwable.TypeException;
import throwable.UndecIDException;
import type.ClassType;
import type.ObjectType;
import type.Type;
import type.VoidType;

import java.util.ArrayList;
import java.util.List;

public class VarAsgmNode implements Node {
    //Assegnamento nel Let quindi a seguito di una dichiarazioone
    private String ID;
    private Type assignedType;
    private Node expression;
    private FOOLParser.VarasmContext varasmContext;

    public VarAsgmNode(String id, Type assignedType, Node expression, FOOLParser.VarasmContext varasmContext) {
        this.ID = id;
        this.assignedType = assignedType;
        this.expression = expression;
        this.varasmContext = varasmContext;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();

        //Se sto instanziando un nuovo oggetto, aggiorno le informazioni
        if (assignedType instanceof ObjectType) {
            ObjectType decType = (ObjectType) assignedType;
            try {
                env.checkID(decType.getClassType().getClassID());
            } catch (UndecIDException e) {
                System.out.println("La classe '" + decType.getClassType().getClassID()+"' non è stata definita!");
                System.exit(-1);
            }
            res.addAll(decType.updateClassType(env));
        }


        //PRIMA di gestire la symtable verifico l'exp
        res.addAll(expression.checkSemantics(env));




        try {
            env.insertDeclaration(ID, assignedType, env.getOffset());
            //se l'espressione che assegno è null (ossia VoidType) setto nella entry il valore inizializzato a false
            if (expression.typeCheck() instanceof VoidType) {
                env.checkID(ID).setInitialized(false);
            } else {
                env.checkID(ID).setInitialized(true);
            }
            env.decreaseOffset();
        } catch (MultipleIDException | UndecIDException | TypeException e) {
            res.add(e.getMessage());
        }
        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {
        //Se la variabile è di tipo oggetto e l'espressione che le sto assegnando è null lo posso fare
        if (assignedType instanceof ObjectType && expression instanceof NullNode) {
            return assignedType;
        }
        if (!expression.typeCheck().isSubType(assignedType)) {
            throw new TypeException("Valore incompatibile per la variabile " + ID);
        }

        return assignedType;
    }

    @Override
    public String codeGeneration() {
        return expression.codeGeneration();
    }
}
