package eventos.dao;

import java.util.List;

import eventos.entidades.Evento;

public interface EventoDao {

	List<Evento> buscarPorAceptadoYDestacado();

	List<Evento> buscarPorTipo(int idTipo);

	Evento buscarUno(int idEvento);

	List<Evento> buscarPorestado(String estado);

	List<Evento> buscarTodos();

}
