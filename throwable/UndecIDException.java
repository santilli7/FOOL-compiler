package throwable;

public class UndecIDException extends Exception {
    public UndecIDException(String id) {
        super("'" + id + "' non dichiarato!");
    }
}
