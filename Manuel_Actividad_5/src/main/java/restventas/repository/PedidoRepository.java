package restventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import restventas.modelo.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByClienteIdCliente(int idCliente);

	List<Pedido> findAll();
	
	List<Pedido> findByComercialIdComercial(int idComercial);
	



}
