package model;

public class Cliente {
	private Integer id;
	private String DNI;
	private String nombre;
	private String apellidos;
	private String email;
	
	
	
	public Cliente(Integer id, String nombre, String apellidos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	public Cliente(Integer id, String dni, String nombre, String apellidos, String email) {
		super();
		this.id = id;
		this.DNI = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		
	}

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDni() {
		return DNI;
	}
	public void setDni(String dni) {
		this.DNI = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return   DNI + "," + nombre + "," + apellidos + "," + email+ "," + id ;
	}
	
	

}
