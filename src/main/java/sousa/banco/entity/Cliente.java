package sousa.banco.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @OneToMany(mappedBy = "endClienteFK", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> endereco;

    @Column(nullable = false)
    @OneToMany(mappedBy ="contatoClienteFK",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public List<Contato> getContato() {
        return contato;
    }

    public void setContato(List<Contato> contato) {
        this.contato = contato;
    }

    public Cliente(Long id, List<Endereco> endereco, List<Contato> contato) {
        this.id = id;
        this.endereco = endereco;
        this.contato = contato;
    }

    public Cliente() {
    }

    public void addEndereco(Endereco endereco) {
        endereco.setEndClienteFK(this);
        this.endereco.add(endereco);
    }

    public void addContato(Contato contato) {
        contato.setContato(this.toString());
        this.contato.add(contato);
    }
}
