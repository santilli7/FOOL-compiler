package throwable;

import java.util.List;

public class SemanticException extends Error {

    public SemanticException(List<String> errors) {
        super(errors.stream().reduce("", (prev, curr) -> prev + curr));

    }

}
