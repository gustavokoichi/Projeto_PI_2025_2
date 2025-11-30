package pe.senac.br.backend.controller;

import pe.senac.br.backend.dto.ArmazemDTO;
import pe.senac.br.backend.model.Armazem;
import pe.senac.br.backend.repository.ArmazemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/armazens")
@CrossOrigin(origins = "http://localhost:3000")
public class ArmazemController {

    private final ArmazemRepository armazemRepository;

    public ArmazemController(ArmazemRepository armazemRepository) {
        this.armazemRepository = armazemRepository;
    }

    @GetMapping
    public List<ArmazemDTO> listar() {
        return armazemRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArmazemDTO> buscarPorId(@PathVariable Integer id) {
        return armazemRepository.findById(id)
                .map(armazem -> ResponseEntity.ok(toDTO(armazem)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ArmazemDTO> criar(@RequestBody ArmazemDTO dto) {
        Armazem armazem = fromDTO(dto);
        Armazem salvo = armazemRepository.save(armazem);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArmazemDTO> atualizar(@PathVariable Integer id,
                                                @RequestBody ArmazemDTO dto) {
        return armazemRepository.findById(id)
                .map(existente -> {
                    existente.setNome(dto.getNome());
                    existente.setCapacidade(dto.getCapacidade());
                    Armazem atualizado = armazemRepository.save(existente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!armazemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        armazemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ArmazemDTO toDTO(Armazem armazem) {
        ArmazemDTO dto = new ArmazemDTO();
        dto.setIdArmazenamento(armazem.getIdArmazenamento());
        dto.setNome(armazem.getNome());
        dto.setCapacidade(armazem.getCapacidade());
        return dto;
    }

    private Armazem fromDTO(ArmazemDTO dto) {
        Armazem armazem = new Armazem();
        armazem.setNome(dto.getNome());
        armazem.setCapacidade(dto.getCapacidade());
        return armazem;
    }
}
