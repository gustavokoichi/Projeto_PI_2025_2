package pe.senac.br.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "LoteSemente")
public class LoteSemente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLote_Semente")
    private Integer idLoteSemente;

    @Column(name = "data_producao", nullable = false)
    private LocalDate dataProducao;

    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;

    @Column(name = "qtd_original_kg", nullable = false, precision = 7, scale = 3)
    private BigDecimal qtdOriginalKg;

    @Column(name = "certificado_origem", nullable = false, length = 45)
    private String certificadoOrigem;

    @Column(name = "local_armazenamento", nullable = false, length = 45)
    private String localArmazenamento;

    @Column(name = "qtd_disponivel_kg", nullable = false, precision = 7, scale = 3)
    private BigDecimal qtdDisponivelKg;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "Semente_idSemente", nullable = false)
    private Semente semente;

    @ManyToOne
    @JoinColumn(name = "Armazem_idArmazenamento", nullable = false)
    private Armazem armazem;

    public LoteSemente() {
    }

    public Integer getIdLoteSemente() {
        return idLoteSemente;
    }

    public void setIdLoteSemente(Integer idLoteSemente) {
        this.idLoteSemente = idLoteSemente;
    }

    public LocalDate getDataProducao() {
        return dataProducao;
    }

    public void setDataProducao(LocalDate dataProducao) {
        this.dataProducao = dataProducao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public BigDecimal getQtdOriginalKg() {
        return qtdOriginalKg;
    }

    public void setQtdOriginalKg(BigDecimal qtdOriginalKg) {
        this.qtdOriginalKg = qtdOriginalKg;
    }

    public String getCertificadoOrigem() {
        return certificadoOrigem;
    }

    public void setCertificadoOrigem(String certificadoOrigem) {
        this.certificadoOrigem = certificadoOrigem;
    }

    public String getLocalArmazenamento() {
        return localArmazenamento;
    }

    public void setLocalArmazenamento(String localArmazenamento) {
        this.localArmazenamento = localArmazenamento;
    }

    public BigDecimal getQtdDisponivelKg() {
        return qtdDisponivelKg;
    }

    public void setQtdDisponivelKg(BigDecimal qtdDisponivelKg) {
        this.qtdDisponivelKg = qtdDisponivelKg;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Semente getSemente() {
        return semente;
    }

    public void setSemente(Semente semente) {
        this.semente = semente;
    }

    public Armazem getArmazem() {
        return armazem;
    }

    public void setArmazem(Armazem armazem) {
        this.armazem = armazem;
    }
}
