package pe.senac.br.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Beneficiario")
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBeneficiario")
    private Integer idBeneficiario;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "Banco_idBanco", nullable = false)
    private Banco banco;

    public Beneficiario() {
    }

    public Integer getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Integer idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}
