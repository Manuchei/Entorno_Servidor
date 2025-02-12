package eventos.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eventos.entidades.Reserva;
import eventos.entidades.Usuario;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

	List<Reserva> findByUsuarioAndEventoFechaInicioAfter(Usuario usuario, LocalDate fechaInicio);

	List<Reserva> findByUsuarioAndEvento_FechaInicioAfter(Usuario usuario, Date fecha);

}
