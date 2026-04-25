package sousa.banco.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import sousa.banco.dto.MetricasDTO;
import sousa.banco.service.TelemetriaService;

import java.util.ArrayList;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TelemetriaResource {

    private final TelemetriaService telemetriaService;

    public TelemetriaResource(TelemetriaService telemetriaService) {
        this.telemetriaService = telemetriaService;
    }

    @GET
    @RolesAllowed({"ROLE_ADMIN"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/telemetria")
    public Response getTimerMetrics() {
        return Response.ok(
                telemetriaService.buscarMetricas(
                        "criaClientePF",
                        "buscaTodosClientesPF"
                )).build();
    }
}