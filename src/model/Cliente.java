package model;

/**
 * <h2>Clase Cliente para la administración de estos</h2>
 * 
 * @author Alicia Martinez Asian
 * @version 1.0
 * @since 14/06/2024
 */


public class Cliente {
    // Atributos del cliente
	/**
	 * Atributo que hace referencia al id del cliente.
	 */
    private Integer id;
	/**
	 * Atributo que hace referencia al dni del cliente.
	 */
    private String DNI;
	/**
	 * Atributo que hace referencia al nombre del cliente.
	 */
    private String nombre;
	/**
	 * Atributo que hace referencia a los apellidos del cliente.
	 */
    private String apellidos;
	/**
	 * Atributo que hace referencia al email del cliente.
	 */
    private String email;
/**
 *  <h2> Constructor parametrizado que crea una instancia de Cliente pasandole id, nombre y apellidos. </h2>
 * @param id
 * 			Id del cliente.
 * @param nombre
 * 			Nombre del cliente.
 * @param apellidos
 * 			Apellidos del cliente.
 */
    // Constructor con parámetros mínimos (id, nombre y apellidos)
    public Cliente(Integer id, String nombre, String apellidos) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    /**
     * <h2> Constructor parametrizado que crea una instancia de Cliente pasandole todos los atributos. </h2>
     * @param id
     * 		Id del Cliente.
     * @param dni
     * 		Dni del cliente.
     * @param nombre
     * 		Nombre del cliente.
     * @param apellidos
     * 		Apellidos del cliente.
     * @param email
     * 		Email del cliente.
     */

    // Constructor con todos los parámetros
    public Cliente(Integer id, String dni, String nombre, String apellidos, String email) {
        super();
        this.id = id;
        this.DNI = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    /**
	 * <h2>Constructor por defecto que instancia la clase Cliente. </h2>
	 */
    // Constructor vacío
    public Cliente() {
        // Constructor por defecto sin parámetros
    }
    
    /**
     * <h2> Devuelve el Id del cliente. </h2>
     * @return
     * 		Devuelve el Id del cliente. 
     */
    // Getter para el ID del cliente
    public Integer getId() {
        return id;
    }
    
    /**
     * <h2> Se introduce el Id del Cliente. </h2>
     * @param id
     * 		Id del cliente.
     */
    // Setter para el ID del cliente
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <h2> Devuelve el DNI del cliente. </h2>
     * @return
     * 		Devuelve el Id del cliente.
     */
    // Getter para el DNI del cliente
    public String getDni() {
        return DNI;
    }

    /**
     * <h2> Se introduce el DNI del Cliente. </h2>
     * @param dni
     * 		DNI del Cliente.
     */
    // Setter para el DNI del cliente
    public void setDni(String dni) {
        this.DNI = dni;
    }

    /**
     * <h2> Devuelve el Nombre del cliente. </h2>
     * @return
     * 		Devuelve el Nombre del cliente.
     */
    // Getter para el nombre del cliente
    public String getNombre() {
        return nombre;
    }

    /**
     * <h2> Se introduce el Nombre del Cliente. </h2>
     * @param nombre
     * 		Nombre del cliente.
     */
    // Setter para el nombre del cliente
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * <h2> Devuelve los apellidos del cliente. </h2>
     * @return
     * 		Devuelve los apellidos del cliente.
     */
    // Getter para los apellidos del cliente
    public String getApellidos() {
        return apellidos;
    }

    /**
     * <h2> Se introduce los apellidos del Cliente. </h2>
     * @param apellidos
     * 		Apellidos del cliente.
     */
    // Setter para los apellidos del cliente
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * <h2> Devuelve el Email del cliente. </h2>
     * @return
     * 		Devuelve el Email del cliente.
     */
    // Getter para el email del cliente
    public String getEmail() {
        return email;
    }

    /**
     * <h2> Se introduce el Email del Cliente. </h2>
     * @param email
     * 		Email del cliente.
     */
    // Setter para el email del cliente
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * <h2> Muestra los atributos del Cliente. </h2>
     */
    // Método toString para representar el cliente como una cadena de texto
    @Override
    public String toString() {
        // Devuelve una cadena de texto con los datos del cliente separados por comas
        return DNI + "," + nombre + "," + apellidos + "," + email + "," + id;
    }
}
