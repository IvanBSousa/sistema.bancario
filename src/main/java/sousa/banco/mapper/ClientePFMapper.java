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
}
