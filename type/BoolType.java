package type;

public class BoolType implements Type {

    @Override
    public ID getID() {
        return ID.BOOL;
    }

    @Override
    public boolean isSubType(Type t) {
        return getID() == t.getID();
    }

    @Override
    public String toPrint() {
        return "bool";
    }

}