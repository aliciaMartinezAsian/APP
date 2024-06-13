package model;

import java.sql.Date;

public class Pago {
	private String id;
	private Date fecha;
	private Integer cantidad;
	private String metodoPago;
	private Integer idCliente;
	
	public Pago(String id, Date fecha, Integer cantidad, Integer idCliente) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.idCliente = idCliente;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	@Override
	public String toString() {
		return "Pago [id=" + id + ", fecha=" + fecha + ", cantidad=" + cantidad + ", metodoPago=" + metodoPago
				+ ", idCliente=" + idCliente + "]";
	}
	
	

}
