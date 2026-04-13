package sousa.banco.exception;

import io.vertx.core.http.HttpServerRequest;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import sousa.banco.dto.RespostaErrosDTO;

import java.time.LocalDateTime;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<BusinessException> {

    @Context
    HttpServerRequest request;

    private String getPath() {
        return (request != null) ? request.path() : "N/A";
    }

     private Response.Status getCodigo(BusinessException businessException) {
        if (businessException instanceof NotFoundException) {
            return Response.Status.NOT_FOUND;
        } else if (businessException instanceof ConflictException) {
            return Response.Status.CONFLICT;
        } else {
            return Response.Status.BAD_REQUEST;
        }
    }

    @Override
    public Response toResponse(BusinessException exception) {
        return Response.status(getCodigo(exception))
                .entity(new RespostaErrosDTO(
                        exception.getCodigo(),
                        exception.getMessage(),
                        getPath(),
                        LocalDateTime.now()))
                .build();



    }
}
