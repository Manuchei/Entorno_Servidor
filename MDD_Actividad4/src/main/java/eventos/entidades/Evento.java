package eventos.entidades;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the eventos database table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "eventos")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EVENTO")
	private Integer idEvento;

	@Column(name = "AFORO_MAXIMO")
	private Integer aforoMaximo;

	private String descripcion;

	private String destacado;

	private String direccion;

	private Integer duracion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;

	@Column(name = "MINIMO_ASISTENCIA")
	private Integer minimoAsistencia;

	private String nombre;

	private BigDecimal precio;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO")
	private Tipo tipo;

	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

}
