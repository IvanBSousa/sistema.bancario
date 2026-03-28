package sousa.banco.dto;

import sousa.banco.entity.ClientePF;
import sousa.banco.enums.EstadosEnum;
import sousa.banco.enums.TipoDocumentoEnum;

import java.time.LocalDate;

public record DocumentoDTO(
        TipoDocumentoEnum tipoDocumento,
        String numeroDocumento,
        String orgaoEmissor,
        EstadosEnum estadoEmissor,
        LocalDate dataEmissao,
        LocalDate dataValidade,
        ClientePF cliente
) {
}
