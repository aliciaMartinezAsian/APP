package model;

public class Tratamiento {
	private Integer id;
	private Integer precio;
	private Integer duracion;
	private String descripcion;
	private String tipo;
	private Integer numSala;
	
	
	public Tratamiento(Integer id, Integer precio, String tipo) {
		super();
		this.id = id;
		this.precio = precio;
		this.tipo = tipo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getNumSala() {
		return numSala;
	}
	public void setNumSala(Integer numSala) {
		this.numSala = numSala;
	}
	@Override
	public String toString() {
		return "Tratamiento [id=" + id + ", precio=" + precio + ", duracion=" + duracion + ", descripcion="
				+ descripcion + ", tipo=" + tipo + ", numSala=" + numSala + "]";
	}
	
	

}
