package pe.senac.br.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTelefone")
    private Integer idTelefone;

    @Column(nullable = false, length = 11)
    private String numero;

    @Column(nullable = false, length = 45)
    private String email;

    @ManyToOne
    @JoinColumn(name = "Fornecedor_idFornecedor")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "Usuario_idusuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "Beneficiario_idBeneficiario")
    private Beneficiario beneficiario;

    @ManyToOne
    @JoinColumn(name = "Armazem_idArmazenamento")
    private Armazem armazem;

    public Contato() {
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Armazem getArmazem() {
        return armazem;
    }

    public void setArmazem(Armazem armazem) {
        this.armazem = armazem;
    }
}
