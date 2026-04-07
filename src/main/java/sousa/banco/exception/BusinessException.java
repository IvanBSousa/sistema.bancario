package sousa.banco.exception;

public class BusinessException extends RuntimeException {

    private final String codigo;

    public BusinessException(String message) {
        super(message);
        this.codigo = "BUSINESS_ERROR";
    }

    public BusinessException(String message, String codigo) {
        super(message);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
