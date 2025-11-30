package pe.senac.br.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.senac.br.backend.model.Armazem;

public interface ArmazemRepository extends JpaRepository<Armazem, Integer> {
	  Optional<Armazem> findByNome(String nome);
}
