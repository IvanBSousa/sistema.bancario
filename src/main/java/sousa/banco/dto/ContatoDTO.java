package sousa.banco.dto;

import sousa.banco.entity.Cliente;
import sousa.banco.enums.TipoContatoEnum;

public record ContatoDTO(
        TipoContatoEnum tipoContato,
        String contato,
        Cliente cliente
) {
}
