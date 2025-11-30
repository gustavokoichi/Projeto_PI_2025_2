package pe.senac.br.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Pedido")
@IdClass(PedidoId.class)
public class Pedido {

    @Id
    @Column(name = "Fornecedor_idFornecedor")
    private Integer fornecedorId;

    @Id
    @Column(name = "Beneficiario_idBeneficiario")
    private Integer beneficiarioId;

    @Id
    @Column(name = "LoteSemente_idLote_Semente")
    private Integer loteSementeId;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false, length = 15)
    private String status;

    @ManyToOne
    @JoinColumn(name = "Fornecedor_idFornecedor", insertable = false, updatable = false)
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "Beneficiario_idBeneficiario", insertable = false, updatable = false)
    private Beneficiario beneficiario;

    @ManyToOne
    @JoinColumn(name = "LoteSemente_idLote_Semente", insertable = false, updatable = false)
    private LoteSemente loteSemente;

    public Pedido() {
    }

    public Integer getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Integer fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public Integer getBeneficiarioId() {
        return beneficiarioId;
    }

    public void setBeneficiarioId(Integer beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }

    public Integer getLoteSementeId() {
        return loteSementeId;
    }

    public void setLoteSementeId(Integer loteSementeId) {
        this.loteSementeId = loteSementeId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public LoteSemente getLoteSemente() {
        return loteSemente;
    }

    public void setLoteSemente(LoteSemente loteSemente) {
        this.loteSemente = loteSemente;
    }
}
