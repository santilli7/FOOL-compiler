package throwable;

public class MultipleIDException extends Exception {

    public MultipleIDException(String id) {
        super("Variabile '" + id + "' già dichiarata!");


    }

}