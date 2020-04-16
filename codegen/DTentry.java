package codegen;

public class DTentry {
    private String methodID;
    private String methodLabel;

    public DTentry(String mID, String mLabel) {
        methodID = mID;
        methodLabel = mLabel;
    }

    public String getMethodID() {
        return methodID;
    }

    public String getMethodLabel() {
        return methodLabel;
    }
}