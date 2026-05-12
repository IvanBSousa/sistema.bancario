package sousa.banco.messaging;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import sousa.banco.logging.Log;

@ApplicationScoped
public class EmailNotificationConsumer {

    @Inject
    Mailer mailer;

    @Incoming("clientePF-cadastrado-in")
    public void consumir(JsonObject event) {
        ClientePFCadastradoEventDTO dto = event.mapTo(ClientePFCadastradoEventDTO.class);

        mailer.send(
                Mail.withText(
                        dto.email(),
                        "Bem-vindo ao Banco Sousa",
                        String.format("Olá %s, seu cadastro foi realizado com sucesso! Seu CPF: %s.",
                                dto.nome(), dto.cpf())
                )
        );

        System.out.printf(
                "Email enviado para %s (%s) informando sobre o cadastro do cliente com CPF %s.%n",
                dto.nome(),
                dto.email(),
                dto.cpf()
        );
    }
}
