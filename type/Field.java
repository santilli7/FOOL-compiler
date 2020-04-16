package type;

public class Field {

    private String id;
    private Type type;

    public Field(String i, Type t) {
        id = i;
        type = t;
    }

    public String getID() {
        return id;
    }

    public Type getType() {
        return type;
    }
}

