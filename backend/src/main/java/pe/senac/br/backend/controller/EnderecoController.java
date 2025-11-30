package pe.senac.br.backend.controller;

import pe.senac.br.backend.dto.EnderecoDTO;
import pe.senac.br.backend.model.Endereco;
import pe.senac.br.backend.model.Fornecedor;
import pe.senac.br.backend.model.Beneficiario;
import pe.senac.br.backend.model.Armazem;
import pe.senac.br.backend.repository.EnderecoRepository;
import pe.senac.br.backend.repository.FornecedorRepository;
import pe.senac.br.backend.repository.BeneficiarioRepository;
import pe.senac.br.backend.repository.ArmazemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enderecos")
@CrossOrigin(origins = "http://localhost:3000")
public class EnderecoController {

    private final EnderecoRepository enderecoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final BeneficiarioRepository beneficiarioRepository;
    private final ArmazemRepository armazemRepository;

    public EnderecoController(EnderecoRepository enderecoRepository,
                             FornecedorRepository fornecedorRepository,
                             BeneficiarioRepository beneficiarioRepository,
                             ArmazemRepository armazemRepository) {
        this.enderecoRepository = enderecoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.beneficiarioRepository = beneficiarioRepository;
        this.armazemRepository = armazemRepository;
    }

    @GetMapping
    public List<EnderecoDTO> listar() {
        return enderecoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarPorId(@PathVariable Integer id) {
        return enderecoRepository.findById(id)
                .map(endereco -> ResponseEntity.ok(toDTO(endereco)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> criar(@RequestBody EnderecoDTO dto) {
        Endereco endereco = fromDTO(dto);
        Endereco salvo = enderecoRepository.save(endereco);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizar(@PathVariable Integer id,
                                                 @RequestBody EnderecoDTO dto) {
        return enderecoRepository.findById(id)
                .map(existente -> {
                    existente.setLogradouro(dto.getLogradouro());
                    existente.setNumero(dto.getNumero());
                    existente.setComplemento(dto.getComplemento());
                    existente.setBairro(dto.getBairro());
                    existente.setCidade(dto.getCidade());
                    existente.setUf(dto.getUf());
                    existente.setCep(dto.getCep());
                    
                    if (dto.getFornecedorId() != null) {
                        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
                        existente.setFornecedor(fornecedor);
                    } else {
                        existente.setFornecedor(null);
                    }
                    
                    if (dto.getBeneficiarioId() != null) {
                        Beneficiario beneficiario = beneficiarioRepository.findById(dto.getBeneficiarioId())
                                .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado"));
                        existente.setBeneficiario(beneficiario);
                    } else {
                        existente.setBeneficiario(null);
                    }
                    
                    if (dto.getArmazemId() != null) {
                        Armazem armazem = armazemRepository.findById(dto.getArmazemId())
                                .orElseThrow(() -> new RuntimeException("Armazém não encontrado"));
                        existente.setArmazem(armazem);
                    } else {
                        existente.setArmazem(null);
                    }
                    
                    Endereco atualizado = enderecoRepository.save(existente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!enderecoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        enderecoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private EnderecoDTO toDTO(Endereco endereco) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setIdEndereco(endereco.getIdEndereco());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setUf(endereco.getUf());
        dto.setCep(endereco.getCep());
        
        if (endereco.getFornecedor() != null) {
            dto.setFornecedorId(endereco.getFornecedor().getIdFornecedor());
        }
        
        if (endereco.getBeneficiario() != null) {
            dto.setBeneficiarioId(endereco.getBeneficiario().getIdBeneficiario());
        }
        
        if (endereco.getArmazem() != null) {
            dto.setArmazemId(endereco.getArmazem().getIdArmazenamento());
        }
        
        return dto;
    }

    private Endereco fromDTO(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setUf(dto.getUf());
        endereco.setCep(dto.getCep());
        
        if (dto.getFornecedorId() != null) {
            Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
            endereco.setFornecedor(fornecedor);
        }
        
        if (dto.getBeneficiarioId() != null) {
            Beneficiario beneficiario = beneficiarioRepository.findById(dto.getBeneficiarioId())
                    .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado"));
            endereco.setBeneficiario(beneficiario);
        }
        
        if (dto.getArmazemId() != null) {
            Armazem armazem = armazemRepository.findById(dto.getArmazemId())
                    .orElseThrow(() -> new RuntimeException("Armazém não encontrado"));
            endereco.setArmazem(armazem);
        }
        
        return endereco;
    }
}
