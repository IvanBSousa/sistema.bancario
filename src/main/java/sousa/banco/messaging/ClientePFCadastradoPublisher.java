package sousa.banco.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import sousa.banco.entity.ClientePF;
import sousa.banco.entity.Contato;
import sousa.banco.enums.TipoContatoEnum;

@ApplicationScoped
public class ClientePFCadastradoPublisher {

    @Channel("clientePF-cadastrado-out")
    Emitter<ClientePFCadastradoEventDTO> emitter;

    public void publicar(ClientePF clientePF) {
        String email = clientePF.getContato()
                .stream()
                .filter(c -> c.getTipoContato() == TipoContatoEnum.EMAIL_PESSOAL)
                .map(Contato::getContato)
                .findFirst()
                .orElse(null);

        if (email == null || email.isBlank()) {
            return;
        }

        emitter.send(new ClientePFCadastradoEventDTO(
                clientePF.getId(),
                clientePF.getNomeCompleto(),
                email,
                clientePF.getCpf()
        ));
    }
}
