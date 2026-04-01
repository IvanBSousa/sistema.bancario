package sousa.banco.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import sousa.banco.dto.ClientePJDTO;
import sousa.banco.entity.*;
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
    public void criaClientePJ(ClientePJDTO clientePJDTO) {

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
    public ClientePJDTO atualizaClientePJ(Long id, ClientePJDTO clientePJDTO) {
        ClientePJ clientePJ = clientePJRepository.findById(id);
        if (clientePJ == null) {
            throw new RuntimeException("Cliente PJ não encontrado");
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
            //clientePJ.getSocios().clear();
            clientePJDTO.socios().forEach(sDto -> {
                ParticipacaoSocietaria socio = ParticipacaoSocietariaMapper.toEntity(sDto);
                clientePJ.addSocio(socio);
            });
        }

        return ClientePJMapper.toDTO(clientePJ);
    }

    @Transactional
    public boolean deletaClientePJ(Long id) {
        clientePJRepository.deleteById(id);
        return true;
    }

    public ClientePJDTO buscaClientePJPorId(Long id) {
        ClientePJ clientePJ = clientePJRepository.findById(id);
        if (clientePJ != null) {
            return ClientePJMapper.toDTO(clientePJ);
        } else {
            throw new RuntimeException("Cliente PJ não encontrado");
        }
    }

    public List<ClientePJDTO> buscaTodosClientesPJ() {
        return clientePJRepository.listAll()
                .stream()
                .map(ClientePJMapper::toDTO)
                .toList();
    }
}
