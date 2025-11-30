package pe.senac.br.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Armazem")
public class Armazem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idArmazenamento")
    private Integer idArmazenamento;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, precision = 7, scale = 3)
    private BigDecimal capacidade;

    @OneToMany(mappedBy = "armazem")
    private List<LoteSemente> lotes = new ArrayList<>();

    public Armazem() {
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

    public List<LoteSemente> getLotes() {
        return lotes;
    }

    public void setLotes(List<LoteSemente> lotes) {
        this.lotes = lotes;
    }
}
