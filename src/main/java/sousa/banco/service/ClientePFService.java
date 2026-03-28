package sousa.banco.service;

import jakarta.enterprise.context.ApplicationScoped;
import sousa.banco.dto.ClientePFDTO;
import sousa.banco.mapper.ClientePFMapper;
import sousa.banco.repository.ClientePFRepository;

import java.util.List;

@ApplicationScoped
public class ClientePFService {

    private final ClientePFRepository clientePFRepository;

    public ClientePFService(ClientePFRepository clientePFRepository) {
        this.clientePFRepository = clientePFRepository;
    }

    public void criaClientePF(ClientePFDTO clientePFDTO) {
        clientePFRepository.persist(ClientePFMapper.toEntity(clientePFDTO));
    }

    public void atualizaClientePF(Long id,ClientePFDTO clientePFDTO) {
        var clientePF = clientePFRepository.findById(id);
        if (clientePF != null) {
            clientePFRepository.persist(ClientePFMapper.toEntity(clientePFDTO));
        }
    }

    public void deletaClientePF(Long id) {
        clientePFRepository.deleteById(id);
    }

    public ClientePFDTO buscaClientePFPorId(Long id) {
        var clientePF = clientePFRepository.findById(id);
        if (clientePF != null) {
            return ClientePFMapper.toDTO(clientePF);
        }
        return null;
    }

    public List<ClientePFDTO> buscaTodosClientesPF() {
        return clientePFRepository.listAll()
                .stream()
                .map(ClientePFMapper::toDTO)
                .toList();
    }
}
