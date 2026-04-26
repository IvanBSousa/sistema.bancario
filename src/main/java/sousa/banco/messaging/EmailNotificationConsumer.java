package sousa.banco.messaging;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class EmailNotificationConsumer {

    @Incoming("clientePF-cadastrado-in")
    public void consumir(JsonObject event) {
        ClientePFCadastradoEventDTO dto = event.mapTo(ClientePFCadastradoEventDTO.class);

        System.out.printf(
                "Email enviado para %s (%s) informando sobre o cadastro do cliente com CPF %s.%n",
                dto.nome(),
                dto.email(),
                dto.cpf()
        );
    }
}
