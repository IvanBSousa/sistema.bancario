package sousa.banco.dto;

import sousa.banco.enums.TipoContatoEnum;

public record ContatoDTO(
        TipoContatoEnum tipoContato,
        String contato
) {
}
