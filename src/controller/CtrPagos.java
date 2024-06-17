package controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.Database;
import model.Pago;
import view.FrmPagos;


/**
 * <h2>Clase CtrPagos para la gestión de pagos en la base de datos</h2>
 * 
 * @autor Alicia Martínez Asián
 * @version 1.0
 * @since 16/06/2024
 */
public class CtrPagos {

    /**
     * <h2>Método para obtener la lista de pagos desde la base de datos.</h2>
     * @return Lista de pagos.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    // Método para obtener la lista de pagos desde la base de datos.
    public List<Pago> lstPagos() throws Exception {
        List<Pago> lst = new ArrayList<>();
        Database.open(); // Abre la conexión a la base de datos
        String query = "SELECT P.*, C.* FROM Pago P JOIN Cliente C ON P.idCliente = C.id"; // Consulta SQL para obtener todos los pagos
        
        ResultSet rs = Database.executeQuery(query); // Ejecuta la consulta y obtiene el ResultSet
        while (rs.next()) { // Itera sobre los resultados
            Pago p = new Pago();
            p.setIdPago(rs.getString("IdPago")); // Establece el ID del pago
            p.setFecha(rs.getString("fecha")); // Establece la fecha del pago
            p.setCantidad(rs.getInt("cantidad")); // Establece la cantidad del pago
            p.setMetodoPago(rs.getString("metodoPago")); // Establece el método de pago
            p.setIdCliente(rs.getInt("idCliente")); // Establece el ID del cliente
            lst.add(p); // Añade el pago a la lista
        }
        Database.close(); // Cierra la conexión a la base de datos
        return lst; // Retorna la lista de pagos
    }

    /**
     * <h2>Método para añadir un nuevo pago a la base de datos.</h2>
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    // Método para añadir un nuevo pago a la base de datos.
    public void addPago() throws Exception {
        // Obtiene los datos del pago desde los campos de texto en la interfaz
        String id = FrmPagos.txtId.getText();
        String fecha = FrmPagos.txtFecha.getText();
        Integer cantidad = Integer.parseInt(FrmPagos.txtCantidad.getText());
        String metodoPago = FrmPagos.txtMetodoPago.getText();
        Integer idCliente = Integer.parseInt(FrmPagos.txtIdCliente.getText());

        Database.open(); // Abre la conexión a la base de datos

        try {
            Database.initTransaction(); // Inicia una transacción
            Pago p = new Pago(id, fecha, cantidad, metodoPago, idCliente); // Crea un nuevo objeto Pago

            // Consulta SQL para insertar un nuevo pago
            String sql = "INSERT INTO Pago (cantidad, metodoPago, idCliente, IdPago, fecha) VALUES (?, ?, ?, ?, ?)";
            // Ejecuta la consulta con los datos del pago
            Database.executePreparedDML(sql, p.getCantidad(), p.getMetodoPago(), p.getIdCliente(), p.getIdPago(), p.getFecha());
            Database.commit(); // Confirma la transacción
        } catch (Exception e) {
            Database.rollback(); // Revierte la transacción en caso de error
        }

        Database.close(); // Cierra la conexión a la base de datos
    }

    /**
     * <h2>Método para eliminar un pago de la base de datos.</h2>
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    // Método para eliminar un pago de la base de datos.
    public boolean delPago() throws Exception {
        // Obtiene el atributo de búsqueda seleccionado en la interfaz
        String atributo = FrmPagos.lstAtributos.getSelectedItem().toString();

        Object valor = null;

        // Asigna el valor de búsqueda dependiendo del atributo seleccionado
        switch (atributo) {
            case "IdPago" -> valor = FrmPagos.txtBuscar.getText();
            case "Fecha" -> valor = FrmPagos.txtBuscar.getText();
            case "Cantidad" -> valor = Integer.parseInt(FrmPagos.txtBuscar.getText());
            case "MetodoPago" -> valor = FrmPagos.txtBuscar.getText();
            case "IdCliente" -> valor = Integer.parseInt(FrmPagos.txtBuscar.getText());
        }

        boolean exito = true;
        Database.open(); // Abre la conexión a la base de datos

        try {
            Database.initTransaction(); // Inicia una transacción
            // Consulta SQL para eliminar un pago según el atributo seleccionado
            String sql = "DELETE FROM Pago WHERE " + atributo + "= ?";
            int filasAfectadas = Database.executePreparedDML(sql, valor); // Ejecuta la consulta
            exito = (filasAfectadas == 1); // Verifica si una fila fue afectada (pago eliminado)
            Database.commit(); // Confirma la transacción
        } catch (Exception e) {
            Database.rollback(); // Revierte la transacción en caso de error
        }

        Database.close(); // Cierra la conexión a la base de datos

        return exito; // Retorna el éxito de la operación
    }

    /**
     * <h2>Método para obtener un pago de la base de datos según un atributo de búsqueda.</h2>
     * @return El pago encontrado.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    // Método para obtener un pago de la base de datos según un atributo de búsqueda.
    public Pago getPago() throws Exception {
        // Obtiene el atributo de búsqueda seleccionado en la interfaz
        String atributo = FrmPagos.lstAtributos.getSelectedItem().toString();

        Object valor = null;

        // Asigna el valor de búsqueda dependiendo del atributo seleccionado
        switch (atributo) {
            case "IdPago" -> valor = FrmPagos.txtBuscar.getText();
            case "Fecha" -> valor = FrmPagos.txtBuscar.getText();
            case "Cantidad" -> valor = Integer.parseInt(FrmPagos.txtBuscar.getText());
            case "MetodoPago" -> valor = FrmPagos.txtBuscar.getText();
            case "IdCliente" -> valor = Integer.parseInt(FrmPagos.txtBuscar.getText());
        }

        Pago p = null;
        Database.open(); // Abre la conexión a la base de datos
        // Consulta SQL para obtener un pago según el atributo seleccionado
        String sql = "SELECT * FROM Pago WHERE " + atributo + "= ?";
        ResultSet rs = Database.executePreparedQuery(sql, valor); // Ejecuta la consulta y obtiene el ResultSet
        while (rs.next()) { // Itera sobre los resultados
            p = new Pago();
            p.setIdPago(rs.getString("IdPago")); // Establece el ID del pago
            p.setFecha(rs.getString("fecha")); // Establece la fecha del pago
            p.setCantidad(rs.getInt("cantidad")); // Establece la cantidad del pago
            p.setMetodoPago(rs.getString("metodoPago")); // Establece el método de pago
            p.setIdCliente(rs.getInt("idCliente")); // Establece el ID del cliente
        }
        Database.close(); // Cierra la conexión a la base de datos
        return p; // Retorna el pago encontrado
    }

    /**
     * <h2>Método para obtener los nombres de las columnas de la tabla Pago.</h2>
     * @return Un array con los nombres de las columnas.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    // Método para obtener los nombres de las columnas de la tabla Pago.
    public String[] obtenerColumnas() throws Exception {
        String[] columnas = null;
        Database.open(); // Abre la conexión a la base de datos

        String sql = "Pago"; // Nombre de la tabla

        columnas = Database.getColumnNames(sql); // Obtiene los nombres de las columnas

        Database.close(); // Cierra la conexión a la base de datos

        return columnas; // Retorna los nombres de las columnas
    }
}
