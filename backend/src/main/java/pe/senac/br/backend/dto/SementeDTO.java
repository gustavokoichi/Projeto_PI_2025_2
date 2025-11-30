package pe.senac.br.backend.dto;

public class SementeDTO {

    private Integer idSemente;
    private String nome;
    private String nomeCientifico;
    private String descricao;

    public SementeDTO() {
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
}
