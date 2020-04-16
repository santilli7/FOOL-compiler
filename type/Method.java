package type;

public class Method {

    private String id;
    private FunType type;

    public Method(String i, FunType t) {
        id = i;
        type = t;
    }

    public String getId() {
        return id;
    }

    public FunType getType() {
        return type;
    }
}