package sousa.banco.resource;

import io.smallrye.faulttolerance.api.RateLimit;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import sousa.banco.dto.ClientePJDTO;
import sousa.banco.interceptor.TimeMetrics;
import sousa.banco.logging.Log;
import sousa.banco.service.ClientePJService;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Log
@Path("/clientes-pj")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientePJResource {

    private final ClientePJService clientePJService;

    public ClientePJResource(ClientePJService clientePJService) {
        this.clientePJService = clientePJService;
    }

    @POST
    @TimeMetrics(value = "criaClientePJ", description = "Tempo gasto para criar um cliente PJ")
    @RateLimit(value = 2, window = 1, windowUnit = ChronoUnit.MINUTES)
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/cria-cliente-pj")
    public Response criaClientePJ(@Valid ClientePJDTO clientePJDTO) {
        clientePJService.criaClientePJ(clientePJDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @TimeMetrics(value = "buscaTodosClientesPJ", description = "Tempo gasto para buscar todos clientes PJ")
    @RateLimit(value = 2, window = 1, windowUnit = ChronoUnit.MINUTES)
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/todos-clientes-pj")
    public Response getClientesPJ() {
        List<ClientePJDTO> clientesPJ = clientePJService.buscaTodosClientesPJ();
        return Response.ok(clientesPJ).build();
    }

    @GET
    @TimeMetrics(value = "buscaClientePJPorId", description = "Tempo gasto para buscar um cliente PJ por ID")
    @RateLimit(value = 2, window = 1, windowUnit = ChronoUnit.MINUTES)
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/{id}")
    public Response getClientePJById(@PathParam("id") Long id) {
        ClientePJDTO clientePJ = clientePJService.buscaClientePJPorId(id);
        if (clientePJ != null) {
            return Response.ok(clientePJ).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @TimeMetrics(value = "atualizaClientePJ", description = "Tempo gasto para atualizar um cliente PJ")
    @RateLimit(value = 2, window = 1, windowUnit = ChronoUnit.MINUTES)
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/{id}")
    public Response atualizaClientePJ(@PathParam("id") Long id, @Valid ClientePJDTO clientePJDTO) {
        ClientePJDTO atualizado = clientePJService.atualizaClientePJ(id, clientePJDTO);
        if (atualizado != null) {
            return Response.ok(atualizado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @TimeMetrics(value = "deletaClientePJ", description = "Tempo gasto para deletar um cliente PJ")
    @RateLimit(value = 2, window = 1, windowUnit = ChronoUnit.MINUTES)
    @RolesAllowed({"ROLE_ADMIN"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/{id}")
    public Response deletaClientePJ(@PathParam("id") Long id) {
        clientePJService.deletaClientePJ(id);
        return Response.noContent().build();
    }
}
