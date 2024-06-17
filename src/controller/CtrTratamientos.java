package controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.Database;
import model.Tratamiento;
import view.FrmTratamientos;

/**
 * <h2>Clase CtrTratamientos para la gestión de tratamientos en la base de datos</h2>
 * 
 * @autor Alicia Martínez Asián
 * @version 1.0
 * @since 16/06/2024
 */
public class CtrTratamientos {

    /**
     * <h2>Método para obtener la lista de tratamientos desde la base de datos.</h2>
     * @return Lista de tratamientos.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    // Método para obtener la lista de tratamientos desde la base de datos
    public List<Tratamiento> lstTratamientos() throws Exception {
        List<Tratamiento> lst = new ArrayList<>();
        Database.open(); // Abre la conexión a la base de datos
        String query = "SELECT * FROM Tratamiento"; // Consulta SQL para obtener todos los tratamientos
        ResultSet rs = Database.executeQuery(query); // Ejecuta la consulta y obtiene el ResultSet
        while (rs.next()) { // Itera sobre los resultados
            Tratamiento t = new Tratamiento();
            t.setId(rs.getInt("id")); // Establece el ID del tratamiento
            t.setPrecio(rs.getInt("precio")); // Establece el precio del tratamiento
            t.setDuracion(rs.getInt("duracion")); // Establece la duración del tratamiento
            t.setDescripcion(rs.getString("descripcion")); // Establece la descripción del tratamiento
            t.setTipo(rs.getString("tipo")); // Establece el tipo del tratamiento
            lst.add(t); // Añade el tratamiento a la lista
        }
        Database.close(); // Cierra la conexión a la base de datos
        return lst; // Retorna la lista de tratamientos
    }

    /**
     * <h2>Método para añadir un nuevo tratamiento a la base de datos.</h2>
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    // Método para añadir un nuevo tratamiento a la base de datos
    public void addTratamiento() throws Exception {
        // Obtiene los datos del tratamiento desde los campos de texto en la interfaz
        String str = FrmTratamientos.txtId.getText();
        Integer id = Integer.parseInt(str);
        Integer precio = Integer.parseInt(FrmTratamientos.txtPrecio.getText());
        Integer duracion = Integer.parseInt(FrmTratamientos.txtDuracion.getText());
        String descripcion = FrmTratamientos.txtDescripcion.getText();
        String tipo = FrmTratamientos.txtTipo.getText();

        Database.open(); // Abre la conexión a la base de datos

        try {
            Database.initTransaction(); // Inicia una transacción
            Tratamiento t = new Tratamiento(id, precio, duracion, descripcion, tipo); // Crea un nuevo objeto Tratamiento

            // Consulta SQL para insertar un nuevo tratamiento
            String sql = "INSERT INTO Tratamiento (id, precio, duracion, descripcion, tipo) VALUES (?,?,?,?,?)";
            // Ejecuta la consulta con los datos del tratamiento
            Database.executePreparedDML(sql, t.getId(), t.getPrecio(), t.getDuracion(), t.getDescripcion(), t.getTipo());
            Database.commit(); // Confirma la transacción
        } catch (Exception e) {
            Database.rollback(); // Revierte la transacción en caso de error
        }

        Database.close(); // Cierra la conexión a la base de datos
    }

    /**
     * <h2>Método para eliminar un tratamiento de la base de datos.</h2>
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    // Método para eliminar un tratamiento de la base de datos
    public boolean delTratamiento() throws Exception {
        // Obtiene el atributo de búsqueda seleccionado en la interfaz
        String atributo = FrmTratamientos.lstAtributos.getSelectedItem().toString();

        Object valor = null;

        // Asigna el valor de búsqueda dependiendo del atributo seleccionado
        switch (atributo) {
            case "Id" -> valor = Integer.parseInt(FrmTratamientos.txtBuscar.getText());
            case "Precio" -> valor = Integer.parseInt(FrmTratamientos.txtBuscar.getText());
            case "Duracion" -> valor = Integer.parseInt(FrmTratamientos.txtBuscar.getText());
            case "Descripcion" -> valor = FrmTratamientos.txtBuscar.getText();
            case "Tipo" -> valor = FrmTratamientos.txtBuscar.getText();
        }

        boolean exito = true;
        Database.open(); // Abre la conexión a la base de datos

        try {
            Database.initTransaction(); // Inicia una transacción
            // Consulta SQL para eliminar un tratamiento según el atributo seleccionado
            String sql = "DELETE FROM Tratamiento WHERE " + atributo + "= ?";
            int filasAfectadas = Database.executePreparedDML(sql, valor); // Ejecuta la consulta
            exito = (filasAfectadas == 1); // Verifica si una fila fue afectada (tratamiento eliminado)
            Database.commit(); // Confirma la transacción
        } catch (Exception e) {
            Database.rollback(); // Revierte la transacción en caso de error
        }

        Database.close(); // Cierra la conexión a la base de datos

        return exito; // Retorna el éxito de la operación
    }

    /**
     * <h2>Método para obtener un tratamiento de la base de datos según un atributo de búsqueda.</h2>
     * @return El tratamiento encontrado.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    // Método para obtener un tratamiento de la base de datos según un atributo de búsqueda
    public Tratamiento getTratamiento() throws Exception {
        // Obtiene el atributo de búsqueda seleccionado en la interfaz
        String atributo = FrmTratamientos.lstAtributos.getSelectedItem().toString();

        Object valor = null;

        // Asigna el valor de búsqueda dependiendo del atributo seleccionado
        switch (atributo) {
            case "Id" -> valor = Integer.parseInt(FrmTratamientos.txtBuscar.getText());
            case "Precio" -> valor = Integer.parseInt(FrmTratamientos.txtBuscar.getText());
            case "Duracion" -> valor = Integer.parseInt(FrmTratamientos.txtBuscar.getText());
            case "Descripcion" -> valor = FrmTratamientos.txtBuscar.getText();
            case "Tipo" -> valor = FrmTratamientos.txtBuscar.getText();
        }

        Tratamiento t = null;
        Database.open(); // Abre la conexión a la base de datos
        // Consulta SQL para obtener un tratamiento según el atributo seleccionado
        String sql = "SELECT * FROM Tratamiento WHERE " + atributo + "= ?";
        ResultSet rs = Database.executePreparedQuery(sql, valor); // Ejecuta la consulta y obtiene el ResultSet
        while (rs.next()) { // Itera sobre los resultados
            t = new Tratamiento();
            t.setId(rs.getInt("id")); // Establece el ID del tratamiento
            t.setPrecio(rs.getInt("precio")); // Establece el precio del tratamiento
            t.setDuracion(rs.getInt("duracion")); // Establece la duración del tratamiento
            t.setDescripcion(rs.getString("descripcion")); // Establece la descripción del tratamiento
            t.setTipo(rs.getString("tipo")); // Establece el tipo del tratamiento
        }
        Database.close(); // Cierra la conexión a la base de datos
        return t; // Retorna el tratamiento encontrado
    }

    /**
     * <h2>Método para obtener los nombres de las columnas de la tabla Tratamiento.</h2>
     * @return Un array con los nombres de las columnas.
     * @throws Exception Si ocurre algún error al acceder a la base de datos.
     */
    // Método para obtener los nombres de las columnas de la tabla Tratamiento
    public String[] obtenerColumnas() throws Exception {
        String[] columnas = null;
        Database.open(); // Abre la conexión a la base de datos

        String sql = "Tratamiento"; // Nombre de la tabla

        columnas = Database.getColumnNames(sql); // Obtiene los nombres de las columnas

        Database.close(); // Cierra la conexión a la base de datos

        return columnas; // Retorna los nombres de las columnas
    }
}

