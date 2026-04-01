package sousa.banco.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sousa.banco.entity.ClientePF;

@ApplicationScoped
public class ClientePFRepository implements PanacheRepository<ClientePF> {

    public ClientePF buscaPorCpf(String cpf) {
        return find("cpf", cpf).firstResult();
    }
}
