package pe.senac.br.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.senac.br.backend.model.LoteSemente;

public interface LoteSementeRepository extends JpaRepository<LoteSemente, Integer> {
	  Optional<LoteSemente> findByIdLoteSemente(Integer idLoteSemente);

}
