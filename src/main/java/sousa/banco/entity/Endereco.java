package sousa.banco.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import sousa.banco.enums.EndercoEnum;
import sousa.banco.enums.EstadosEnum;
import sousa.banco.enums.TipoEnderecoEnum;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private EndercoEnum logradouro;

    @Column(nullable = false)
    private String rua;

    private String numero;

    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private EstadosEnum estado;

    @Column(nullable = false)
    @Size(max = 8)
    private String cep;

    private TipoEnderecoEnum tipoEndereco;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EndercoEnum getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(EndercoEnum logradouro) {
        this.logradouro = logradouro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public EstadosEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadosEnum estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public TipoEnderecoEnum getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEnderecoEnum tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco(Long id, EndercoEnum logradouro, String rua, String numero, String complemento, String bairro,
                    String cidade, EstadosEnum estado, String cep, TipoEnderecoEnum tipoEndereco, Cliente cliente) {
        this.id = id;
        this.logradouro = logradouro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.tipoEndereco = tipoEndereco;
        this.cliente = cliente;
    }

    public Endereco() {
    }
}
