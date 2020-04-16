package type;

import symboltable.SymbolTable;
import throwable.UndecIDException;

import java.util.ArrayList;
import java.util.List;

public class ObjectType implements Type {

    private ClassType classT;

    public ObjectType(ClassType classT) {
        this.classT = classT;
    }

    public ClassType getClassType() {
        return this.classT;
    }

    // This is used to update the classType filling superType when needed
    public List<String> updateClassType(SymbolTable env) {
        List<String> res = new ArrayList<>();
        try {
            try {
                this.classT = (ClassType) env.getTypeOf(classT.getClassID());
            } catch (UndecIDException e) {
                throw new UndecIDException(classT.getClassID());
            }
        } catch (UndecIDException e) {
            res.add(new String(e.getMessage()));
        }
        return res;
    }

    @Override
    public ID getID() {
        return ID.OBJECT;
    }

    @Override
    public boolean isSubType(Type type) {
        if (type instanceof ObjectType) {
            ObjectType it2 = (ObjectType) type;
            return classT.isSubType(it2.getClassType());
        } else {
            return false;
        }
    }

    @Override
    public String toPrint() {
        return "Oggetto: " + classT.getClassID();
    }
}