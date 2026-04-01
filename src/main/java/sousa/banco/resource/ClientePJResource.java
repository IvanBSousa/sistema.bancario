package sousa.banco.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sousa.banco.dto.ClientePJDTO;
import sousa.banco.service.ClientePJService;

import java.util.List;

@Path("/clientes-pj")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientePJResource {

    private final ClientePJService clientePJService;

    public ClientePJResource(ClientePJService clientePJService) {
        this.clientePJService = clientePJService;
    }

    @POST
    @Path("/cria-cliente-pj")
    public Response criaClientePJ(@Valid ClientePJDTO clientePJDTO) {
        clientePJService.criaClientePJ(clientePJDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/todos-clientes-pj")
    public Response getClientesPJ() {
        List<ClientePJDTO> clientesPJ = clientePJService.buscaTodosClientesPJ();
        return Response.ok(clientesPJ).build();
    }

    @GET
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
    @Path("/{id}")
    public Response deletaClientePJ(@PathParam("id") Long id) {
        boolean deletado = clientePJService.deletaClientePJ(id);
        if (deletado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
