package pe.senac.br.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoDTO {

    private Integer fornecedorId;
    private Integer beneficiarioId;
    private Integer loteSementeId;
    private LocalDate data;
    private BigDecimal valor;
    private String status;

    public PedidoDTO() {
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
}
