package pe.senac.br.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.senac.br.backend.model.Semente;

public interface SementeRepository extends JpaRepository<Semente, Integer> {
	  Optional<Semente> findByNome(String nome);
}
