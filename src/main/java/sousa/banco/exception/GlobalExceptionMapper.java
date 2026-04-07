package sousa.banco.exception;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.exception.ConstraintViolationException;
import sousa.banco.dto.RespostaErrosDTO;

import java.time.LocalDateTime;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Context
    UriInfo uriInfo;

    private String getPath() {
        return (uriInfo != null) ? uriInfo.getPath() : "N/A";
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
    public Response toResponse(Exception exception) {
        if (exception instanceof ConstraintViolationException) {
            return Response.status(409)
                    .entity(new RespostaErrosDTO(
                            "DB_CONSTRAINT",
                            "Violação de integridade",
                            getPath(),
                            LocalDateTime.now()))
                    .build();
        }

        if (exception instanceof BusinessException businessException) {
            return Response.status(getCodigo(businessException))
                    .entity(new RespostaErrosDTO(
                            businessException.getCodigo(),
                            businessException.getMessage(),
                            getPath(),
                            LocalDateTime.now()))
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new RespostaErrosDTO(
                        "INTERNAL_SERVER_ERROR",
                        "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.",
                        getPath(),
                        LocalDateTime.now()))
                .build();
    }
}
