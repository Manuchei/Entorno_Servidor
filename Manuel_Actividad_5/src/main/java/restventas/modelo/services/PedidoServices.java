package restventas.modelo.services;

import java.util.List;

import restventas.dto.PedidoDto;

public interface PedidoServices {
	
    List<PedidoDto> obtenerPedidosPorComercial(int idComercial);


}
