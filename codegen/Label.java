package codegen;

public class Label {
    private static int label = 0;
    private static int functionsLabelCount = 0;

    public static String nuovaLabel() {
        return "label" + (label++);
    }

    public static String nuovaLabelPerFunzione() {
        return "function" + (functionsLabelCount++);
    }

    public static void reset() {
        label = 0;
        functionsLabelCount = 0;
    }
}