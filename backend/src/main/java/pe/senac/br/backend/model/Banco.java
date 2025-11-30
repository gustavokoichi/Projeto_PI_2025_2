package pe.senac.br.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Banco")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBanco")
    private Integer idBanco;

    @Column(name = "numAgencia", nullable = false, length = 6)
    private String numAgencia;

    @Column(name = "numConta", nullable = false, length = 20)
    private String numConta;

    @Column(name = "chavePix", nullable = false, unique = true, length = 100)
    private String chavePix;

    public Banco() {
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public String getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(String numAgencia) {
        this.numAgencia = numAgencia;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }
}
