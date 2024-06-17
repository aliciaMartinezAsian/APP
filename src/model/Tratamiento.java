package model;
/**
 * <h2>Clase Tratamiento para la administración de tratamientos</h2>
 * 
 * @author Alicia Martinez Asian
 * @version 1.0
 * @since 14/06/2024
 */

public class Tratamiento {
    // Atributos del tratamiento
    /**
     * Atributo que hace referencia al id del tratamiento.
     */
    private Integer id;
    /**
     * Atributo que hace referencia al precio del tratamiento.
     */
    private Integer precio;
    /**
     * Atributo que hace referencia a la duración del tratamiento.
     */
    private Integer duracion;
    /**
     * Atributo que hace referencia a la descripción del tratamiento.
     */
    private String descripcion;
    /**
     * Atributo que hace referencia al tipo del tratamiento.
     */
    private String tipo;

    /**
     * <h2> Constructor parametrizado que crea una instancia de Tratamiento pasándole id, precio y tipo. </h2>
     * @param id
     *          Id del tratamiento.
     * @param precio
     *          Precio del tratamiento.
     * @param tipo
     *          Tipo del tratamiento.
     */
    // Constructor con parámetros mínimos (id, precio y tipo)
    public Tratamiento(Integer id, Integer precio, String tipo) {
        super();
        this.id = id;
        this.precio = precio;
        this.tipo = tipo;
    }

    /**
     * <h2> Constructor parametrizado que crea una instancia de Tratamiento pasándole todos los atributos. </h2>
     * @param id
     *          Id del tratamiento.
     * @param precio
     *          Precio del tratamiento.
     * @param duracion
     *          Duración del tratamiento.
     * @param descripcion
     *          Descripción del tratamiento.
     * @param tipo
     *          Tipo del tratamiento.
     */
    // Constructor con todos los parámetros
    public Tratamiento(Integer id, Integer precio, Integer duracion, String descripcion, String tipo) {
        super();
        this.id = id;
        this.precio = precio;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    /**
     * <h2>Constructor por defecto que instancia la clase Tratamiento.</h2>
     */
    // Constructor vacío
    public Tratamiento() {
        // Constructor por defecto sin parámetros
    }

    /**
     * <h2> Devuelve el Id del tratamiento. </h2>
     * @return
     *          Devuelve el Id del tratamiento.
     */
    // Getter para el ID del tratamiento
    public Integer getId() {
        return id;
    }

    /**
     * <h2> Se introduce el Id del tratamiento. </h2>
     * @param id
     *          Id del tratamiento.
     */
    // Setter para el ID del tratamiento
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <h2> Devuelve el precio del tratamiento. </h2>
     * @return
     *          Devuelve el precio del tratamiento.
     */
    // Getter para el precio del tratamiento
    public Integer getPrecio() {
        return precio;
    }

    /**
     * <h2> Se introduce el precio del tratamiento. </h2>
     * @param precio
     *          Precio del tratamiento.
     */
    // Setter para el precio del tratamiento
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    /**
     * <h2> Devuelve la duración del tratamiento. </h2>
     * @return
     *          Devuelve la duración del tratamiento.
     */
    // Getter para la duración del tratamiento
    public Integer getDuracion() {
        return duracion;
    }

    /**
     * <h2> Se introduce la duración del tratamiento. </h2>
     * @param duracion
     *          Duración del tratamiento.
     */
    // Setter para la duración del tratamiento
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    /**
     * <h2> Devuelve la descripción del tratamiento. </h2>
     * @return
     *          Devuelve la descripción del tratamiento.
     */
    // Getter para la descripción del tratamiento
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * <h2> Se introduce la descripción del tratamiento. </h2>
     * @param descripcion
     *          Descripción del tratamiento.
     */
    // Setter para la descripción del tratamiento
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * <h2> Devuelve el tipo del tratamiento. </h2>
     * @return
     *          Devuelve el tipo del tratamiento.
     */
    // Getter para el tipo del tratamiento
    public String getTipo() {
        return tipo;
    }

    /**
     * <h2> Se introduce el tipo del tratamiento. </h2>
     * @param tipo
     *          Tipo del tratamiento.
     */
    // Setter para el tipo del tratamiento
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * <h2> Muestra los atributos del Tratamiento. </h2>
     */
    // Método toString para representar el tratamiento como una cadena de texto
    @Override
    public String toString() {
        // Devuelve una cadena de texto con los datos del tratamiento separados por comas
        return precio + "," + duracion + "," + descripcion + "," + tipo + "," + id;
    }
}
