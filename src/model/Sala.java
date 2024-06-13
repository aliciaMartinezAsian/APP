package model;

public class Sala {
	 private Integer num;
	 private Integer metrosCuadrados;
	 private Integer capacidad;
	 
	public Sala(Integer num) {
		super();
		this.num = num;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(Integer metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		return "Sala [num=" + num + ", metrosCuadrados=" + metrosCuadrados + ", capacidad=" + capacidad + "]";
	}

}
