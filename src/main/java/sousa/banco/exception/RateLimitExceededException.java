package sousa.banco.exception;

public class RateLimitExceededException extends BusinessException {
    public RateLimitExceededException(String message) {

        super("RATE_LIMIT_EXCEEDED", message);
    }
}
