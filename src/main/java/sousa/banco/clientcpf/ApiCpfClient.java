package sousa.banco.clientcpf;

import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sousa.banco.dto.ConsultaCPFResponseDTO;

@RegisterRestClient(configKey = "apicpf-api")
@Path("/api/consulta")
public interface ApiCpfClient {

    @GET
    ConsultaCPFResponseDTO consultaCPF(
            @QueryParam("cpf") String cpf,
            @QueryParam("api_key") String apiKey);
}
