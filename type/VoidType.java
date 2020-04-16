package type;

public class VoidType implements Type {
    @Override
    public ID getID() {
        return ID.VOID;
    }

    @Override
    public boolean isSubType(Type t) {
        return getID() == t.getID();
    }

    @Override
    public String toPrint() {
        return "void";
    }
}
