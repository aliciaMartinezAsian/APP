package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbm.Database;

import model.Cliente;
import view.FrmClientes;

public class CtrClientes {

	public List<Cliente> lstClientes() throws Exception {
		List<Cliente> lst = new ArrayList<>();
		Database.open();
		String query = "SELECT * FROM Cliente";
		ResultSet rs = Database.executeQuery(query);
		while (rs.next()) {
			Cliente p = new Cliente();
			p.setId(rs.getInt("id"));
			p.setDni(rs.getString("DNI"));
			p.setNombre(rs.getString("nombre"));
			p.setApellidos(rs.getString("apellidos"));
			p.setEmail(rs.getString("email"));
			lst.add(p);
		}
		Database.close();
		return lst;
	}

	public void addCliente() {

		String str = FrmClientes.txtId.getText();
		Integer id = Integer.parseInt(str);
		String dni = FrmClientes.txtDni.getText().toString();
		String nombre = FrmClientes.txtNombre.getText().toString();
		String apellidos = FrmClientes.txtApellidos.getText().toString();
		String correo = FrmClientes.txtCorreo.getText().toString();

		try {
			Database.open();
			Database.initTransacction();
			Cliente c = new Cliente(id, dni, nombre, apellidos, correo);

			String sql = "INSERT INTO Cliente (id,DNI,nombre, apellidos, email) VALUES (?,?,?,?,?)";
			Database.executePreparedDML(sql, c.getId(), c.getDni(), c.getNombre(), c.getApellidos(), c.getEmail());
			Database.commit();
		} catch (Exception e) {
			// mostrar dialogo de "Cliente no válido y del porqué"

			Database.rollback();

		}

		Database.close();
	}

	public boolean delPersona() {

		Integer num = Integer.parseInt(FrmClientes.txtBuscar.getText().toString());

		boolean exito=true;

		try {

			Database.open();
			Database.initTransacction();
			String sql = "DELETE FROM Cliente WHERE id=?";
			int filasAfectadas = Database.executePreparedDML(sql, num);
			exito = (filasAfectadas == 1);
			Database.commit();
		} catch (Exception e) {

			Database.rollback();

		}

		Database.close();

		return exito;
	}

	public Cliente getCliente(int id) throws Exception {
		Cliente p = null;
		Database.open();
		String sql = "SELECT * FROM Cliente WHERE id=?";
		ResultSet rs = Database.executePreparedQuery(sql, id);
		while (rs.next()) {
			p = new Cliente();
			p.setId(rs.getInt("id"));
			p.setDni(rs.getString("DNI"));
			p.setNombre(rs.getString("nombre"));
			p.setApellidos(rs.getString("apellidos"));
			p.setEmail(rs.getString("email"));
		}
		Database.close();
		return p;
	}

	public String[] obtenerColumnas() {
		String[] columnas = null;
		Database.open();

		String sql = "Cliente";

		try {
			columnas = Database.getColumnNames(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Database.close();

		return columnas;
	}


}
