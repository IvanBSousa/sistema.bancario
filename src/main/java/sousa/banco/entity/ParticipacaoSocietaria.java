package sousa.banco.entity;

import jakarta.persistence.*;
import sousa.banco.enums.TipoParticipacaoSocietariaEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "participacao_societaria")
public class ParticipacaoSocietaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpfCnpj;

    private String nomeRazaoSocial;

    @Column(name = "percentual_participacao", nullable = false)
    private BigDecimal percentualParticipacao;

    @Column(name = "data_entrada", nullable = false)
    private LocalDate dataEntrada;

    @Column(name = "tipo_participacao", nullable = false)
    private TipoParticipacaoSocietariaEnum tipoParticipacao;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private ClientePJ empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
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

    public ClientePJ getEmpresa() {
        return empresa;
    }

    public void setEmpresa(ClientePJ empresa) {
        this.empresa = empresa;
    }

    public ParticipacaoSocietaria(Long id, String cpfCnpj, String nomeRazaoSocial, BigDecimal percentualParticipacao,
                                  LocalDate dataEntrada, TipoParticipacaoSocietariaEnum tipoParticipacao,
                                  ClientePJ empresa) {
        this.id = id;
        this.cpfCnpj = cpfCnpj;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.percentualParticipacao = percentualParticipacao;
        this.dataEntrada = dataEntrada;
        this.tipoParticipacao = tipoParticipacao;
        this.empresa = empresa;
    }

    public ParticipacaoSocietaria() {
    }
}
