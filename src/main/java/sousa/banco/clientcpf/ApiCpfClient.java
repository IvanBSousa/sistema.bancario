package sousa.banco.clientcpf;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sousa.banco.dto.ConsultaCPFResonseDTO;

@RegisterRestClient(configKey = "apicpf-api")
@Path("/api/consulta")
public interface ApiCpfClient {

    @GET
    ConsultaCPFResonseDTO consultaCPF(
            @QueryParam("cpf") String cpf,
            @QueryParam("api_key") String apiKey);
}
