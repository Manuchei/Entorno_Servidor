package eventos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.entidades.Evento;
import eventos.repository.EventoRepository;

@Repository
public class EventoDaoImpl implements EventoDao {
	@Autowired
	private EventoRepository erepo;

	@Override
	public List<Evento> buscarPorAceptadoYDestacado() {
		// TODO Auto-generated method stub
		return erepo.findByAceptadoAndDestacado();
	}

	@Override
	public List<Evento> buscarPorTipo(int idTipo) {
		// TODO Auto-generated method stub
		return erepo.findByTipoIdTipo(idTipo);
	}

	@Override
	public Evento buscarUno(int idEvento) {
		// TODO Auto-generated method stub
		return erepo.findById(idEvento).orElse(null);
	}

	@Override
	public List<Evento> buscarPorestado(String estado) {
		// TODO Auto-generated method stub
		return erepo.findByEstado(estado);
	}

	public List<Evento> buscarTodos() {
		return erepo.findAll();
	}

}
