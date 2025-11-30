package pe.senac.br.backend.dto;

public class ContatoDTO {

    private Integer idTelefone;
    private String numero;
    private String email;
    private Integer fornecedorId;
    private Integer usuarioId;
    private Integer beneficiarioId;
    private Integer armazemId;

    public ContatoDTO() {
    }

    public Integer getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Integer idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Integer fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getBeneficiarioId() {
        return beneficiarioId;
    }

    public void setBeneficiarioId(Integer beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }

    public Integer getArmazemId() {
        return armazemId;
    }

    public void setArmazemId(Integer armazemId) {
        this.armazemId = armazemId;
    }
}
