package pe.senac.br.backend.repository;

import pe.senac.br.backend.model.Pedido;
import pe.senac.br.backend.model.PedidoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, PedidoId> {
    
    List<Pedido> findByFornecedorId(Integer fornecedorId);
    
    List<Pedido> findByBeneficiarioId(Integer beneficiarioId);
    
    List<Pedido> findByLoteSementeId(Integer loteSementeId);
    
    List<Pedido> findByStatus(String status);
    
    List<Pedido> findByData(LocalDate data);
    
    List<Pedido> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
    
    List<Pedido> findByFornecedorIdAndStatus(Integer fornecedorId, String status);
}
