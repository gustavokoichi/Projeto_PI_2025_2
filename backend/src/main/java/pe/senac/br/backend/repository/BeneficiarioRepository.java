package pe.senac.br.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.senac.br.backend.model.Beneficiario;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Integer> {
	  Optional<Beneficiario> findByCpf(String cpf);

}
