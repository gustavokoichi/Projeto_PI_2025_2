package pe.senac.br.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.senac.br.backend.model.Banco;

public interface BancoRepository extends JpaRepository<Banco, Integer> {
	  Optional<Banco> findByChavePix(String chavePix);

}
