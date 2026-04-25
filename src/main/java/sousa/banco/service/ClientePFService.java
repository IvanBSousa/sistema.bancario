package sousa.banco.service;

import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import sousa.banco.clientcpf.ConsultaCPF;
import sousa.banco.dto.ClientePFDTO;
import sousa.banco.dto.ConsultaCPFResponseDTO;
import sousa.banco.dto.DocumentoDTO;
import sousa.banco.entity.*;
import sousa.banco.enums.TipoEnderecoEnum;
import sousa.banco.exception.ConflictException;
import sousa.banco.exception.NotFoundException;
import sousa.banco.mapper.*;
import sousa.banco.repository.ClientePFRepository;

import java.util.List;

@ApplicationScoped
public class ClientePFService {

    private final ClientePFRepository clientePFRepository;
    private final ConsultaCPF consultaCPF;

    @ConfigProperty(name = "apicpf.api.key")
    String apiKey;

    public ClientePFService(ClientePFRepository clientePFRepository,
                            ConsultaCPF consultaCPF) {
        this.clientePFRepository = clientePFRepository;
        this.consultaCPF = consultaCPF;
    }

    @Transactional
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @CacheInvalidateAll(cacheName = "clientePFPorId")
    @CacheInvalidateAll(cacheName = "clientesPFTodos")
    @CacheInvalidateAll(cacheName = "clientePFPorCpf")
    public void criaClientePF(ClientePFDTO clientePFDTO) {

        ClientePF clienteExistente = clientePFRepository.buscaPorCpf(clientePFDTO.cpf());
        if (clienteExistente != null) {
            throw new ConflictException("ClientePF com CPF " + clientePFDTO.cpf() + " já existe.");
        }

        ClientePF clientePF = ClientePFMapper.toEntity(clientePFDTO);

        if (clientePFDTO.endereco() != null) {
            clientePFDTO.endereco().forEach(eDto -> {
                Endereco endereco = EnderecoMapper.toEntity(eDto);
                clientePF.addEndereco(endereco);
            });
        }

        if (clientePFDTO.contato() != null) {
            clientePFDTO.contato().forEach(cDto -> {
                Contato contato = ContatoMapper.toEntity(cDto);
                clientePF.addContato(contato);
            });
        }

        if (clientePFDTO.renda() != null) {
            clientePFDTO.renda().forEach(rDto -> {
                Renda renda = RendaMapper.toEntity(rDto);
                clientePF.addRenda(renda);
            });
        }

        if (clientePFDTO.documentos() != null) {
            clientePFDTO.documentos().forEach(dDto -> {
                var documento = DocumentoMapper.toEntity(dDto);
                clientePF.addDocumento(documento);
            });
        }

        clientePFRepository.persist(clientePF);
    }

    @Transactional
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @CacheInvalidateAll(cacheName = "clientePFPorId")
    @CacheInvalidateAll(cacheName = "clientesPFTodos")
    @CacheInvalidateAll(cacheName = "clientePFPorCpf")
    public ClientePFDTO atualizaClientePF(Long id, ClientePFDTO clientePFDTO) {
        ClientePF clientePF = clientePFRepository.findById(id);
        if (clientePF == null) {
            throw new NotFoundException("ClientePF com ID " + id + " não encontrado.");
        }

        clientePF.setNomeCompleto(clientePFDTO.nomeCompleto());
        clientePF.setDataNascimento(clientePFDTO.dataNascimento());
        clientePF.setNacionalidade(clientePFDTO.nacionalidade());
        clientePF.setEstadoCivil(clientePFDTO.estadoCivil());
        clientePF.setCpfConjuge(clientePFDTO.cpfConjuge());
        clientePF.setNomeConjuge(clientePFDTO.nomeConjuge());

        if (clientePFDTO.endereco() != null) {
            long countResidencialExistentes = clientePF.getEndereco().stream()
                    .filter(e -> e.getTipoEndereco() == TipoEnderecoEnum.RESIDENCIAL)
                    .count();

            long countResidencialNovos = clientePFDTO.endereco().stream()
                    .filter(e -> e.tipoEndereco() == TipoEnderecoEnum.RESIDENCIAL)
                    .count();

            if (countResidencialExistentes + countResidencialNovos > 1) {
                throw new ConflictException("O cliente não pode ter mais de um endereço residencial.");
            }
        }

            clientePFDTO.endereco().forEach(eDto -> {
                Endereco endereco = EnderecoMapper.toEntity(eDto);
                clientePF.addEndereco(endereco);
            });


        if (clientePFDTO.contato() != null) {
            clientePFDTO.contato().forEach(cDto -> {
                Contato contato = ContatoMapper.toEntity(cDto);
                clientePF.addContato(contato);
            });
        }

        if (clientePFDTO.renda() != null) {
            clientePFDTO.renda().forEach(rDto -> {
                Renda renda = RendaMapper.toEntity(rDto);
                clientePF.addRenda(renda);
            });
        }

        if (clientePFDTO.documentos() != null) {

            List<String> docExistente = clientePF.getDocumentos()
                    .stream()
                    .map(Documento::getNumeroDocumento)
                    .toList();
            String docNovo = clientePFDTO.documentos()
                    .stream()
                    .map(DocumentoDTO::numeroDocumento)
                    .toString();

            if (docExistente.contains(docNovo)) {
                throw new ConflictException("Este documento já existe!");
            }

            clientePFDTO.documentos().forEach(dDto -> {
                var documento = DocumentoMapper.toEntity(dDto);
                clientePF.addDocumento(documento);
            });
        }

        return ClientePFMapper.toDTO(clientePF);
    }

    @Transactional
    @RolesAllowed({"ROLE_ADMIN"})
    @SecurityRequirement(name = "Keycloak")
    @CacheInvalidateAll(cacheName = "clientePFPorId")
    @CacheInvalidateAll(cacheName = "clientesPFTodos")
    @CacheInvalidateAll(cacheName = "clientePFPorCpf")
    public boolean deletaClientePF(Long id) {
        clientePFRepository.deleteById(id);
        return true;
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @CacheResult(cacheName = "clientePFPorId")
    public ClientePFDTO buscaClientePFPorId(Long id) {
        var clientePF = clientePFRepository.findById(id);
        if (clientePF == null) {
            throw new NotFoundException("ClientePF com ID " + id + " não encontrado.");
        }
        return ClientePFMapper.toDTO(clientePF);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @CacheResult(cacheName = "clientesPFTodos")
    public List<ClientePFDTO> buscaTodosClientesPF() {

        if (clientePFRepository.listAll().isEmpty()) {
            throw new NotFoundException("Nenhum ClientePF encontrado.");
        }
        return clientePFRepository.listAll()
                .stream()
                .map(ClientePFMapper::toDTO)
                .toList();
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Keycloak")
    @CacheResult(cacheName = "clientePFPorCpf")
    public ClientePFDTO buscaClientePFPorCPF(String cpf) {
        ClientePF clientePF = clientePFRepository.buscaPorCpf(cpf);
        if (clientePF == null) {
            throw new NotFoundException("ClientePF com CPF " + cpf + " não encontrado.");
        }
        return ClientePFMapper.toDTO(clientePF);
    }

    public ConsultaCPFResponseDTO buscaDadosCPF(String cpf) {
        return consultaCPF.consultaCPF(cpf);
    }
}
