package pe.senac.br.backend.repository;

import pe.senac.br.backend.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
    List<Endereco> findByFornecedorIdFornecedor(Integer fornecedorId);
    
    List<Endereco> findByBeneficiarioIdBeneficiario(Integer beneficiarioId);
}
