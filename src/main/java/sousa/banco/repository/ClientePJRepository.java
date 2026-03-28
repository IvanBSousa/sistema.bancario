package sousa.banco.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sousa.banco.entity.ClientePJ;

@ApplicationScoped
public class ClientePJRepository implements PanacheRepository<ClientePJ> {
}
