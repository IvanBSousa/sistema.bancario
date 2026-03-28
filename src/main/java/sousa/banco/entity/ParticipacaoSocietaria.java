package sousa.banco.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import sousa.banco.enums.TipoParticipacaoSocietariaEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ParticipacaoSocietaria {

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private ClientePJ empresa;

    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    private ClientePF socio;

    @ManyToOne
    @JoinColumn(name = "empresa_socia_id")
    private ClientePJ empresaSocia;

    private BigDecimal percentualParticipacao;

    private LocalDate dataEntrada;

    private TipoParticipacaoSocietariaEnum tipoParticipacao;
}
