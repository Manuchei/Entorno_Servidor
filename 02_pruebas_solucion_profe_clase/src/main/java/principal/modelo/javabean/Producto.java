package principal.modelo.javabean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Producto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long idProducto;
	private String descripcion;
	private double precio;
	private int stock;
	private Date fechaAlta;
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	

	public Producto(long idProducto, String descripcion, double precio, int stock, Date fechaAlta) {
		super();
		this.idProducto = idProducto;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fechaAlta = fechaAlta;
	}



	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	

	public Date getFechaAlta() {
		return fechaAlta;
	}



	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}



	@Override
	public int hashCode() {
		return Objects.hash(idProducto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Producto))
			return false;
		Producto other = (Producto) obj;
		return idProducto == other.idProducto;
	}



	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", descripcion=" + descripcion + ", precio=" + precio + ", stock="
				+ stock + ", fechaAlta=" + fechaAlta + "]";
	}

	
	
	// Metodos propios
	
	

}
