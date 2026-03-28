package sousa.banco.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FaturamentoDTO(
        BigDecimal faturamentoAnual,
        BigDecimal faturamento12Meses,
        LocalDate anoBase
) {
}
