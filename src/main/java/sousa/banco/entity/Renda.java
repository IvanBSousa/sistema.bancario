package sousa.banco.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import sousa.banco.enums.DocumentoRendaEnum;

import java.math.BigDecimal;

@Entity
@Table(name = "renda")
public class Renda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "renda_mensal_bruta", nullable = true)
    @Positive(message = "A renda mensal bruta deve ser um valor positivo.")
    private BigDecimal rendaMensalBruta;

    @Column(name = "renda_mensal_liquida", nullable = true)
    @Positive(message = "A renda mensal líquida deve ser um valor positivo.")
    private BigDecimal rendaMensalLiquida;

    @Column(nullable = true)
    private BigDecimal descontos;

    @Column(name = "doc_fonte_pagadora", nullable = true)
    private String docFontePagadora;

    @Column(name = "nome_fonte_pagadora", nullable = true)
    private String nomeFontePagadora;

    @Column(name = "profissao", nullable = false)
    private String profissao;

    @Column(name = "tipo_documento_renda", nullable = true)
    private DocumentoRendaEnum tipoDocumentoRenda;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Long rendaClienteFK;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRendaMensalBruta() {
        return rendaMensalBruta;
    }

    public void setRendaMensalBruta(BigDecimal rendaMensalBruta) {
        this.rendaMensalBruta = rendaMensalBruta;
    }

    public BigDecimal getRendaMensalLiquida() {
        return rendaMensalLiquida;
    }

    public void setRendaMensalLiquida(BigDecimal rendaMensalLiquida) {
        this.rendaMensalLiquida = rendaMensalLiquida;
    }

    public BigDecimal getDescontos() {
        return descontos;
    }

    public void setDescontos(BigDecimal descontos) {
        this.descontos = descontos;
    }

    public String getDocFontePagadora() {
        return docFontePagadora;
    }

    public void setDocFontePagadora(String docFontePagadora) {
        this.docFontePagadora = docFontePagadora;
    }

    public String getNomeFontePagadora() {
        return nomeFontePagadora;
    }

    public void setNomeFontePagadora(String nomeFontePagadora) {
        this.nomeFontePagadora = nomeFontePagadora;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public DocumentoRendaEnum getTipoDocumentoRenda() {
        return tipoDocumentoRenda;
    }

    public void setTipoDocumentoRenda(DocumentoRendaEnum tipoDocumentoRenda) {
        this.tipoDocumentoRenda = tipoDocumentoRenda;
    }

    public Long getRendaClienteFK() {
        return rendaClienteFK;
    }

    public void setRendaClienteFK(Long rendaClienteFK) {
        this.rendaClienteFK = rendaClienteFK;
    }

    public Renda(Long id, BigDecimal rendaMensalBruta, BigDecimal rendaMensalLiquida, BigDecimal descontos,
                 String docFontePagadora, String nomeFontePagadora, String profissao,
                 DocumentoRendaEnum tipoDocumentoRenda, Long cliente) {
        this.id = id;
        this.rendaMensalBruta = rendaMensalBruta;
        this.rendaMensalLiquida = rendaMensalLiquida;
        this.descontos = descontos;
        this.docFontePagadora = docFontePagadora;
        this.nomeFontePagadora = nomeFontePagadora;
        this.profissao = profissao;
        this.tipoDocumentoRenda = tipoDocumentoRenda;
        this.rendaClienteFK = cliente;
    }

    public Renda() {
    }

}
