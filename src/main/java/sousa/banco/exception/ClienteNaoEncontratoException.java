package sousa.banco.exception;

public class ClienteNaoEncontratoException extends RuntimeException {
    public ClienteNaoEncontratoException(String message) {
        super(message);
    }
}
