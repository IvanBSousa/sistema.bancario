package sousa.banco.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "faturamento")
public class Faturamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "faturamento_anual", nullable = true)
    @Positive(message = "O faturamento anual deve ser um valor positivo.")
    private BigDecimal faturamentoAnual;

    @Column(name = "faturamento_12_meses", nullable = true)
    @Positive(message = "O faturamento dos últimos 12 meses deve ser um valor positivo.")
    private BigDecimal faturamento12Meses;

    private LocalDate anoBase;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private ClientePJ empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFaturamentoAnual() {
        return faturamentoAnual;
    }

    public void setFaturamentoAnual(BigDecimal faturamentoAnual) {
        this.faturamentoAnual = faturamentoAnual;
    }

    public BigDecimal getFaturamento12Meses() {
        return faturamento12Meses;
    }

    public void setFaturamento12Meses(BigDecimal faturamento12Meses) {
        this.faturamento12Meses = faturamento12Meses;
    }

    public LocalDate getAnoBase() {
        return anoBase;
    }

    public void setAnoBase(LocalDate anoBase) {
        this.anoBase = anoBase;
    }

    public ClientePJ getEmpresa() {
        return empresa;
    }

    public void setEmpresa(ClientePJ empresa) {
        this.empresa = empresa;
    }

    public Faturamento(Long id, BigDecimal faturamentoAnual, BigDecimal faturamento12Meses, LocalDate anoBase,
                       ClientePJ empresa) {
        this.id = id;
        this.faturamentoAnual = faturamentoAnual;
        this.faturamento12Meses = faturamento12Meses;
        this.anoBase = anoBase;
        this.empresa = empresa;
    }

    public Faturamento(){
    }
}
