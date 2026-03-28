package sousa.banco.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "faturamento")
public class Faturamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "O faturamento anual deve ser um valor positivo.")
    private BigDecimal faturamtentoAnual;

    @Positive(message = "O faturamento dos últimos 12 meses deve ser um valor positivo.")
    private BigDecimal faturamento12Meses;

    private LocalDate anoBase;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private ClientePJ empresa;

}
