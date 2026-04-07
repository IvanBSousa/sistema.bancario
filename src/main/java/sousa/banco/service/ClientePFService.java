package sousa.banco.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import sousa.banco.dto.ClientePFDTO;
import sousa.banco.entity.ClientePF;
import sousa.banco.entity.Contato;
import sousa.banco.entity.Endereco;
import sousa.banco.entity.Renda;
import sousa.banco.exception.NotFoundException;
import sousa.banco.mapper.*;
import sousa.banco.repository.ClientePFRepository;

import java.util.List;

@ApplicationScoped
public class ClientePFService {

    private final ClientePFRepository clientePFRepository;

    public ClientePFService(ClientePFRepository clientePFRepository) {
        this.clientePFRepository = clientePFRepository;
    }

    @Transactional
    public void criaClientePF(ClientePFDTO clientePFDTO) {

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

        return ClientePFMapper.toDTO(clientePF);
    }

    @Transactional
    public boolean deletaClientePF(Long id) {
        clientePFRepository.deleteById(id);
        return true;
    }

    public ClientePFDTO buscaClientePFPorId(Long id) {
        var clientePF = clientePFRepository.findById(id);
        if (clientePF == null) {
            throw new NotFoundException("ClientePF com ID " + id + " não encontrado.");
        }
        return ClientePFMapper.toDTO(clientePF);
    }

    public List<ClientePFDTO> buscaTodosClientesPF() {

        if (clientePFRepository.listAll().isEmpty()) {
            throw new NotFoundException("Nenhum ClientePF encontrado.");
        }
        return clientePFRepository.listAll()
                .stream()
                .map(ClientePFMapper::toDTO)
                .toList();
    }

    public ClientePFDTO buscaClientePFPorCPF(String cpf) {
        ClientePF clientePF = clientePFRepository.buscaPorCpf(cpf);
        if (clientePF == null) {
            throw new NotFoundException("ClientePF com CPF " + cpf + " não encontrado.");
        }
        return ClientePFMapper.toDTO(clientePF);
    }
}
