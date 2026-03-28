package sousa.banco.entity;

import jakarta.persistence.*;
import sousa.banco.enums.EstadoCivilEnum;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cliente_pf", uniqueConstraints = {
        @UniqueConstraint(columnNames = "CPF"),
        @UniqueConstraint(columnNames = "documentos_numeroDocumento")
})
public class ClientePF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private String CPF;

    @OneToMany(mappedBy = "clientes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos;

    private LocalDate dataNascimento;

    private String nacionalidade;

    private EstadoCivilEnum estadoCivil;

    @ManyToOne
    @JoinColumn(name = "conjuge_id")
    private ClientePF conjuge;

    @OneToMany(mappedBy = "clientes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Renda> renda;

    @OneToMany(mappedBy = "clientes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> endereco;

    @OneToMany(mappedBy ="clientes",cascade = CascadeType.ALL, orphanRemoval = true)
    private Contato contato;

}
