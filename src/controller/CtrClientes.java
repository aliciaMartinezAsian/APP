package controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbm.Database;
import model.Cliente;
import view.FrmClientes;

public class CtrClientes {

    // Método para obtener la lista de clientes desde la base de datos
    public List<Cliente> lstClientes() throws Exception {
        List<Cliente> lst = new ArrayList<>();
        Database.open(); // Abre la conexión a la base de datos
        String query = "SELECT * FROM Cliente"; // Consulta SQL para obtener todos los clientes
        ResultSet rs = Database.executeQuery(query); // Ejecuta la consulta y obtiene el ResultSet
        while (rs.next()) { // Itera sobre los resultados
            Cliente p = new Cliente();
            p.setId(rs.getInt("id")); // Establece el ID del cliente
            p.setDni(rs.getString("DNI")); // Establece el DNI del cliente
            p.setNombre(rs.getString("nombre")); // Establece el nombre del cliente
            p.setApellidos(rs.getString("apellidos")); // Establece los apellidos del cliente
            p.setEmail(rs.getString("email")); // Establece el email del cliente
            lst.add(p); // Añade el cliente a la lista
        }
        Database.close(); // Cierra la conexión a la base de datos
        return lst; // Retorna la lista de clientes
    }

    // Método para añadir un nuevo cliente a la base de datos
    public void addCliente() throws Exception {
        // Obtiene los datos del cliente desde los campos de texto en la interfaz
        String str = FrmClientes.txtId.getText();
        Integer id = Integer.parseInt(str);
        String dni = FrmClientes.txtDni.getText();
        String nombre = FrmClientes.txtNombre.getText();
        String apellidos = FrmClientes.txtApellidos.getText();
        String correo = FrmClientes.txtCorreo.getText();
        
        Database.open(); // Abre la conexión a la base de datos

        try {
            Database.initTransacction(); // Inicia una transacción
            Cliente c = new Cliente(id, dni, nombre, apellidos, correo); // Crea un nuevo objeto Cliente

            // Consulta SQL para insertar un nuevo cliente
            String sql = "INSERT INTO Cliente (id,DNI,nombre, apellidos, email) VALUES (?,?,?,?,?)";
            // Ejecuta la consulta con los datos del cliente
            Database.executePreparedDML(sql, c.getId(), c.getDni(), c.getNombre(), c.getApellidos(), c.getEmail());
            Database.commit(); // Confirma la transacción
        } catch (Exception e) {
            Database.rollback(); // Revierte la transacción en caso de error
        }

        Database.close(); // Cierra la conexión a la base de datos
    }

    // Método para eliminar un cliente de la base de datos
    public boolean delPersona() throws Exception {
        // Obtiene el atributo de búsqueda seleccionado en la interfaz
        String atributo = FrmClientes.lstAtributos.getSelectedItem().toString();

        Object valor = null;

        // Asigna el valor de búsqueda dependiendo del atributo seleccionado
        switch (atributo) {
            case "Id" -> valor = Integer.parseInt(FrmClientes.txtBuscar.getText());
            case "DNI" -> valor = FrmClientes.txtBuscar.getText();
            case "Nombre" -> valor = FrmClientes.txtBuscar.getText();
            case "Apellidos" -> valor = FrmClientes.txtBuscar.getText();
            case "Email" -> valor = FrmClientes.txtBuscar.getText();
        }

        boolean exito = true;
        Database.open(); // Abre la conexión a la base de datos

        try {
            Database.initTransacction(); // Inicia una transacción
            // Consulta SQL para eliminar un cliente según el atributo seleccionado
            String sql = "DELETE FROM Cliente WHERE " + atributo + "= ?";
            int filasAfectadas = Database.executePreparedDML(sql, valor); // Ejecuta la consulta
            exito = (filasAfectadas == 1); // Verifica si una fila fue afectada (cliente eliminado)
            Database.commit(); // Confirma la transacción
        } catch (Exception e) {
            Database.rollback(); // Revierte la transacción en caso de error
        }

        Database.close(); // Cierra la conexión a la base de datos

        return exito; // Retorna el éxito de la operación
    }

    // Método para obtener un cliente de la base de datos según un atributo de búsqueda
    public Cliente getCliente() throws Exception {
        // Obtiene el atributo de búsqueda seleccionado en la interfaz
        String atributo = FrmClientes.lstAtributos.getSelectedItem().toString();

        Object valor = null;

        // Asigna el valor de búsqueda dependiendo del atributo seleccionado
        switch (atributo) {
            case "Id" -> valor = Integer.parseInt(FrmClientes.txtBuscar.getText());
            case "DNI" -> valor = FrmClientes.txtBuscar.getText();
            case "Nombre" -> valor = FrmClientes.txtBuscar.getText();
            case "Apellidos" -> valor = FrmClientes.txtBuscar.getText();
            case "Email" -> valor = FrmClientes.txtBuscar.getText();
        }

        Cliente p = null;
        Database.open(); // Abre la conexión a la base de datos
        // Consulta SQL para obtener un cliente según el atributo seleccionado
        String sql = "SELECT * FROM Cliente WHERE " + atributo + "= ?";
        ResultSet rs = Database.executePreparedQuery(sql, valor); // Ejecuta la consulta y obtiene el ResultSet
        while (rs.next()) { // Itera sobre los resultados
            p = new Cliente();
            p.setId(rs.getInt("id")); // Establece el ID del cliente
            p.setDni(rs.getString("DNI")); // Establece el DNI del cliente
            p.setNombre(rs.getString("nombre")); // Establece el nombre del cliente
            p.setApellidos(rs.getString("apellidos")); // Establece los apellidos del cliente
            p.setEmail(rs.getString("email")); // Establece el email del cliente
        }
        Database.close(); // Cierra la conexión a la base de datos
        return p; // Retorna el cliente encontrado
    }

    // Método para obtener los nombres de las columnas de la tabla 'Cliente'
    public String[] obtenerColumnas() throws Exception {
        String[] columnas = null;
        Database.open(); // Abre la conexión a la base de datos

        String sql = "Cliente"; // Nombre de la tabla

        columnas = Database.getColumnNames(sql); // Obtiene los nombres de las columnas

        Database.close(); // Cierra la conexión a la base de datos

        return columnas; // Retorna los nombres de las columnas
    }
}