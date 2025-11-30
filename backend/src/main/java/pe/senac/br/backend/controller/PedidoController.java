package pe.senac.br.backend.controller;

import pe.senac.br.backend.dto.PedidoDTO;
import pe.senac.br.backend.model.Pedido;
import pe.senac.br.backend.model.PedidoId;
import pe.senac.br.backend.model.Fornecedor;
import pe.senac.br.backend.model.Beneficiario;
import pe.senac.br.backend.model.LoteSemente;
import pe.senac.br.backend.repository.PedidoRepository;
import pe.senac.br.backend.repository.FornecedorRepository;
import pe.senac.br.backend.repository.BeneficiarioRepository;
import pe.senac.br.backend.repository.LoteSementeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final BeneficiarioRepository beneficiarioRepository;
    private final LoteSementeRepository loteSementeRepository;

    public PedidoController(PedidoRepository pedidoRepository,
                           FornecedorRepository fornecedorRepository,
                           BeneficiarioRepository beneficiarioRepository,
                           LoteSementeRepository loteSementeRepository) {
        this.pedidoRepository = pedidoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.beneficiarioRepository = beneficiarioRepository;
        this.loteSementeRepository = loteSementeRepository;
    }

    @GetMapping
    public List<PedidoDTO> listar() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{fornecedorId}/{beneficiarioId}/{loteSementeId}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Integer fornecedorId,
                                                  @PathVariable Integer beneficiarioId,
                                                  @PathVariable Integer loteSementeId) {
        PedidoId id = new PedidoId(fornecedorId, beneficiarioId, loteSementeId);
        return pedidoRepository.findById(id)
                .map(pedido -> ResponseEntity.ok(toDTO(pedido)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> criar(@RequestBody PedidoDTO dto) {
        Pedido pedido = fromDTO(dto);
        Pedido salvo = pedidoRepository.save(pedido);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{fornecedorId}/{beneficiarioId}/{loteSementeId}")
    public ResponseEntity<PedidoDTO> atualizar(@PathVariable Integer fornecedorId,
                                               @PathVariable Integer beneficiarioId,
                                               @PathVariable Integer loteSementeId,
                                               @RequestBody PedidoDTO dto) {
        PedidoId id = new PedidoId(fornecedorId, beneficiarioId, loteSementeId);
        return pedidoRepository.findById(id)
                .map(existente -> {
                    existente.setData(dto.getData());
                    existente.setValor(dto.getValor());
                    existente.setStatus(dto.getStatus());
                    
                    Pedido atualizado = pedidoRepository.save(existente);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{fornecedorId}/{beneficiarioId}/{loteSementeId}")
    public ResponseEntity<Void> deletar(@PathVariable Integer fornecedorId,
                                        @PathVariable Integer beneficiarioId,
                                        @PathVariable Integer loteSementeId) {
        PedidoId id = new PedidoId(fornecedorId, beneficiarioId, loteSementeId);
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-status/{status}")
    public List<PedidoDTO> buscarPorStatus(@PathVariable String status) {
        return pedidoRepository.findByStatus(status)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setFornecedorId(pedido.getFornecedorId());
        dto.setBeneficiarioId(pedido.getBeneficiarioId());
        dto.setLoteSementeId(pedido.getLoteSementeId());
        dto.setData(pedido.getData());
        dto.setValor(pedido.getValor());
        dto.setStatus(pedido.getStatus());
        return dto;
    }

    private Pedido fromDTO(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setFornecedorId(dto.getFornecedorId());
        pedido.setBeneficiarioId(dto.getBeneficiarioId());
        pedido.setLoteSementeId(dto.getLoteSementeId());
        pedido.setData(dto.getData());
        pedido.setValor(dto.getValor());
        pedido.setStatus(dto.getStatus());
        
        if (dto.getFornecedorId() != null) {
            fornecedorRepository.findById(dto.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        }
        
        if (dto.getBeneficiarioId() != null) {
            beneficiarioRepository.findById(dto.getBeneficiarioId())
                    .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado"));
        }
        
        if (dto.getLoteSementeId() != null) {
            loteSementeRepository.findById(dto.getLoteSementeId())
                    .orElseThrow(() -> new RuntimeException("Lote de Semente não encontrado"));
        }
        
        return pedido;
    }
}
