package pe.senac.br.backend.dto;

import java.math.BigDecimal;

public class ArmazemDTO {

    private Integer idArmazenamento;
    private String nome;
    private BigDecimal capacidade;

    public ArmazemDTO() {
    }

    public Integer getIdArmazenamento() {
        return idArmazenamento;
    }

    public void setIdArmazenamento(Integer idArmazenamento) {
        this.idArmazenamento = idArmazenamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(BigDecimal capacidade) {
        this.capacidade = capacidade;
    }
}
