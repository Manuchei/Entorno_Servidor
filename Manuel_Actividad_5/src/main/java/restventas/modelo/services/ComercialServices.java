package restventas.modelo.services;

import java.util.List;
import java.util.Map;

import restventas.modelo.entities.Comercial;

public interface ComercialServices {

	Comercial alta(Comercial comercial);

	List<Comercial> buscarTodos();

	Comercial buscarUno(int idComercial);

	int eliminar(int idComercial);

	List<Comercial> obtenerComercialesSinPedidos();

	List<Comercial> obtenerComercialesPorCliente(int idCliente);

	Map<String, Double> obtenerTotalPedidosPorComercial();

}
