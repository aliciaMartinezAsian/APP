package model;

/**
 * <h2>Clase Reserva para la administración de reservas de tratamientos</h2>
 * 
 * @autor Alicia Martinez Asian
 * @version 1.0
 * @since 14/06/2024
 */

public class Reserva {
    // Atributos de la reserva
    /**
     * Atributo que hace referencia al id del tratamiento.
     */
    private Integer idTratamiento;
    /**
     * Atributo que hace referencia al id del cliente.
     */
    private Integer idCliente;
    /**
     * Atributo que hace referencia al horario de la reserva.
     */
    private String horario;
    
    /**
     * <h2> Constructor parametrizado que crea una instancia de Reserva pasándole idTratamiento, idCliente y horario. </h2>
     * @param idTratamiento
     *          Id del tratamiento.
     * @param idCliente
     *          Id del cliente.
     * @param horario
     *          Horario de la reserva.
     */
    // Constructor con parámetros (idTratamiento, idCliente, horario)
    public Reserva(Integer idTratamiento, Integer idCliente, String horario) {
        super();
        this.idTratamiento = idTratamiento;
        this.idCliente = idCliente;
        this.horario = horario;
    }
    
    /**
     * <h2> Constructor por defecto que instancia la clase Reserva. </h2>
     */
    // Constructor vacío
    public Reserva() {
        // Constructor por defecto sin parámetros
    }
    
    /**
     * <h2> Devuelve el Id del tratamiento. </h2>
     * @return
     *          Devuelve el Id del tratamiento.
     */
    // Getter para el ID del tratamiento
    public Integer getIdTratamiento() {
        return idTratamiento;
    }
    
    /**
     * <h2> Se introduce el Id del Tratamiento. </h2>
     * @param idTratamiento
     *          Id del tratamiento.
     */
    // Setter para el ID del tratamiento
    public void setIdTratamiento(Integer idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    /**
     * <h2> Devuelve el Id del cliente. </h2>
     * @return
     *          Devuelve el Id del cliente.
     */
    // Getter para el ID del cliente
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * <h2> Se introduce el Id del Cliente. </h2>
     * @param idCliente
     *          Id del cliente.
     */
    // Setter para el ID del cliente
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * <h2> Devuelve el Horario de la reserva. </h2>
     * @return
     *          Devuelve el Horario de la reserva.
     */
    // Getter para el horario de la reserva
    public String getHorario() {
        return horario;
    }

    /**
     * <h2> Se introduce el Horario de la Reserva. </h2>
     * @param horario
     *          Horario de la reserva.
     */
    // Setter para el horario de la reserva
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * <h2> Muestra los atributos de la Reserva. </h2>
     */
    // Método toString para representar la reserva como una cadena de texto
    @Override
    public String toString() {
        // Devuelve una cadena de texto con los datos de la reserva separados por comas
        return idTratamiento + "," + idCliente + "," + horario;
    }
}
