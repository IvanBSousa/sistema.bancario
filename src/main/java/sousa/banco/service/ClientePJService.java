package sousa.banco.service;

import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import sousa.banco.dto.ClientePJDTO;
import sousa.banco.entity.*;
import sousa.banco.exception.ConflictException;
import sousa.banco.exception.NotFoundException;
import sousa.banco.mapper.*;
import sousa.banco.repository.ClientePJRepository;

import java.util.List;

@ApplicationScoped
public class ClientePJService {

    private final ClientePJRepository clientePJRepository;

    public ClientePJService(ClientePJRepository clientePJRepository) {
        this.clientePJRepository = clientePJRepository;
    }

    @Transactional
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @CacheInvalidateAll(cacheName = "clientePJPorId")
    @CacheInvalidateAll(cacheName = "clientesPJTodos")
    @CacheInvalidateAll(cacheName = "clientePJPorCnpj")
    public void criaClientePJ(ClientePJDTO clientePJDTO) {
        ClientePJ clienteExistente = clientePJRepository.buscaPorCnpj(clientePJDTO.cnpj());
        if (clienteExistente != null) {
            throw new ConflictException("ClientePJ com CNPJ " + clientePJDTO.cnpj() + " já existe.");
        }

        ClientePJ clientePJ = ClientePJMapper.toEntity(clientePJDTO);

        if (clientePJDTO.endereco() != null) {
            clientePJDTO.endereco().forEach(eDto -> {
                Endereco endereco = EnderecoMapper.toEntity(eDto);
                clientePJ.addEndereco(endereco);
            });
        }

        if (clientePJDTO.contato() != null) {
            clientePJDTO.contato().forEach(cDto -> {
                Contato contato = ContatoMapper.toEntity(cDto);
                clientePJ.addContato(contato);
            });
        }

        if (clientePJDTO.faturamento() != null) {
            clientePJDTO.faturamento().forEach(fDto -> {
                Faturamento faturamento = FaturamentoMapper.toEntity(fDto);
                clientePJ.addFaturamento(faturamento);
            });
        }

        if (clientePJDTO.socios() != null) {
            clientePJDTO.socios().forEach(sDto -> {
                ParticipacaoSocietaria socio = ParticipacaoSocietariaMapper.toEntity(sDto);
                clientePJ.addSocio(socio);
            });
        }

        clientePJRepository.persist(clientePJ);
    }

    @Transactional
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @CacheInvalidateAll(cacheName = "clientePJPorId")
    @CacheInvalidateAll(cacheName = "clientesPJTodos")
    @CacheInvalidateAll(cacheName = "clientePJPorCnpj")
    public ClientePJDTO atualizaClientePJ(Long id, ClientePJDTO clientePJDTO) {
        ClientePJ clientePJ = clientePJRepository.findById(id);
        if (clientePJ == null) {
            throw new NotFoundException("Cliente PJ com ID " + id + " não encontrado");
        }

        clientePJ.setRazaoSocial(clientePJDTO.razaoSocial());
        clientePJ.setNomeFantasia(clientePJDTO.nomeFantasia());
        clientePJ.setDataConstituicao(clientePJDTO.dataConstituicao());
        clientePJ.setRegimeTributario(clientePJDTO.regimeTributario());

        if (clientePJDTO.endereco() != null) {
            clientePJDTO.endereco().forEach(eDto -> {
                Endereco endereco = EnderecoMapper.toEntity(eDto);
                clientePJ.addEndereco(endereco);
            });
        }

        if (clientePJDTO.contato() != null) {
            clientePJDTO.contato().forEach(cDto -> {
                Contato contato = ContatoMapper.toEntity(cDto);
                clientePJ.addContato(contato);
            });
        }

        if (clientePJDTO.faturamento() != null) {
            clientePJDTO.faturamento().forEach(fDto -> {
                Faturamento faturamento = FaturamentoMapper.toEntity(fDto);
                clientePJ.addFaturamento(faturamento);
            });
        }

        if (clientePJDTO.socios() != null) {
            clientePJ.getSocios().clear();
            clientePJDTO.socios().forEach(sDto -> {
                ParticipacaoSocietaria socio = ParticipacaoSocietariaMapper.toEntity(sDto);
                clientePJ.addSocio(socio);
            });
        }

        return ClientePJMapper.toDTO(clientePJ);
    }

    @Transactional
    @RolesAllowed({"ROLE_ADMIN"})
    @SecurityRequirement(name = "Keycloak")
    @CacheInvalidateAll(cacheName = "clientePJPorId")
    @CacheInvalidateAll(cacheName = "clientesPJTodos")
    @CacheInvalidateAll(cacheName = "clientePJPorCnpj")
    public boolean deletaClientePJ(Long id) {
        var ClientePJ =  clientePJRepository.findById(id);
        if (ClientePJ == null) {
            throw new NotFoundException("Cliente PJ com ID " + id + " não encontrado");
        }
        clientePJRepository.deleteById(id);
        return true;
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @CacheResult(cacheName = "clientePJPorId")
    public ClientePJDTO buscaClientePJPorId(Long id) {
        ClientePJ clientePJ = clientePJRepository.findById(id);
        if (clientePJ == null) {
            throw new NotFoundException("Cliente PJ com ID " + id + " não encontrado");
        }
        return ClientePJMapper.toDTO(clientePJ);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @CacheResult(cacheName = "clientePJTodos")
    public List<ClientePJDTO> buscaTodosClientesPJ() {
        List<ClientePJ> clientesPJ = clientePJRepository.listAll();

        if (clientesPJ.isEmpty()) {
            throw new NotFoundException("Nenhum Cliente PJ encontrado.");
        }

        return clientesPJ.stream()
                .map(ClientePJMapper::toDTO)
                .toList();
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @CacheResult(cacheName = "clientePJPorCnpj")
    public ClientePJDTO buscaClientePJPorCnpj(String cnpj) {
        ClientePJ clientePJ = clientePJRepository.buscaPorCnpj(cnpj);
        if (clientePJ == null) {
            throw new NotFoundException("Cliente PJ com CNPJ " + cnpj + " não encontrado.");
        }
        return ClientePJMapper.toDTO(clientePJ);
    }
}
