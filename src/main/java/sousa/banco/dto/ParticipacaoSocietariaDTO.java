package sousa.banco.dto;

import sousa.banco.entity.ClientePF;
import sousa.banco.entity.ClientePJ;
import sousa.banco.enums.TipoParticipacaoSocietariaEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ParticipacaoSocietariaDTO(
        String cpfCnpj,
        String nomeRazaoSocial,
        BigDecimal percentualParticipacao,
        LocalDate dataEntrada,
        TipoParticipacaoSocietariaEnum tipoParticipacao
) {
}
