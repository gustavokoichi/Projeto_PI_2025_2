package pe.senac.br.backend.controller;

import pe.senac.br.backend.dto.LoteSementeDTO;
import pe.senac.br.backend.model.LoteSemente;
import pe.senac.br.backend.model.Semente;
import pe.senac.br.backend.model.Armazem;
import pe.senac.br.backend.repository.LoteSementeRepository;
import pe.senac.br.backend.repository.SementeRepository;
import pe.senac.br.backend.repository.ArmazemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lotes-sementes")
@CrossOrigin(origins = "http://localhost:3000")
public class LoteSementeController {

    private final LoteSementeRepository loteSementeRepository;
    private final SementeRepository sementeRepository;
    private final ArmazemRepository armazemRepository;

    public LoteSementeController(LoteSementeRepository loteSementeRepository,
                                 SementeRepository sementeRepository,
                                 ArmazemRepository armazemRepository) {
        this.loteSementeRepository = loteSementeRepository;
        this.sementeRepository = sementeRepository;
        this.armazemRepository = armazemRepository;
    }

    @GetMapping
    public List<LoteSementeDTO> listar() {
        return loteSementeRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteSementeDTO> buscarPorId(@PathVariable Integer id) {
        return loteSementeRepository.findById(id)
                .map(lote -> ResponseEntity.ok(toDTO(lote)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LoteSementeDTO> criar(@RequestBody LoteSementeDTO dto) {
        LoteSemente lote = fromDTO(dto);
        LoteSemente salvo = loteSementeRepository.save(lote);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteSementeDTO> atualizar(@PathVariable Integer id,
                                                    @RequestBody LoteSementeDTO dto) {
        return loteSementeRepository.findById(id)
                .map(existente -> {
                    existente.setDataProducao(dto.getDataProducao());
                    existente.setDataValidade(dto.getDataValidade());
                    existente.setQtdOriginalKg(dto.getQtdOriginalKg());
                    existente.setCertificadoOrigem(dto.getCertificadoOrigem());
                    existente.setLocalArmazenamento(dto.getLocalArmazenamento());
                    existente.setQtdDisponivelKg(dto.getQtdDisponivelKg());
                    existente.setValor(dto.getValor());
                    
                    if (dto.getSementeId() != null) {
                        Semente semente = sementeRepository.findById(dto.getSementeId())
                                .orElseThrow(() -> new RuntimeException("Semente não encontrada"));
                        existente.setSemente(semente);
                    }
                    
                    if (dto.getArmazemId() != null) {
                        Armazem armazem = armazemRepository.findById(dto.getArmazemId())
                                .orElseThrow(() -> new RuntimeException("Armazém não encontrado"));
                        existente.setArmazem(armazem);
                    }
                    
                    LoteSemente atualizado = loteSementeRepository.save(existente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!loteSementeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        loteSementeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private LoteSementeDTO toDTO(LoteSemente lote) {
        LoteSementeDTO dto = new LoteSementeDTO();
        dto.setIdLoteSemente(lote.getIdLoteSemente());
        dto.setDataProducao(lote.getDataProducao());
        dto.setDataValidade(lote.getDataValidade());
        dto.setQtdOriginalKg(lote.getQtdOriginalKg());
        dto.setCertificadoOrigem(lote.getCertificadoOrigem());
        dto.setLocalArmazenamento(lote.getLocalArmazenamento());
        dto.setQtdDisponivelKg(lote.getQtdDisponivelKg());
        dto.setValor(lote.getValor());
        
        if (lote.getSemente() != null) {
            dto.setSementeId(lote.getSemente().getIdSemente());
        }
        
        if (lote.getArmazem() != null) {
            dto.setArmazemId(lote.getArmazem().getIdArmazenamento());
        }
        
        return dto;
    }

    private LoteSemente fromDTO(LoteSementeDTO dto) {
        LoteSemente lote = new LoteSemente();
        lote.setDataProducao(dto.getDataProducao());
        lote.setDataValidade(dto.getDataValidade());
        lote.setQtdOriginalKg(dto.getQtdOriginalKg());
        lote.setCertificadoOrigem(dto.getCertificadoOrigem());
        lote.setLocalArmazenamento(dto.getLocalArmazenamento());
        lote.setQtdDisponivelKg(dto.getQtdDisponivelKg());
        lote.setValor(dto.getValor());
        
        if (dto.getSementeId() != null) {
            Semente semente = sementeRepository.findById(dto.getSementeId())
                    .orElseThrow(() -> new RuntimeException("Semente não encontrada"));
            lote.setSemente(semente);
        }
        
        if (dto.getArmazemId() != null) {
            Armazem armazem = armazemRepository.findById(dto.getArmazemId())
                    .orElseThrow(() -> new RuntimeException("Armazém não encontrado"));
            lote.setArmazem(armazem);
        }
        
        return lote;
    }
}
