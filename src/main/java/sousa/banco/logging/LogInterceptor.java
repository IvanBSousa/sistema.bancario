package sousa.banco.logging;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

@Log
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LogInterceptor {

    private static final Logger LOG = Logger.getLogger(LogInterceptor.class);

    private final SecurityIdentity securityIdentity;
    private final JsonWebToken jwt;

    public LogInterceptor(SecurityIdentity securityIdentity, JsonWebToken jwt) {
        this.securityIdentity = securityIdentity;
        this.jwt = jwt;
    }

    @AroundInvoke
    public Object registrar(InvocationContext context) throws Exception {
        Log annotation = context.getMethod().getAnnotation(Log.class);
        if (annotation == null) {
            annotation = context.getTarget().getClass().getAnnotation(Log.class);
        }

        String acao = annotation != null && !annotation.value().isBlank()
                ? annotation.value()
                : context.getMethod().getName();

        String principal = securityIdentity.getPrincipal().getName();
        String username = jwt.getClaim("preferred_username");
        String sub = jwt.getSubject();

        LOG.infof(
                "Usuário=%s principal=%s sub=%s INICIOU acao=%s metodo=%s.%s",
                username, principal, sub,
                acao,
                context.getMethod().getDeclaringClass().getSimpleName(),
                context.getMethod().getName()
        );

        try {
            Object resultado = context.proceed();

            LOG.infof(
                    "Usuário=%s principal=%s sub=%s CONCLUIU acao=%s metodo=%s.%s",
                    username, principal, sub,
                    acao,
                    context.getMethod().getDeclaringClass().getSimpleName(),
                    context.getMethod().getName()
            );

            return resultado;
        } catch (Exception e) {
            LOG.errorf(
                    "Usuário=%s principal=%s sub=%s ERRO acao=%s metodo=%s.%s",
                    username, principal, sub,
                    acao,
                    context.getMethod().getDeclaringClass().getSimpleName(),
                    context.getMethod().getName(),
                    e.getMessage()
            );

            throw e;
        }
    }
}
