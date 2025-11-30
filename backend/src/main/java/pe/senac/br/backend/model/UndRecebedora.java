package pe.senac.br.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UndRecebedora")
public class UndRecebedora {

    @Id
    @Column(name = "Beneficiario_idBeneficiario")
    private Integer beneficiarioIdBeneficiario;

    @Column(nullable = false, length = 15)
    private String cnpj;

    @OneToOne
    @MapsId
    @JoinColumn(name = "Beneficiario_idBeneficiario")
    private Beneficiario beneficiario;

    public UndRecebedora() {
    }

    public Integer getBeneficiarioIdBeneficiario() {
        return beneficiarioIdBeneficiario;
    }

    public void setBeneficiarioIdBeneficiario(Integer beneficiarioIdBeneficiario) {
        this.beneficiarioIdBeneficiario = beneficiarioIdBeneficiario;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }
}
