package pe.senac.br.backend.controller;

import pe.senac.br.backend.dto.UndRecebedoraDTO;
import pe.senac.br.backend.model.UndRecebedora;
import pe.senac.br.backend.model.Beneficiario;
import pe.senac.br.backend.repository.UndRecebedoraRepository;
import pe.senac.br.backend.repository.BeneficiarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/unidades-recebedoras")
@CrossOrigin(origins = "http://localhost:3000")
public class UndRecebedoraController {

    private final UndRecebedoraRepository undRecebedoraRepository;
    private final BeneficiarioRepository beneficiarioRepository;

    public UndRecebedoraController(UndRecebedoraRepository undRecebedoraRepository,
                                   BeneficiarioRepository beneficiarioRepository) {
        this.undRecebedoraRepository = undRecebedoraRepository;
        this.beneficiarioRepository = beneficiarioRepository;
    }

    @GetMapping
    public List<UndRecebedoraDTO> listar() {
        return undRecebedoraRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UndRecebedoraDTO> buscarPorId(@PathVariable Integer id) {
        return undRecebedoraRepository.findById(id)
                .map(und -> ResponseEntity.ok(toDTO(und)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UndRecebedoraDTO> criar(@RequestBody UndRecebedoraDTO dto) {
        UndRecebedora und = fromDTO(dto);
        UndRecebedora salvo = undRecebedoraRepository.save(und);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UndRecebedoraDTO> atualizar(@PathVariable Integer id,
                                                      @RequestBody UndRecebedoraDTO dto) {
        return undRecebedoraRepository.findById(id)
                .map(existente -> {
                    existente.setCnpj(dto.getCnpj());
                    
                    if (dto.getBeneficiarioIdBeneficiario() != null) {
                        Beneficiario beneficiario = beneficiarioRepository.findById(dto.getBeneficiarioIdBeneficiario())
                                .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado"));
                        existente.setBeneficiario(beneficiario);
                    }
                    
                    UndRecebedora atualizado = undRecebedoraRepository.save(existente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!undRecebedoraRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        undRecebedoraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private UndRecebedoraDTO toDTO(UndRecebedora und) {
        UndRecebedoraDTO dto = new UndRecebedoraDTO();
        dto.setBeneficiarioIdBeneficiario(und.getBeneficiarioIdBeneficiario());
        dto.setCnpj(und.getCnpj());
        return dto;
    }

    private UndRecebedora fromDTO(UndRecebedoraDTO dto) {
        UndRecebedora und = new UndRecebedora();
        und.setCnpj(dto.getCnpj());
        
        if (dto.getBeneficiarioIdBeneficiario() != null) {
            Beneficiario beneficiario = beneficiarioRepository.findById(dto.getBeneficiarioIdBeneficiario())
                    .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado"));
            und.setBeneficiario(beneficiario);
        }
        
        return und;
    }
}
