package sousa.banco.exception;

public class ConflictException extends BusinessException {

    public ConflictException(String message) {
        super("CONFLICT", message);
    }
}
