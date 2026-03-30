package sousa.banco.entity;

import jakarta.persistence.*;
import sousa.banco.enums.EstadoCivilEnum;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente_pf", uniqueConstraints = {
        @UniqueConstraint(columnNames = "CPF")
})
public class ClientePF extends Cliente {

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    @OneToMany(mappedBy = "docClienteFK", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos = new ArrayList<>();

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String nacionalidade;

    @Column(name = "estado_civil", nullable = false)
    private EstadoCivilEnum estadoCivil;

    @Column(name = "cpf_conjuge", nullable = true)
    private String cpfConjuge;

    @Column(name = "nome_conjuge", nullable = true)
    private String nomeConjuge;

    @OneToMany(mappedBy = "rendaClienteFK", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Renda> renda = new ArrayList<>();

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) { this.documentos = documentos; }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public EstadoCivilEnum getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNomeConjuge() { return nomeConjuge;}

    public void setNomeConjuge(String nomeConjuge) { this.nomeConjuge = nomeConjuge; }

    public String getCpfConjuge() {
        return cpfConjuge;
    }

    public void setCpfConjuge(String cpfConjuge) {
        this.cpfConjuge = cpfConjuge;
    }

    public List<Renda> getRenda() {
        return renda;
    }

    public void setRenda(List<Renda> renda) {
        this.renda = renda;
    }

    public ClientePF(Long id, List<Endereco> endereco, List<Contato> contato, String nomeCompleto, String cpf,
                     List<Documento> documentos, LocalDate dataNascimento, String nacionalidade,
                     EstadoCivilEnum estadoCivil, String nomeConjuge, String cpfConjuge,List<Renda> renda) {
        super(id, endereco, contato);
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.documentos = documentos;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.estadoCivil = estadoCivil;
        this.nomeConjuge = nomeConjuge;
        this.cpfConjuge = cpfConjuge;
        this.renda = renda;
    }

    public ClientePF() {
    }

    public void addRenda(Renda renda) {
        renda.setRendaClienteFK(this);
        this.renda.add(renda);
    }

    public void addDocumento(Documento documento) {
        documento.setDocClienteFK(this);
        this.documentos.add(documento);
    }

}
