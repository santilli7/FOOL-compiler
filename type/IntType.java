package type;

public class IntType implements Type {

    @Override
    public ID getID() {
        return ID.INT;
    }

    @Override
    public boolean isSubType(Type t) {
        return t.getID() == this.getID();
    }

    @Override
    public String toPrint() {
        return "int";
    }
}
