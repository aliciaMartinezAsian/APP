package dbm;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {

	private static Connection conn = null;

	private Database() {
		try {
			new Config();
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}
	}

	public static Connection open() {
		if (conn == null) {

			new Database();

		}

		try {
			conn = DriverManager.getConnection(Config.url);
		} catch (SQLException e) {
			System.out.println("ERROR " + e.getMessage());
		}
		return conn;

	}

	public static void close() {

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ignore) {
		}
		;

	}

	public static ResultSet executeQuery(String sql) throws SQLException {
		return (conn == null) ? null : conn.createStatement().executeQuery(sql);
	}

	public static int executeDML(String sql) throws SQLException {
		return (conn == null) ? 0 : conn.createStatement().executeUpdate(sql);
	}

	public static boolean executeDDL(String sql) throws SQLException {
		return (conn == null) ? false : conn.createStatement().execute(sql);
	}

	public static DatabaseMetaData getMetaData() throws SQLException {
		return (conn == null) ? null : conn.getMetaData();
	}

	public static String[] getTableNames() throws SQLException {
		List<String> lstTableName = new ArrayList<>();
		try (ResultSet rs = conn.getMetaData().getTables(null, null, null, new String[] { "TABLE" })) {
			while (rs.next()) {
				lstTableName.add(rs.getString("TABLE_NAME"));
			}
		}
		return lstTableName.toArray(new String[0]);
	}

	public static String[] getColumnNames(String sql) throws SQLException {
		if (!sql.trim().contains(" "))
			sql = "SELECT * FROM " + sql;
		try (ResultSet rs = executeQuery(sql)) {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			String[] names = new String[colCount];
			for (int i = 1; i <= colCount; i++) {
				names[i - 1] = rsmd.getColumnName(i);
			}
			return names;
		}
	}

	public static int rows(String sql) throws SQLException {
		int rowCount = 0;
		if (!sql.trim().contains(" "))
			sql = "SELECT * FROM " + sql;
		try (ResultSet rs = executeQuery(sql)) {
			while (rs.next()) {
				rowCount++;
			}
		}
		return rowCount;
	}

	public static ResultSet executePreparedQuery(String sql, Object... parametros) throws SQLException {
		ResultSet filasDevueltas = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int index = 1;
			for (Object parametro : parametros) {
				pstmt.setObject(index++, parametro);
			}
			filasDevueltas = pstmt.executeQuery();
		}
		return filasDevueltas;
	}

	public static int executePreparedDML(String sql, Object... parametros) throws SQLException {
		int filasAfectadas = 0;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int index = 1;
			for (Object parametro : parametros) {
				pstmt.setObject(index++, parametro);
			}
			filasAfectadas = pstmt.executeUpdate();
		}
		return filasAfectadas;
	}

//	public static ResultSet obtenerRegistros(String tabla) throws SQLException {
//		String sql = "SELECT * FROM " + tabla;
//
//		ResultSet rs = conn.createStatement().executeQuery(sql);
//
//		return rs;
//
//	}

	public static void initTransacction() {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("ERROR " + e.getMessage());
		}

	}

	public static void commit() throws SQLException {
		conn.commit();

	}

	public static void rollback() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			
		}

	}

}
