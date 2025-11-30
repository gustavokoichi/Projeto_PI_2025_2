package pe.senac.br.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.senac.br.backend.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
	  Optional<Fornecedor> findByCpfCnpj(String cpfCnpj);

}
