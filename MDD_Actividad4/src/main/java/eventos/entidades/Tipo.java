package eventos.entidades;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

/**
 * The persistent class for the tipos database table.
 * 
 */
@Entity
@Table(name = "tipos")
public class Tipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO")
	private Integer idTipo;

	private String descripcion;

	private String nombre;

	@OneToMany(mappedBy = "tipo")
	private List<Evento> eventos;

	public Tipo() {
	}

	public Integer getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}