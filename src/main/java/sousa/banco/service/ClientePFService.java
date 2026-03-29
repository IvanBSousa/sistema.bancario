package sousa.banco.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import sousa.banco.dto.ClientePFDTO;
import sousa.banco.dto.ContatoDTO;
import sousa.banco.dto.EnderecoDTO;
import sousa.banco.dto.RendaDTO;
import sousa.banco.entity.ClientePF;
import sousa.banco.entity.Contato;
import sousa.banco.entity.Endereco;
import sousa.banco.entity.Renda;
import sousa.banco.mapper.ClientePFMapper;
import sousa.banco.mapper.ContatoMapper;
import sousa.banco.mapper.EnderecoMapper;
import sousa.banco.mapper.RendaMapper;
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

        ClientePF cliente = ClientePFMapper.toEntity(clientePFDTO);
        cliente.setNomeCompleto(clientePFDTO.nomeCompleto());
        cliente.setCpf(clientePFDTO.cpf());
        cliente.setDataNascimento(clientePFDTO.dataNascimento());
        cliente.setNacionalidade(clientePFDTO.nacionalidade());
        cliente.setEstadoCivil(clientePFDTO.estadoCivil());
        cliente.setCpfConjuge(clientePFDTO.cpfConjuge());
        cliente.setNomeConjuge(clientePFDTO.nomeConjuge());
        clientePFDTO.endereco().forEach(eDto -> {
            Endereco e = EnderecoMapper.toEntity(eDto);
            cliente.addEndereco(e);
        });

        for (ContatoDTO cDto : clientePFDTO.contato()) {
            Contato contato = ContatoMapper.toEntity(cDto);
            cliente.addContato(contato); // 👈 AQUI
        }

//        for (RendaDTO rDto : clientePFDTO.renda()) {
//            Renda renda = RendaMapper.toEntity(rDto);
//            cliente.addRenda(renda); // 👈 AQUI
//        }

        clientePFRepository.persist(cliente);
    }

    @Transactional
    public void atualizaClientePF(Long id,ClientePFDTO clientePFDTO) {
        var clientePF = clientePFRepository.findById(id);
        if (clientePF != null) {
            clientePFRepository.persist(ClientePFMapper.toEntity(clientePFDTO));
        }
    }

    @Transactional
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
