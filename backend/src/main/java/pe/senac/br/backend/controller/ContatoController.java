package pe.senac.br.backend.controller;

import pe.senac.br.backend.dto.ContatoDTO;
import pe.senac.br.backend.model.Contato;
import pe.senac.br.backend.model.Fornecedor;
import pe.senac.br.backend.model.Usuario;
import pe.senac.br.backend.model.Beneficiario;
import pe.senac.br.backend.model.Armazem;
import pe.senac.br.backend.repository.ContatoRepository;
import pe.senac.br.backend.repository.FornecedorRepository;
import pe.senac.br.backend.repository.UsuarioRepository;
import pe.senac.br.backend.repository.BeneficiarioRepository;
import pe.senac.br.backend.repository.ArmazemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contatos")
@CrossOrigin(origins = "http://localhost:3000")
public class ContatoController {

    private final ContatoRepository contatoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final UsuarioRepository usuarioRepository;
    private final BeneficiarioRepository beneficiarioRepository;
    private final ArmazemRepository armazemRepository;

    public ContatoController(ContatoRepository contatoRepository,
                            FornecedorRepository fornecedorRepository,
                            UsuarioRepository usuarioRepository,
                            BeneficiarioRepository beneficiarioRepository,
                            ArmazemRepository armazemRepository) {
        this.contatoRepository = contatoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.usuarioRepository = usuarioRepository;
        this.beneficiarioRepository = beneficiarioRepository;
        this.armazemRepository = armazemRepository;
    }

    @GetMapping
    public List<ContatoDTO> listar() {
        return contatoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoDTO> buscarPorId(@PathVariable Integer id) {
        return contatoRepository.findById(id)
                .map(contato -> ResponseEntity.ok(toDTO(contato)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContatoDTO> criar(@RequestBody ContatoDTO dto) {
        Contato contato = fromDTO(dto);
        Contato salvo = contatoRepository.save(contato);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoDTO> atualizar(@PathVariable Integer id,
                                               @RequestBody ContatoDTO dto) {
        return contatoRepository.findById(id)
                .map(existente -> {
                    existente.setNumero(dto.getNumero());
                    existente.setEmail(dto.getEmail());
                    
                    if (dto.getFornecedorId() != null) {
                        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
                        existente.setFornecedor(fornecedor);
                    } else {
                        existente.setFornecedor(null);
                    }
                    
                    if (dto.getUsuarioId() != null) {
                        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
                        existente.setUsuario(usuario);
                    } else {
                        existente.setUsuario(null);
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
                    
                    Contato atualizado = contatoRepository.save(existente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!contatoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contatoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ContatoDTO toDTO(Contato contato) {
        ContatoDTO dto = new ContatoDTO();
        dto.setIdTelefone(contato.getIdTelefone());
        dto.setNumero(contato.getNumero());
        dto.setEmail(contato.getEmail());
        
        if (contato.getFornecedor() != null) {
            dto.setFornecedorId(contato.getFornecedor().getIdFornecedor());
        }
        
        if (contato.getUsuario() != null) {
            dto.setUsuarioId(contato.getUsuario().getIdUsuario());
        }
        
        if (contato.getBeneficiario() != null) {
            dto.setBeneficiarioId(contato.getBeneficiario().getIdBeneficiario());
        }
        
        if (contato.getArmazem() != null) {
            dto.setArmazemId(contato.getArmazem().getIdArmazenamento());
        }
        
        return dto;
    }

    private Contato fromDTO(ContatoDTO dto) {
        Contato contato = new Contato();
        contato.setNumero(dto.getNumero());
        contato.setEmail(dto.getEmail());
        
        if (dto.getFornecedorId() != null) {
            Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
            contato.setFornecedor(fornecedor);
        }
        
        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            contato.setUsuario(usuario);
        }
        
        if (dto.getBeneficiarioId() != null) {
            Beneficiario beneficiario = beneficiarioRepository.findById(dto.getBeneficiarioId())
                    .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado"));
            contato.setBeneficiario(beneficiario);
        }
        
        if (dto.getArmazemId() != null) {
            Armazem armazem = armazemRepository.findById(dto.getArmazemId())
                    .orElseThrow(() -> new RuntimeException("Armazém não encontrado"));
            contato.setArmazem(armazem);
        }
        
        return contato;
    }
}
