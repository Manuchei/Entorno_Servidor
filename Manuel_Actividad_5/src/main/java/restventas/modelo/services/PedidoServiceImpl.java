package restventas.modelo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restventas.dto.PedidoDto;
import restventas.modelo.entities.Pedido;
import restventas.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoServices {

	@Autowired
	private PedidoRepository pre;

	@Override
	public List<PedidoDto> obtenerPedidosPorComercial(int idComercial) {
		List<Pedido> pedidos = pre.findByComercialIdComercial(idComercial);
		return pedidos.stream().map(this::convertirAPedidoDto).collect(Collectors.toList());
	}

	private PedidoDto convertirAPedidoDto(Pedido pedido) {
		return PedidoDto.builder().idPedido(pedido.getIdPedido()).importe(pedido.getImporte()).fecha(pedido.getFecha())
				.idCliente(pedido.getCliente().getIdCliente()) // Asegúrate de que Cliente tenga este campo
				.idComercial(pedido.getComercial().getIdComercial()) // Asegúrate de que Comercial tenga este campo
				.build();
	}
}
