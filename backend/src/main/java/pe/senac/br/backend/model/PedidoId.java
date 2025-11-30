package pe.senac.br.backend.model;

import java.io.Serializable;
import java.util.Objects;

public class PedidoId implements Serializable {

    private Integer fornecedorId;
    private Integer beneficiarioId;
    private Integer loteSementeId;

    public PedidoId() {
    }

    public PedidoId(Integer fornecedorId, Integer beneficiarioId, Integer loteSementeId) {
        this.fornecedorId = fornecedorId;
        this.beneficiarioId = beneficiarioId;
        this.loteSementeId = loteSementeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoId pedidoId = (PedidoId) o;
        return Objects.equals(fornecedorId, pedidoId.fornecedorId) &&
               Objects.equals(beneficiarioId, pedidoId.beneficiarioId) &&
               Objects.equals(loteSementeId, pedidoId.loteSementeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fornecedorId, beneficiarioId, loteSementeId);
    }
}
