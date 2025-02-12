package restventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restventas.modelo.entities.Comercial;

public interface ComercialRepository extends JpaRepository<Comercial, Integer> {
	
	 @Query("SELECT c FROM Comercial c LEFT JOIN Pedido p ON c.idComercial = p.comercial.idComercial WHERE p.idPedido IS NULL")
	    List<Comercial> findComercialesSinPedidos();
	
	
}
