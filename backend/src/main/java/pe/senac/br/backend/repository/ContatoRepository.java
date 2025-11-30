package pe.senac.br.backend.repository;

import pe.senac.br.backend.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
  
    List<Contato> findByFornecedorIdFornecedor(Integer fornecedorId);
    
    List<Contato> findByUsuarioIdUsuario(Integer usuarioId);
    
    List<Contato> findByBeneficiarioIdBeneficiario(Integer beneficiarioId);
    
    List<Contato> findByArmazemIdArmazenamento(Integer armazemId);
}
