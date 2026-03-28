package sousa.banco.mapper;

import sousa.banco.dto.ClientePFDTO;
import sousa.banco.entity.ClientePF;

public class ClientePFMapper {

    public static ClientePFDTO toDTO(ClientePF clientePF) {
        if (clientePF == null) {
            return null;
        }
        return new ClientePFDTO(
                clientePF.getNomeCompleto(),
                clientePF.getCpf(),
                clientePF.getDocumentos()
                        .stream()
                        .map(DocumentoMapper::toDTO)
                        .toList(),
                clientePF.getDataNascimento(),
                clientePF.getNacionalidade(),
                clientePF.getEstadoCivil(),
                clientePF.getConjuge(),
                clientePF.getRenda()
                        .stream()
                        .map(RendaMapper::toDTO)
                        .toList(),
                clientePF.getEndereco()
                        .stream()
                        .map(EnderecoMapper::toDTO)
                        .toList(),
                clientePF.getContato()
                        .stream()
                        .map(ContatoMapper::toDTO)
                        .toList()
        );
    }

    public static ClientePF toEntity(ClientePFDTO clientePFDTO) {
        if (clientePFDTO == null) {
            return null;
        }
        ClientePF clientePF = new ClientePF();
        clientePF.setNomeCompleto(clientePFDTO.nomeCompleto());
        clientePF.setCpf(clientePFDTO.cpf());
        clientePF.setDocumentos(
                clientePFDTO.documentos()
                        .stream()
                        .map(DocumentoMapper::toEntity)
                        .toList()
        );
        clientePF.setDataNascimento(clientePFDTO.dataNascimento());
        clientePF.setNacionalidade(clientePFDTO.nacionalidade());
        clientePF.setEstadoCivil(clientePFDTO.estadoCivil());
        clientePF.setConjuge(clientePFDTO.conjuge());
        clientePF.setRenda(
                clientePFDTO.renda()
                        .stream()
                        .map(RendaMapper::toEntity)
                        .toList()
        );
        clientePF.setEndereco(
                clientePFDTO.endereco()
                        .stream()
                        .map(EnderecoMapper::toEntity)
                        .toList()
        );
        clientePF.setContato(
                clientePFDTO.contato()
                        .stream()
                        .map(ContatoMapper::toEntity)
                        .toList()
        );
        return  clientePF;
    }
}
