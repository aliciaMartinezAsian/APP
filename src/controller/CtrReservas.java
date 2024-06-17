package controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.Database;
import model.Reserva;
import view.FrmReservas;

/**
 * <h2>Clase CtrReservas para la gestión de reservas en la base de datos</h2>
 * 
 * @autor Alicia Martínez Asián
 * @version 1.0
 * @since 16/06/2024
 */
public class CtrReservas {

    /**
     * <h2>Método para obtener la lista de reservas desde la base de datos.</h2>
     * @return Lista de reservas.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    public List<Reserva> lstReservas() throws Exception {
        List<Reserva> lst = new ArrayList<>();
        Database.open(); // Abre la conexión a la base de datos
        String query = "SELECT * FROM Reservas"; // Consulta SQL para obtener todas las reservas
        ResultSet rs = Database.executeQuery(query); // Ejecuta la consulta y obtiene el ResultSet
        while (rs.next()) { // Itera sobre los resultados
            Reserva r = new Reserva();
            r.setIdTratamiento(rs.getInt("IdTratamiento")); // Establece el ID del tratamiento
            r.setIdCliente(rs.getInt("IdCliente")); // Establece el ID del cliente
            r.setHorario(rs.getString("Horario")); // Establece el horario de la reserva
            lst.add(r); // Añade la reserva a la lista
        }
        Database.close(); // Cierra la conexión a la base de datos
        return lst; // Retorna la lista de reservas
    }

    /**
     * <h2>Método para añadir una nueva reserva a la base de datos.</h2>
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    public void addReserva() throws Exception {
        // Obtiene los datos de la reserva desde los campos de texto en la interfaz
        String str = FrmReservas.txtIdTratamiento.getText();
        Integer idTratamiento = Integer.parseInt(str);
        Integer idCliente = Integer.parseInt(FrmReservas.txtIdCliente.getText());
        String horario = FrmReservas.txtHorario.getText();

        Database.open(); // Abre la conexión a la base de datos

        try {
            Database.initTransaction(); // Inicia una transacción
            Reserva r = new Reserva(idTratamiento, idCliente, horario); // Crea un nuevo objeto Reserva

            // Consulta SQL para insertar una nueva reserva
            String sql = "INSERT INTO Reservas (IdTratamiento, IdCliente, horario) VALUES (?,?,?)";
            // Ejecuta la consulta con los datos de la reserva
            Database.executePreparedDML(sql, r.getIdTratamiento(), r.getIdCliente(), r.getHorario());
            Database.commit(); // Confirma la transacción
        } catch (Exception e) {
            Database.rollback(); // Revierte la transacción en caso de error
        }

        Database.close(); // Cierra la conexión a la base de datos
    }

    /**
     * <h2>Método para eliminar una reserva de la base de datos.</h2>
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    public boolean delReserva() throws Exception {
        // Obtiene el atributo de búsqueda seleccionado en la interfaz
        String atributo = FrmReservas.lstAtributos.getSelectedItem().toString();

        Object valor = null;

        // Asigna el valor de búsqueda dependiendo del atributo seleccionado
        switch (atributo) {
            case "IdTratamiento" -> valor = Integer.parseInt(FrmReservas.txtBuscar.getText());
            case "IdCliente" -> valor = Integer.parseInt(FrmReservas.txtBuscar.getText());
            case "Horario" -> valor = FrmReservas.txtBuscar.getText();
        }

        boolean exito = true;
        Database.open(); // Abre la conexión a la base de datos

        try {
            Database.initTransaction(); // Inicia una transacción
            // Consulta SQL para eliminar una reserva según el atributo seleccionado
            String sql = "DELETE FROM Reservas WHERE " + atributo + "= ?";
            int filasAfectadas = Database.executePreparedDML(sql, valor); // Ejecuta la consulta
            exito = (filasAfectadas == 1); // Verifica si una fila fue afectada (reserva eliminada)
            Database.commit(); // Confirma la transacción
        } catch (Exception e) {
            Database.rollback(); // Revierte la transacción en caso de error
        }

        Database.close(); // Cierra la conexión a la base de datos

        return exito; // Retorna el éxito de la operación
    }

    /**
     * <h2>Método para obtener una reserva de la base de datos según un atributo de búsqueda.</h2>
     * @return La reserva encontrada.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    public Reserva getReserva() throws Exception {
        // Obtiene el atributo de búsqueda seleccionado en la interfaz
        String atributo = FrmReservas.lstAtributos.getSelectedItem().toString();

        Object valor = null;

        // Asigna el valor de búsqueda dependiendo del atributo seleccionado
        switch (atributo) {
            case "IdTratamiento" -> valor = Integer.parseInt(FrmReservas.txtBuscar.getText());
            case "IdCliente" -> valor = Integer.parseInt(FrmReservas.txtBuscar.getText());
            case "Horario" -> valor = FrmReservas.txtBuscar.getText();
        }

        Reserva r = null;
        Database.open(); // Abre la conexión a la base de datos
        // Consulta SQL para obtener una reserva según el atributo seleccionado
        String sql = "SELECT * FROM Reservas WHERE " + atributo + "= ?";
        ResultSet rs = Database.executePreparedQuery(sql, valor); // Ejecuta la consulta y obtiene el ResultSet
        while (rs.next()) { // Itera sobre los resultados
            r = new Reserva();
            r.setIdTratamiento(rs.getInt("IdTratamiento")); // Establece el ID del tratamiento
            r.setIdCliente(rs.getInt("IdCliente")); // Establece el ID del cliente
            r.setHorario(rs.getString("Horario")); // Establece el horario de la reserva
        }
        Database.close(); // Cierra la conexión a la base de datos
        return r; // Retorna la reserva encontrada
    }

    /**
     * <h2>Método para obtener los nombres de las columnas de la tabla Reservas.</h2>
     * @return Un array con los nombres de las columnas.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    public String[] obtenerColumnas() throws Exception {
        String[] columnas = null;
        Database.open(); // Abre la conexión a la base de datos

        String sql = "Reservas"; // Nombre de la tabla

        columnas = Database.getColumnNames(sql); // Obtiene los nombres de las columnas

        Database.close(); // Cierra la conexión a la base de datos

        return columnas; // Retorna los nombres de las columnas
    }
}

