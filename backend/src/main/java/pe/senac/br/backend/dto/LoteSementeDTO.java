package pe.senac.br.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoteSementeDTO {

    private Integer idLoteSemente;
    private LocalDate dataProducao;
    private LocalDate dataValidade;
    private BigDecimal qtdOriginalKg;
    private String certificadoOrigem;
    private String localArmazenamento;
    private BigDecimal qtdDisponivelKg;
    private BigDecimal valor;
    private Integer sementeId;
    private Integer armazemId;

    public LoteSementeDTO() {
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

    public Integer getSementeId() {
        return sementeId;
    }

    public void setSementeId(Integer sementeId) {
        this.sementeId = sementeId;
    }

    public Integer getArmazemId() {
        return armazemId;
    }

    public void setArmazemId(Integer armazemId) {
        this.armazemId = armazemId;
    }
}
