package pe.senac.br.backend.controller;

import pe.senac.br.backend.dto.FornecedorDTO;
import pe.senac.br.backend.model.Fornecedor;
import pe.senac.br.backend.repository.FornecedorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fornecedores")
@CrossOrigin(origins = "http://localhost:3000")
public class FornecedorController {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorController(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @GetMapping
    public List<FornecedorDTO> listar() {
        return fornecedorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> buscarPorId(@PathVariable Integer id) {
        return fornecedorRepository.findById(id)
                .map(fornecedor -> ResponseEntity.ok(toDTO(fornecedor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> criar(@RequestBody FornecedorDTO dto) {
        Fornecedor fornecedor = fromDTO(dto);
        Fornecedor salvo = fornecedorRepository.save(fornecedor);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDTO> atualizar(@PathVariable Integer id,
                                                   @RequestBody FornecedorDTO dto) {
        return fornecedorRepository.findById(id)
                .map(existente -> {
                    existente.setNome(dto.getNome());
                    existente.setCpfCnpj(dto.getCpfCnpj());
                    Fornecedor atualizado = fornecedorRepository.save(existente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!fornecedorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        fornecedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private FornecedorDTO toDTO(Fornecedor fornecedor) {
        FornecedorDTO dto = new FornecedorDTO();
        dto.setIdFornecedor(fornecedor.getIdFornecedor());
        dto.setNome(fornecedor.getNome());
        dto.setCpfCnpj(fornecedor.getCpfCnpj());
        return dto;
    }

    private Fornecedor fromDTO(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.getNome());
        fornecedor.setCpfCnpj(dto.getCpfCnpj());
        return fornecedor;
    }
}
