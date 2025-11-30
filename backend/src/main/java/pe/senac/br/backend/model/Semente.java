package pe.senac.br.backend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Semente")
public class Semente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSemente")
    private Integer idSemente;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(name = "nome_cientifico", nullable = false, unique = true, length = 45)
    private String nomeCientifico;

    @Column(length = 100)
    private String descricao;

    @OneToMany(mappedBy = "semente")
    private List<LoteSemente> lotes = new ArrayList<>();

    public Semente() {
    }

    public Integer getIdSemente() {
        return idSemente;
    }

    public void setIdSemente(Integer idSemente) {
        this.idSemente = idSemente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<LoteSemente> getLotes() {
        return lotes;
    }

    public void setLotes(List<LoteSemente> lotes) {
        this.lotes = lotes;
    }
}
