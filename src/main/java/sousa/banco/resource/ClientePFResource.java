package sousa.banco.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import sousa.banco.clientcpf.ConsultaCPF;
import sousa.banco.dto.ClientePFDTO;
import sousa.banco.service.ClientePFService;

import java.util.List;

@Path("/clientes-pf")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientePFResource {

    private final ClientePFService clientePFService;

    public ClientePFResource(ClientePFService clientePFService) {
        this.clientePFService = clientePFService;
    }

    @POST
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/cria-cliente-pf")
    public Response criaClientePF(@Valid ClientePFDTO clientePFDTO) {
        clientePFService.criaClientePF(clientePFDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/todos-clientes-pf")
    public Response getClientesPF() {
        List<ClientePFDTO> clientesPF = clientePFService.buscaTodosClientesPF();
        return Response.ok(clientesPF).build();
    }

    @GET
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/id/{id}")
    public Response getClientePFById(@PathParam("id") Long id) {
        ClientePFDTO clientePF = clientePFService.buscaClientePFPorId(id);
        if (clientePF != null) {
            return Response.ok(clientePF).build();
        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @GET
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/cpf/{cpf}")
    public Response getClientePFPorCPF(@PathParam("cpf") String cpf) {
        ClientePFDTO clientePF = clientePFService.buscaClientePFPorCPF(cpf);
        if (clientePF != null) {
            return Response.ok(clientePF).build();
        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @Path("/{id}")
    public Response atualizaClientePF(@PathParam("id") Long id, @Valid ClientePFDTO clientePFDTO) {
        ClientePFDTO atualizado = clientePFService.atualizaClientePF(id, clientePFDTO);
        if (atualizado != null) {
            return Response.ok(atualizado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

     @DELETE
     @RolesAllowed({"ROLE_ADMIN"})
     @SecurityRequirement(name = "Keycloak")
     @Path("/{id}")
     public Response deleteClientePF(@PathParam("id") Long id) {
         boolean deletado = clientePFService.deletaClientePF(id);
         if (deletado) {
             return Response.noContent().build();
         } else {
             return Response.status(Response.Status.NOT_FOUND).build();
         }
     }

     @GET
     @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
     @SecurityRequirement(name = "Keycloak")
     @Path("consulta-cpf-api/{cpf}")
     public Response infoCPF(@PathParam("cpf") String cpf) {
        var resposta = clientePFService.buscaDadosCPF(cpf);
        if (resposta != null) {
            return Response.ok(resposta).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
     }
}
