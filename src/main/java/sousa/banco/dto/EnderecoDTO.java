package sousa.banco.dto;

import sousa.banco.entity.Cliente;
import sousa.banco.enums.EndercoEnum;
import sousa.banco.enums.EstadosEnum;
import sousa.banco.enums.TipoEnderecoEnum;

public record EnderecoDTO(
        EndercoEnum logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        EstadosEnum estado,
        String cep,
        TipoEnderecoEnum tipoEndereco,
        Cliente cliente
) {
}
