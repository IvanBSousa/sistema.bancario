package sousa.banco.clientcpf;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import sousa.banco.dto.ConsultaCPFResonseDTO;

@ApplicationScoped
public class ConsultaCPF {

    @RestClient
    ApiCpfClient apiCpfClient;

    @ConfigProperty(name = "apicpf.api.key")
    String apiKey;

    public ConsultaCPFResonseDTO consultaCPF(String cpf) {
        try {
            return apiCpfClient.consultaCPF(cpf, apiKey);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar CPF: " + e.getMessage(), e);
        }
    }
}
