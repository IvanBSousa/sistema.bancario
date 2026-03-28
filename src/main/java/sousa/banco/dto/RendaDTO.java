package sousa.banco.dto;

import sousa.banco.enums.DocumentoRendaEnum;

import java.math.BigDecimal;

public record RendaDTO(
        BigDecimal rendaMensalBruta,
        BigDecimal rendaMensalLiquida,
        BigDecimal descontos,
        String docFontePagadora,
        String nomeFontePagadora,
        String profissao,
        DocumentoRendaEnum tipoDocumentoRenda
) {
}
