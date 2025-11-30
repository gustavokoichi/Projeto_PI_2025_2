package pe.senac.br.backend.dto;

public class UndRecebedoraDTO {

    private Integer beneficiarioIdBeneficiario;
    private String cnpj;

    public UndRecebedoraDTO() {
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
}
