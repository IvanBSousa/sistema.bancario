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

    private EndercoEnum logradouro;

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private EstadosEnum estado;

    @Size(max = 8)
    private String cep;

    private TipoEnderecoEnum tipoEndereco;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
