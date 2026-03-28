package sousa.banco.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Table(name = "contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<String> telefone;

    @Email
    private List<String> email;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
