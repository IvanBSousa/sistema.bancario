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

    @Column(name = "tipo_documento", nullable = false)
    private TipoDocumentoEnum tipoDocumento;

    @Column(name = "numero_documento", nullable = false)
    private String numeroDocumento;

    @Column(name = "orgao_emissor", nullable = false)
    private String orgaoEmissor;

    @Column(name = "estado_emissor", nullable = false)
    private EstadosEnum estadoEmissor;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClientePF docClienteFK;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDocumentoEnum getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public EstadosEnum getEstadoEmissor() {
        return estadoEmissor;
    }

    public void setEstadoEmissor(EstadosEnum estadoEmissor) {
        this.estadoEmissor = estadoEmissor;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public ClientePF getDocClienteFK() {
        return docClienteFK;
    }

    public void setDocClienteFK(ClientePF docClienteFK) {
        this.docClienteFK = docClienteFK;
    }

    public Documento(Long id, TipoDocumentoEnum tipoDocumento, String numeroDocumento, String orgaoEmissor,
                     EstadosEnum estadoEmissor, LocalDate dataEmissao, LocalDate dataValidade, ClientePF docClienteFK) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.orgaoEmissor = orgaoEmissor;
        this.estadoEmissor = estadoEmissor;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.docClienteFK = docClienteFK;
    }

    public Documento() {
    }
}
