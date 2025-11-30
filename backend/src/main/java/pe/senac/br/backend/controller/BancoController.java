package pe.senac.br.backend.controller;

import pe.senac.br.backend.dto.BancoDTO;
import pe.senac.br.backend.model.Banco;
import pe.senac.br.backend.repository.BancoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bancos")
@CrossOrigin(origins = "http://localhost:3000")
public class BancoController {

    private final BancoRepository bancoRepository;

    public BancoController(BancoRepository bancoRepository) {
        this.bancoRepository = bancoRepository;
    }

    @GetMapping
    public List<BancoDTO> listar() {
        return bancoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoDTO> buscarPorId(@PathVariable Integer id) {
        return bancoRepository.findById(id)
                .map(banco -> ResponseEntity.ok(toDTO(banco)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BancoDTO> criar(@RequestBody BancoDTO dto) {
        Banco banco = fromDTO(dto);
        Banco salvo = bancoRepository.save(banco);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BancoDTO> atualizar(@PathVariable Integer id,
                                                @RequestBody BancoDTO dto) {
        return bancoRepository.findById(id)
                .map(existente -> {
                    existente.setNumAgencia(dto.getNumAgencia());
                    existente.setNumConta(dto.getNumConta());
                    existente.setChavePix(dto.getChavePix());
                    Banco atualizado = bancoRepository.save(existente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!bancoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bancoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private BancoDTO toDTO(Banco banco) {
        BancoDTO dto = new BancoDTO();
        dto.setIdBanco(banco.getIdBanco());
        dto.setNumAgencia(banco.getNumAgencia());
        dto.setNumConta(banco.getNumConta());
        dto.setChavePix(banco.getChavePix());
        return dto;
    }

    private Banco fromDTO(BancoDTO dto) {
        Banco banco = new Banco();
        banco.setNumAgencia(dto.getNumAgencia());
        banco.setNumConta(dto.getNumConta());
        banco.setChavePix(dto.getChavePix());
        return banco;
    }
}
