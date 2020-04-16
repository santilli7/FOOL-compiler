package codegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatchTable {

    //Hashmap di tutte le dispatch table
    private static HashMap<String, List<DTentry>> dispatchTables = new HashMap<>();

    public static void addDispatchTable(String classID, List<DTentry> dt) {
        dispatchTables.put(classID, dt);
    }

    // Viene ritornata una copia della dispatch table (così non si modifica, per riferimento, quella del padre)
    public static List<DTentry> getDispatchTable(String classID) {
        List<DTentry> tmp = new ArrayList<>();
        List<DTentry> dt = dispatchTables.get(classID);
        for (DTentry dte : dt) {
            DTentry tmp2 = new DTentry(dte.getMethodID(), dte.getMethodLabel());
            tmp.add(tmp2);
        }
        return tmp;
    }

    public static String generaCodiceDispatchTable() {
        StringBuilder stringBuilder = new StringBuilder();
        // String è l'ID della classe, con un'arraylist di tutti i suoi metodi
        // Per ogni classe
        for (Map.Entry<String, List<DTentry>> dt : dispatchTables.entrySet()) {
            //Genera le label delle classi
            stringBuilder.append("class" + dt.getKey() + ":\n"); // nuovaLabelPerDispatchTable
            // Per ogni elemento della dispatch table
            for (DTentry entry : dispatchTables.get(dt.getKey())) {
                //Aggiunge sotto alla label della classe le label delle funzioni a cui si riferisce
                stringBuilder.append(entry.getMethodLabel());
            }
        }
        return stringBuilder.toString();
    }

    public static void reset() {
        dispatchTables = new HashMap<>();
    }
}