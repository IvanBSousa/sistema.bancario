package sousa.banco.exception;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Priorities;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String JSON_INVALIDO = "JSON inválido: ";

//    @ServerExceptionMapper
//    @Priority(Priorities.USER)
//    public void cpfNaoEncontrado
}
