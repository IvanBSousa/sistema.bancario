package sousa.banco.entity;

import jakarta.persistence.*;
import sousa.banco.enums.EstadosEnum;
import sousa.banco.enums.TipoDocumentoEnum;

import java.time.LocalDate;

@Entity
@Table(name = "documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoDocumentoEnum tipoDocumento;

    private String numeroDocumento;

    private String orgaoEmissor;

    private EstadosEnum estadoEmissor;

    private LocalDate dataEmissao;

    private LocalDate dataValidade;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
