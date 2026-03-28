package sousa.banco.mapper;

import sousa.banco.dto.FaturamentoDTO;
import sousa.banco.entity.Faturamento;

public class FaturamentoMapper {

    public static FaturamentoDTO toDTO(Faturamento faturamento) {
        if (faturamento == null) {
            return null;
        }
        return new FaturamentoDTO(
                faturamento.getFaturamentoAnual(),
                faturamento.getFaturamento12Meses(),
                faturamento.getAnoBase(),
                faturamento.getEmpresa()
        );
    }

    public static Faturamento toEntity(FaturamentoDTO faturamentoDTO) {
        if (faturamentoDTO == null) {
            return null;
        }

        Faturamento faturamento = new Faturamento();
        faturamento.setFaturamentoAnual(faturamentoDTO.faturamentoAnual());
        faturamento.setFaturamento12Meses(faturamentoDTO.faturamento12Meses());
        faturamento.setAnoBase(faturamentoDTO.anoBase());
        faturamento.setEmpresa(faturamentoDTO.empresa());
        return faturamento;

    }
}
