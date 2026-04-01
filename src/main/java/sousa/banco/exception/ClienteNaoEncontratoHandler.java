package sousa.banco.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ClienteNaoEncontratoHandler implements ExceptionMapper<ClienteNaoEncontratoException> {

    @Override
    public Response toResponse(ClienteNaoEncontratoException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .build();
    }
}
