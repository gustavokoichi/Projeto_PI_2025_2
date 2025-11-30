package pe.senac.br.backend.controller;

import pe.senac.br.backend.dto.SementeDTO;
import pe.senac.br.backend.model.Semente;
import pe.senac.br.backend.repository.SementeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sementes")
@CrossOrigin(origins = "http://localhost:3000")
public class SementeController {

    private final SementeRepository sementeRepository;

    public SementeController(SementeRepository sementeRepository) {
        this.sementeRepository = sementeRepository;
    }

    @GetMapping
    public List<SementeDTO> listar() {
        return sementeRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SementeDTO> buscarPorId(@PathVariable Integer id) {
        return sementeRepository.findById(id)
                .map(semente -> ResponseEntity.ok(toDTO(semente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SementeDTO> criar(@RequestBody SementeDTO dto) {
        Semente semente = fromDTO(dto);
        Semente salvo = sementeRepository.save(semente);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SementeDTO> atualizar(@PathVariable Integer id,
                                                @RequestBody SementeDTO dto) {
        return sementeRepository.findById(id)
                .map(existente -> {
                    existente.setNome(dto.getNome());
                    existente.setNomeCientifico(dto.getNomeCientifico());
                    existente.setDescricao(dto.getDescricao());
                    Semente atualizado = sementeRepository.save(existente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!sementeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sementeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private SementeDTO toDTO(Semente semente) {
        SementeDTO dto = new SementeDTO();
        dto.setIdSemente(semente.getIdSemente());
        dto.setNome(semente.getNome());
        dto.setNomeCientifico(semente.getNomeCientifico());
        dto.setDescricao(semente.getDescricao());
        return dto;
    }

    private Semente fromDTO(SementeDTO dto) {
        Semente semente = new Semente();
        semente.setNome(dto.getNome());
        semente.setNomeCientifico(dto.getNomeCientifico());
        semente.setDescricao(dto.getDescricao());
        return semente;
    }
}
