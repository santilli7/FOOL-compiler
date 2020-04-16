package symboltable;

import type.Type;

public class Entry {

    private int nestingLevel; //nestinglevel
    private Type type;
    private int offset;
    private boolean insideClass;
    private boolean initialized;  //usata per gestire il null

    public Entry(int nestingLevel) {
        this.nestingLevel = nestingLevel;
        this.initialized = true;
    }

    public Entry(int nestingLevel, Type type) {
        this.nestingLevel = nestingLevel;
        this.type = type;
        this.initialized = true;
    }

    public Entry(int nestingLevel, Type type, int offset) {
        this.nestingLevel = nestingLevel;
        this.type = type;
        this.offset = offset;
        this.initialized = true;
    }

    public Entry(int nestingLevel, Type type, int offset, boolean insideClass) {
        this.nestingLevel = nestingLevel;
        this.type = type;
        this.offset = offset;
        this.insideClass = insideClass;
        this.initialized = true;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public Type getType() {
        return type;
    }


    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isInsideClass() {
        return insideClass;
    }
}