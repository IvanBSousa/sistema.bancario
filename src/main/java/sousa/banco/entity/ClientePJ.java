package sousa.banco.entity;

import jakarta.persistence.*;
import sousa.banco.enums.RegimeTributarioEnum;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cliente_pj")
public class ClientePJ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razaoSocial;

    private String nomeFantasia;

    private String cnpj;

    private LocalDate dataConstituicao;

    private RegimeTributarioEnum regimeTributario;

    @OneToMany(mappedBy = "clientePJ", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> endereco;

    @OneToMany(mappedBy = "clientePJ", cascade = CascadeType.ALL)
    private List<ParticipacaoSocietaria> socios;

    @OneToMany(mappedBy = "clientePJ", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Faturamento> faturamento;
}
