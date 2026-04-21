package sousa.banco.resource;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TelemetriaResource {

    private final MeterRegistry meterRegistry;

    public TelemetriaResource(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GET
    @RolesAllowed({"ROLE_ADMIN"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/telemetria")
    public Response getTimerMetrics() {
        Map<String, Map<String, Object>> allMetrics = new HashMap<>();

        // Timer para "criaClientePF"
        Map<String, Object> criaClientePFMetrics = new HashMap<>();
        Timer criaClientePFTimer = meterRegistry.find("criaClientePF").timer();
        if (criaClientePFTimer != null) {
            criaClientePFMetrics.put("count", criaClientePFTimer.count());
            criaClientePFMetrics.put("sum_seconds", criaClientePFTimer.totalTime(TimeUnit.SECONDS));
            criaClientePFMetrics.put("max_seconds", criaClientePFTimer.max(TimeUnit.SECONDS));
            criaClientePFMetrics.put("mean_seconds", criaClientePFTimer.mean(TimeUnit.SECONDS));
        }
        allMetrics.put("criaClientePF", criaClientePFMetrics);

        // Timer para "buscaTodosClientesPF"
        Map<String, Object> buscaTodosClientesPFMetrics = new HashMap<>();
        Timer buscaTodosClientesPFTimer = meterRegistry.find("buscaTodosClientesPF").timer();
        if (buscaTodosClientesPFTimer != null) {
            buscaTodosClientesPFMetrics.put("count", buscaTodosClientesPFTimer.count());
            buscaTodosClientesPFMetrics.put("sum_seconds", buscaTodosClientesPFTimer.totalTime(TimeUnit.SECONDS));
            buscaTodosClientesPFMetrics.put("max_seconds", buscaTodosClientesPFTimer.max(TimeUnit.SECONDS));
            buscaTodosClientesPFMetrics.put("mean_seconds", buscaTodosClientesPFTimer.mean(TimeUnit.SECONDS));
        }
        allMetrics.put("buscaTodosClientesPF", buscaTodosClientesPFMetrics);

        // Adicione outros timers aqui

        return Response.ok(allMetrics).build();
    }
}