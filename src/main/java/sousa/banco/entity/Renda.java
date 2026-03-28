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

    @Positive(message = "A renda mensal bruta deve ser um valor positivo.")
    private BigDecimal rendaMensalBruta;

    @Positive(message = "A renda mensal líquida deve ser um valor positivo.")
    private BigDecimal rendaMensalLiquida;

    private BigDecimal descontos;

    private String docFontePagadora;

    private String nomeFontePagadora;

    private String profissao;

    private DocumentoRendaEnum tipoDocumentoRenda;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClientePF cliente;

}
