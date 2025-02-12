package restventas.modelo.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restventas.modelo.entities.Comercial;
import restventas.modelo.entities.Pedido;
import restventas.repository.ComercialRepository;
import restventas.repository.PedidoRepository;

@Service
public class ComercialServiceImpl implements ComercialServices {

	@Autowired
	private ComercialRepository cre;

	@Autowired
	private PedidoRepository pre;

	@Override
	public Comercial alta(Comercial comercial) {
		try {
			if (cre.existsById(comercial.getIdComercial()))
				return null;
			else
				return cre.save(comercial);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Comercial> buscarTodos() {
		return cre.findAll();
	}

	@Override
	public Comercial buscarUno(int idComercial) {
		// TODO Auto-generated method stub
		return cre.findById(idComercial).orElse(null);
	}

	@Override
	public int eliminar(int idComercial) {
		try {
			if (cre.existsById(idComercial)) {
				cre.deleteById(idComercial);
				return 1;
			} else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Comercial> obtenerComercialesSinPedidos() {
		return cre.findComercialesSinPedidos();
	}

	@Override
	public List<Comercial> obtenerComercialesPorCliente(int idCliente) {
		List<Pedido> pedidos = pre.findByClienteIdCliente(idCliente);
		return pedidos.stream().map(Pedido::getComercial).distinct().toList();
	}

	@Override
	public Map<String, Double> obtenerTotalPedidosPorComercial() {
		return pre.findAll().stream().collect(Collectors.groupingBy(pedido -> {
			Comercial comercial = pedido.getComercial();
			return comercial.getNombre() + " " + comercial.getApellido1() + " " + comercial.getApellido2();
		}, Collectors.summingDouble(Pedido::getImporte)));
	}

}
