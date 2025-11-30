package pe.senac.br.backend.repository;

import pe.senac.br.backend.model.UndRecebedora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UndRecebedoraRepository extends JpaRepository<UndRecebedora, Integer> {
    
    Optional<UndRecebedora> findByCnpj(String cnpj);
}
