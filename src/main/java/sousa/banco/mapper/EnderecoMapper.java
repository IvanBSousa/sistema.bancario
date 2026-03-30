package sousa.banco.mapper;

import sousa.banco.dto.EnderecoDTO;
import sousa.banco.entity.ClientePF;
import sousa.banco.entity.Endereco;

public class EnderecoMapper {

    public static EnderecoDTO toDTO(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return new EnderecoDTO(
                endereco.getLogradouro(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getTipoEndereco()
        );
    }

    public static Endereco toEntity(EnderecoDTO enderecoDTO) {
        if (enderecoDTO == null) {
            return null;
        }
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.logradouro());
        endereco.setRua(enderecoDTO.rua());
        endereco.setNumero(enderecoDTO.numero());
        endereco.setComplemento(enderecoDTO.complemento());
        endereco.setBairro(enderecoDTO.bairro());
        endereco.setCidade(enderecoDTO.cidade());
        endereco.setEstado(enderecoDTO.estado());
        endereco.setCep(enderecoDTO.cep());
        endereco.setTipoEndereco(enderecoDTO.tipoEndereco());
        return endereco;
    }
}
