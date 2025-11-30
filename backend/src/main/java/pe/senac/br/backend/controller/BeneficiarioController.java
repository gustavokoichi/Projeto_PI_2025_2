package pe.senac.br.backend.controller;

import pe.senac.br.backend.dto.BeneficiarioDTO;
import pe.senac.br.backend.model.Beneficiario;
import pe.senac.br.backend.model.Banco;
import pe.senac.br.backend.repository.BeneficiarioRepository;
import pe.senac.br.backend.repository.BancoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/beneficiarios")
@CrossOrigin(origins = "http://localhost:3000")
public class BeneficiarioController {

    private final BeneficiarioRepository beneficiarioRepository;
    private final BancoRepository bancoRepository;

    public BeneficiarioController(BeneficiarioRepository beneficiarioRepository,
                                  BancoRepository bancoRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.bancoRepository = bancoRepository;
    }

    @GetMapping
    public List<BeneficiarioDTO> listar() {
        return beneficiarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeneficiarioDTO> buscarPorId(@PathVariable Integer id) {
        return beneficiarioRepository.findById(id)
                .map(beneficiario -> ResponseEntity.ok(toDTO(beneficiario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BeneficiarioDTO> criar(@RequestBody BeneficiarioDTO dto) {
        Beneficiario beneficiario = fromDTO(dto);
        Beneficiario salvo = beneficiarioRepository.save(beneficiario);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeneficiarioDTO> atualizar(@PathVariable Integer id,
                                                     @RequestBody BeneficiarioDTO dto) {
        return beneficiarioRepository.findById(id)
                .map(existente -> {
                    existente.setNome(dto.getNome());
                    existente.setCpf(dto.getCpf());
                    
                    if (dto.getBancoId() != null) {
                        Banco banco = bancoRepository.findById(dto.getBancoId())
                                .orElseThrow(() -> new RuntimeException("Banco não encontrado"));
                        existente.setBanco(banco);
                    }
                    
                    Beneficiario atualizado = beneficiarioRepository.save(existente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!beneficiarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        beneficiarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private BeneficiarioDTO toDTO(Beneficiario beneficiario) {
        BeneficiarioDTO dto = new BeneficiarioDTO();
        dto.setIdBeneficiario(beneficiario.getIdBeneficiario());
        dto.setNome(beneficiario.getNome());
        dto.setCpf(beneficiario.getCpf());
        if (beneficiario.getBanco() != null) {
            dto.setBancoId(beneficiario.getBanco().getIdBanco());
        }
        return dto;
    }

    private Beneficiario fromDTO(BeneficiarioDTO dto) {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNome(dto.getNome());
        beneficiario.setCpf(dto.getCpf());
        
        if (dto.getBancoId() != null) {
            Banco banco = bancoRepository.findById(dto.getBancoId())
                    .orElseThrow(() -> new RuntimeException("Banco não encontrado"));
            beneficiario.setBanco(banco);
        }
        
        return beneficiario;
    }
}
