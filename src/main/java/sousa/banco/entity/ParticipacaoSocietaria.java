package sousa.banco.entity;

import jakarta.persistence.Column;
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

    @Column(name = "percentual_participacao", nullable = false)
    private BigDecimal percentualParticipacao;

    @Column(name = "data_entrada", nullable = false)
    private LocalDate dataEntrada;

    @Column(name = "tipo_participacao", nullable = false)
    private TipoParticipacaoSocietariaEnum tipoParticipacao;

    public ClientePJ getEmpresa() {
        return empresa;
    }

    public void setEmpresa(ClientePJ empresa) {
        this.empresa = empresa;
    }

    public ClientePF getSocio() {
        return socio;
    }

    public void setSocio(ClientePF socio) {
        this.socio = socio;
    }

    public ClientePJ getEmpresaSocia() {
        return empresaSocia;
    }

    public void setEmpresaSocia(ClientePJ empresaSocia) {
        this.empresaSocia = empresaSocia;
    }

    public BigDecimal getPercentualParticipacao() {
        return percentualParticipacao;
    }

    public void setPercentualParticipacao(BigDecimal percentualParticipacao) {
        this.percentualParticipacao = percentualParticipacao;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public TipoParticipacaoSocietariaEnum getTipoParticipacao() {
        return tipoParticipacao;
    }

    public void setTipoParticipacao(TipoParticipacaoSocietariaEnum tipoParticipacao) {
        this.tipoParticipacao = tipoParticipacao;
    }

    public ParticipacaoSocietaria(ClientePJ empresa, ClientePF socio, ClientePJ empresaSocia,
                                  BigDecimal percentualParticipacao, LocalDate dataEntrada,
                                  TipoParticipacaoSocietariaEnum tipoParticipacao) {
        this.empresa = empresa;
        this.socio = socio;
        this.empresaSocia = empresaSocia;
        this.percentualParticipacao = percentualParticipacao;
        this.dataEntrada = dataEntrada;
        this.tipoParticipacao = tipoParticipacao;
    }
}
