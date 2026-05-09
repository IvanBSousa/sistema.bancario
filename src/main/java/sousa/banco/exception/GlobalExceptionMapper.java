package sousa.banco.exception;

import io.smallrye.faulttolerance.api.RateLimitException;
import io.vertx.core.http.HttpServerRequest;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import sousa.banco.dto.RespostaErrosDTO;

import java.time.LocalDateTime;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

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
    public Response toResponse(Throwable exception) {
        if (exception instanceof RateLimitException) {
            return Response.status(Response.Status.TOO_MANY_REQUESTS)
                    .entity(new RespostaErrosDTO(
                            "TO_MANY_REQUESTS",
                            "Limite de requisicoes",
                            getPath(),
                            LocalDateTime.now()
                    ))
                    .build();
        }

        if (exception instanceof BusinessException) {
            return Response.status(getCodigo((BusinessException) exception))
                    .entity(new RespostaErrosDTO(
                            ((BusinessException) exception).getCodigo(),
                            exception.getMessage(),
                            getPath(),
                            LocalDateTime.now()
                    ))
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new RespostaErrosDTO(
                        "INTERNAL_SERVER_ERROR",
                        exception.getMessage(),
                        getPath(),
                        LocalDateTime.now()))
                .build();
    }
}
