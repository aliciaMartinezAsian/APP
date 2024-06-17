package model;

/**
 * <h2>Clase Pago para la administración de estos</h2>
 * 
 * @autor Alicia Martinez Asian
 * @version 1.0
 * @since 14/06/2024
 */
public class Pago {
    // Atributos del pago
	/**
	 * Atributo que hace referencia al id del pago.
	 */
    private String idPago;
	/**
	 * Atributo que hace referencia a la fecha del pago.
	 */
    private String fecha;
	/**
	 * Atributo que hace referencia a la cantidad del pago.
	 */
    private Integer cantidad;
	/**
	 * Atributo que hace referencia al método de pago.
	 */
    private String metodoPago;
	/**
	 * Atributo que hace referencia al id del cliente que realizó el pago.
	 */
    private Integer idCliente;

    /**
     * <h2>Constructor parametrizado que crea una instancia de Pago pasándole id, fecha, cantidad y id del cliente.</h2>
     * @param id
     *          Id del pago.
     * @param fecha
     *          Fecha del pago.
     * @param cantidad
     *          Cantidad del pago.
     * @param idCliente
     *          Id del cliente que realizó el pago.
     */
    public Pago(String id, String fecha, Integer cantidad, Integer idCliente) {
        super();
        this.idPago = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.idCliente = idCliente;
    }

    /**
     * <h2>Constructor parametrizado que crea una instancia de Pago pasándole todos los atributos.</h2>
     * @param id
     *          Id del pago.
     * @param fecha
     *          Fecha del pago.
     * @param cantidad
     *          Cantidad del pago.
     * @param metodoPago
     *          Método de pago.
     * @param idCliente
     *          Id del cliente que realizó el pago.
     */
    public Pago(String id, String fecha, Integer cantidad, String metodoPago, Integer idCliente) {
        super();
        this.idPago = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.metodoPago = metodoPago;
        this.idCliente = idCliente;
    }

    /**
     * <h2>Constructor por defecto que instancia la clase Pago.</h2>
     */
    public Pago() {
        // Constructor por defecto sin parámetros
    }

    /**
     * <h2>Devuelve el Id del pago.</h2>
     * @return
     *         Devuelve el Id del pago.
     */
    public String getIdPago() {
        return idPago;
    }

    /**
     * <h2>Se introduce el Id del pago.</h2>
     * @param id
     *          Id del pago.
     */
    public void setIdPago(String id) {
        this.idPago = id;
    }

    /**
     * <h2>Devuelve la fecha del pago.</h2>
     * @return
     *         Devuelve la fecha del pago.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * <h2>Se introduce la fecha del pago.</h2>
     * @param fecha
     *          Fecha del pago.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * <h2>Devuelve la cantidad del pago.</h2>
     * @return
     *         Devuelve la cantidad del pago.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * <h2>Se introduce la cantidad del pago.</h2>
     * @param cantidad
     *          Cantidad del pago.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * <h2>Devuelve el método de pago.</h2>
     * @return
     *         Devuelve el método de pago.
     */
    public String getMetodoPago() {
        return metodoPago;
    }

    /**
     * <h2>Se introduce el método de pago.</h2>
     * @param metodoPago
     *          Método de pago.
     */
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * <h2>Devuelve el Id del cliente que realizó el pago.</h2>
     * @return
     *         Devuelve el Id del cliente que realizó el pago.
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * <h2>Se introduce el Id del cliente que realizó el pago.</h2>
     * @param idCliente
     *          Id del cliente que realizó el pago.
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * <h2>Muestra los atributos del Pago.</h2>
     */
    @Override
    public String toString() {
        // Devuelve una cadena de texto con los datos del pago separados por comas
        return cantidad + "," + metodoPago + "," + idCliente + "," + idPago + "," + fecha;
    }
}

