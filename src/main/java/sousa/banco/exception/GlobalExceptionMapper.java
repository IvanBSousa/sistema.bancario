package sousa.banco.exception;

import io.smallrye.faulttolerance.api.RateLimitException;
import io.vertx.core.http.HttpServerRequest;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import sousa.banco.dto.RespostaErrosDTO;

import java.time.LocalDateTime;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    @Context
    HttpServerRequest request;

    @Context
    HttpHeaders headers;

    private String getPath() {
        return (request != null) ? request.path() : "N/A";
    }

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof RateLimitException) {
            return buildResponse(
                    Response.Status.TOO_MANY_REQUESTS,
                    "TO_MANY_REQUESTS",
                    "Limite de requisicoes"
            );
        }

        if (exception instanceof BusinessException businessException) {
            return buildResponse(
                    getStatus(businessException),
                    businessException.getCodigo(),
                    businessException.getMessage()
            );
        }

        if (exception instanceof WebApplicationException webApplicationException) {
            if (shouldPreserveDefaultHtmlResponse(webApplicationException)) {
                return webApplicationException.getResponse();
            }
            return handleWebApplicationException(webApplicationException);
        }

        return buildResponse(
                Response.Status.INTERNAL_SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                "Ocorreu um erro interno no servidor."
        );
    }

    private boolean shouldPreserveDefaultHtmlResponse(WebApplicationException exception) {
        Response response = exception.getResponse();
        int status = response != null ? response.getStatus() : 500;
        String accept = headers != null ? headers.getHeaderString(HttpHeaders.ACCEPT) : null;

        return status == 404 && accept != null && accept.contains("text/html");
    }

    private Response handleWebApplicationException(WebApplicationException exception) {
        Response response = exception.getResponse();
        int statusCode = response != null ? response.getStatus() : 500;
        Response.Status status = Response.Status.fromStatusCode(statusCode);

        String codigo = switch (statusCode) {
            case 400 -> "BAD_REQUEST";
            case 401 -> "UNAUTHORIZED";
            case 403 -> "FORBIDDEN";
            case 404 -> "NOT_FOUND";
            case 405 -> "METHOD_NOT_ALLOWED";
            case 406 -> "NOT_ACCEPTABLE";
            case 415 -> "UNSUPPORTED_MEDIA_TYPE";
            case 429 -> "TOO_MANY_REQUESTS";
            default -> "HTTP_" + statusCode;
        };

        String mensagem = exception.getMessage();
        if (mensagem == null || mensagem.isBlank()) {
            mensagem = status != null ? status.getReasonPhrase() : "Erro HTTP";
        }

        return buildResponse(statusCode, codigo, mensagem);
    }

    private Response buildResponse(Response.Status status, String codigo, String detalhes) {
        return Response.status(status)
                .entity(new RespostaErrosDTO(
                        codigo,
                        detalhes,
                        getPath(),
                        LocalDateTime.now()
                ))
                .build();
    }

    private Response buildResponse(int status, String codigo, String detalhes) {
        return Response.status(status)
                .entity(new RespostaErrosDTO(
                        codigo,
                        detalhes,
                        getPath(),
                        LocalDateTime.now()
                ))
                .build();
    }

    private Response.Status getStatus(BusinessException exception) {
        if (exception instanceof NotFoundException) {
            return Response.Status.NOT_FOUND;
        }
        if (exception instanceof ConflictException) {
            return Response.Status.CONFLICT;
        }
        if (exception instanceof RateLimitExceededException) {
            return Response.Status.TOO_MANY_REQUESTS;
        }
        return Response.Status.BAD_REQUEST;
    }
}
