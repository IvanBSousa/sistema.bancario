package sousa.banco.entity;

import jakarta.persistence.*;
import sousa.banco.enums.RegimeTributarioEnum;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cliente_pj")
public class ClientePJ extends Cliente {

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(name = "data_constituicao", nullable = false)
    private LocalDate dataConstituicao;

    @Column(name = "regime_tributario", nullable = false)
    private RegimeTributarioEnum regimeTributario;

    @Column(name = "participacao_societaria", nullable = false)
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<ParticipacaoSocietaria> socios;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Faturamento> faturamento;

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public LocalDate getDataConstituicao() {
        return dataConstituicao;
    }

    public void setDataConstituicao(LocalDate dataConstituicao) {
        this.dataConstituicao = dataConstituicao;
    }

    public RegimeTributarioEnum getRegimeTributario() {
        return regimeTributario;
    }

    public void setRegimeTributario(RegimeTributarioEnum regimeTributario) {
        this.regimeTributario = regimeTributario;
    }

    public List<ParticipacaoSocietaria> getSocios() {
        return socios;
    }

    public void setSocios(List<ParticipacaoSocietaria> socios) {
        this.socios = socios;
    }

    public List<Faturamento> getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(List<Faturamento> faturamento) {
        this.faturamento = faturamento;
    }

    public ClientePJ(Long id, List<Endereco> endereco, List<Contato> contato, String razaoSocial, String nomeFantasia,
                     String cnpj, LocalDate dataConstituicao, RegimeTributarioEnum regimeTributario,
                     List<ParticipacaoSocietaria> socios, List<Faturamento> faturamento) {
        super(id, endereco, contato);
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.dataConstituicao = dataConstituicao;
        this.regimeTributario = regimeTributario;
        this.socios = socios;
        this.faturamento = faturamento;
    }

    public ClientePJ() {
    }
}
