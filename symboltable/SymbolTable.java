package symboltable;

import throwable.MultipleIDException;
import throwable.UndecIDException;
import type.ClassType;
import type.FunType;
import type.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class SymbolTable {

    private List<HashMap<String, Entry>> symTable = new ArrayList<HashMap<String, Entry>>();
    private int offset = 0;
    private Entry lastEntryIstance = null;

    public List<HashMap<String, Entry>> getSymTable() {
        return symTable;
    }

    public void setSymTable(List<HashMap<String, Entry>> symTable) {
        this.symTable = symTable;
    }

    public void print() {
        System.out.println("SYMBOL TABLE (" + symTable.size() + ") : ");
        //per ogni nodo della lista
        int i = 0;
        for (HashMap item : symTable) {
            System.out.println("LV: " + (i++) + ": " + item.keySet());
        }
    }

    public Entry getLastEntryIstance() {
        return lastEntryIstance;
    }


    public Type getTypeOf(String id) throws UndecIDException {
        return checkID(id).getType();
    }

    public int getOffset() {
        return offset;
    }

    public void increaseOffset() {
        offset = offset + 1;
    }

    public void decreaseOffset() {
        offset = offset - 1;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNestingLevel() {
        return symTable.size() - 1;
    }

    public void newScope() {
        HashMap<String, Entry> hashMap = new HashMap<>();
        symTable.add(hashMap);
    }

    public void exitScope() {
        symTable.remove(getNestingLevel());
    }

    //Dichiarazione semplice
    public SymbolTable insertDeclaration(String id, Type type, int offset) throws MultipleIDException {
        Entry nuovaEntry = new Entry(getNestingLevel(), type, offset);
        if (symTable.get(getNestingLevel()).containsKey(id)) {
            throw new MultipleIDException(id);
        }
        if (type instanceof ClassType) {
            lastEntryIstance = nuovaEntry;
        }
        symTable.get(getNestingLevel()).put(id, nuovaEntry);

        return this;
    }

    //aggiorna l'attributo type della STentry con chiave ID
    //è utilizzato per aggiornare il supertipo delle classi (dopo tutte le classdec)
    public SymbolTable setDeclarationType(String id, Type newtype, int offset) throws UndecIDException {
        Entry nuovaEntry = new Entry(getNestingLevel(), newtype, offset);
        Entry vecchiaEntry = symTable.get(getNestingLevel()).replace(id, nuovaEntry);
        if (newtype instanceof ClassType) {
            lastEntryIstance = nuovaEntry;
        }
        if (vecchiaEntry == null) {
            throw new UndecIDException(id);
        }
        return this;
    }

    //Dichiarazione in Classe o in Funzione(parametri,campi e varlocali)
    public SymbolTable insertDeclarationFunClass(String id, Type type, int offset, boolean inside) throws MultipleIDException {
        Entry nuovaEntry = new Entry(getNestingLevel(), type, offset, inside);

        if (symTable.get(getNestingLevel()).containsKey(id)) {
            throw new MultipleIDException(id);
        }
        if (type instanceof ClassType) {
            lastEntryIstance = nuovaEntry;
        }
        symTable.get(getNestingLevel()).put(id, nuovaEntry);

        return this;
    }

    public Entry checkID(String id) throws UndecIDException {
        ListIterator<HashMap<String, Entry>> i = symTable.listIterator(symTable.size());
        while (i.hasPrevious()) {
            HashMap<String, Entry> current = i.previous();
            if (current.containsKey(id)) {
                return current.get(id);
            }
        }
        throw new UndecIDException(id);
    }

    public Entry checkIDnotFunction(String id) throws UndecIDException {
        ListIterator<HashMap<String, Entry>> i = symTable.listIterator(symTable.size());
        while (i.hasPrevious()) {
            HashMap<String, Entry> current = i.previous();
            if (current.containsKey(id) && !(current.get(id).getType() instanceof FunType)) {
                return current.get(id);
            }
        }
        throw new UndecIDException(id);
    }
}
