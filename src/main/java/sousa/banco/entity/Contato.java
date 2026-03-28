package sousa.banco.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import sousa.banco.enums.TipoContatoEnum;

import java.util.List;

@Entity
@Table(name = "contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private TipoContatoEnum tipoContato;

    private String contato;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoContatoEnum getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContatoEnum tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Contato(Long id, TipoContatoEnum tipoContato, String contato, Cliente cliente) {
        this.id = id;
        this.tipoContato = tipoContato;
        this.contato = contato;
        this.cliente = cliente;
    }

    public Contato() {
    }
}
