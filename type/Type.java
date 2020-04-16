package type;

public interface Type {

    enum ID { //tipi supportati dal linguaggio
        INT,
        BOOL,
        RETURN, //Tipo di ritorno della funzione
        CLASS,
        OBJECT,
        VOID
    }

    ID getID(); //restituisce uno dei tipi possibili del linguaggio

    boolean isSubType(Type t); //per gestire le regole di subtyping

    String toPrint(); //per stampare ad output il tipo finale del programma

}
