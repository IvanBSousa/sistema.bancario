package sousa.banco.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    public Response criaClientePF(@Valid ClientePFDTO clientePFDTO) {
        clientePFService.criaClientePF(clientePFDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response getClientesPF() {
        List<ClientePFDTO> clientesPF = clientePFService.buscaTodosClientesPF();
        return Response.ok(clientesPF).build();
    }
}
