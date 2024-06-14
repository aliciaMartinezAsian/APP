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

	private Database() throws Exception {

		new Config();

	}

	public static Connection open() throws Exception {
		if (conn == null) {

			new Database();

		}

		conn = DriverManager.getConnection(Config.url);

		return conn;

	}

	public static void close() throws SQLException {

		if (conn != null) {
			conn.close();
		}

	}

	public static ResultSet executeQuery(String sql) throws Exception {
		return (conn == null) ? null : conn.createStatement().executeQuery(sql);
	}

	public static int executeDML(String sql) throws Exception {
		return (conn == null) ? 0 : conn.createStatement().executeUpdate(sql);
	}

	public static boolean executeDDL(String sql) throws Exception {
		return (conn == null) ? false : conn.createStatement().execute(sql);
	}

	public static DatabaseMetaData getMetaData() throws Exception {
		return (conn == null) ? null : conn.getMetaData();
	}

	public static String[] getTableNames() throws Exception {
		List<String> lstTableName = new ArrayList<>();
		try (ResultSet rs = conn.getMetaData().getTables(null, null, null, new String[] { "TABLE" })) {
			while (rs.next()) {
				lstTableName.add(rs.getString("TABLE_NAME"));
			}
		}
		return lstTableName.toArray(new String[0]);
	}

	public static String[] getColumnNames(String sql) throws Exception {
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

	public static int rows(String sql) throws Exception {
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

	public static ResultSet executePreparedQuery(String sql, Object... parametros) throws Exception {
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

	public static int executePreparedDML(String sql, Object... parametros) throws Exception {
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

	public static void initTransacction() throws Exception {

		conn.setAutoCommit(false);

	}

	public static void commit() throws Exception {
		conn.commit();

	}

	public static void rollback() throws Exception {

		conn.rollback();

	}

}
