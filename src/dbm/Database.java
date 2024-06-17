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

/**
 * <h2>Clase de utilidad para manejar operaciones comunes de base de datos. Esta
 * clase utiliza un patrón singleton para mantener una única conexión a la base
 * de datos.</h2>
 *
 * @author Alicia Martinez Asian
 * @version 1.0
 * @since 15/06/2024
 */

public class Database {
	/**
	 * Atributo que hace referencia a la conexión con la base de datos.
	 */
	private static Connection conn = null;

	/**
	 * <h2>Constructor privado para evitar la instanción fuera de la clase</h2>
	 * 
	 * @throws Exeption Si ocurrre un error al cargar la configuración
	 */
	// Constructor privado para evitar la instanciación fuera de la clase
	private Database() throws Exception {
		// Cargar la configuración
		new Config();
	}

	/**
	 * <h2>Abre una conexión a la base de datos si no está ya abierta.</h2>
	 *
	 * @return La conexión a la base de datos
	 * @throws Exception Si ocurre un error al abrir la conexión
	 */
	// Método para abrir la conexión con la base de datos
	public static Connection open() throws Exception {
		if (conn == null) {
			new Database(); // Inicializar la configuración de la base de datos
		}
		conn = DriverManager.getConnection(Config.url); // Establecer la conexión
		return conn;
	}

	/**
	 * <h2>Cierra la conexión a la base de datos si está abierta.</h2>
	 *
	 * @throws SQLException Si ocurre un error al cerrar la conexión
	 */
	// Método para cerrar la conexión con la base de datos
	public static void close() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * <h2>Ejecuta una consulta SQL y devuelve el conjunto de resultados.</h2>
	 *
	 * @param sql La cadena de consulta SQL
	 * @return Conjunto de resultados de la consulta
	 * @throws Exception Si ocurre un error al ejecutar la consulta
	 */
	// Método para ejecutar una consulta SQL
	public static ResultSet executeQuery(String sql) throws Exception {
		return (conn == null) ? null : conn.createStatement().executeQuery(sql);
	}

	/**
	 * <h2>Ejecuta una declaración DML (Lenguaje de Manipulación de Datos) SQL.</h2>
	 *
	 * @param sql Declaración SQL
	 * @return Número de filas afectadas
	 * @throws Exception Si ocurre un error al ejecutar la declaración
	 */
	// Método para ejecutar una declaración DML
	public static int executeDML(String sql) throws Exception {
		return (conn == null) ? 0 : conn.createStatement().executeUpdate(sql);
	}

	/**
	 * <h2>Ejecuta una declaración DDL (Lenguaje de Definición de Datos) SQL.</h2>
	 *
	 * @param sql Declaración SQL
	 * @return True si la declaración devuelve un ResultSet, false en caso contrario
	 * @throws Exception Si ocurre un error al ejecutar la declaración
	 */
	// Método para ejecutar una declaración DDL
	public static boolean executeDDL(String sql) throws Exception {
		return (conn == null) ? false : conn.createStatement().execute(sql);
	}

	/**
	 * <h2>Devuelve los metadatos de la base de datos.</h2>
	 *
	 * @return Metadatos de la base de datos
	 * @throws Exception s Si ocurre un error al recuperar los metadatos
	 */
	// Método que devuelve los metadatos de la base de datos.
	public static DatabaseMetaData getMetaData() throws Exception {
		return (conn == null) ? null : conn.getMetaData();
	}

	/**
	 * <h2>Devuelve los nombres de todas las tablas en la base de datos.</h2>
	 *
	 * @return Array de nombres de tablas
	 * @throws Exception Si ocurre un error al recuperar los nombres de las tablas
	 */
	// Método que devuelve los nombres de todas las tablas en la base de datos.
	public static String[] getTableNames() throws Exception {
		List<String> lstTableName = new ArrayList<>();
		try (ResultSet rs = conn.getMetaData().getTables(null, null, null, new String[] { "TABLE" })) {
			while (rs.next()) {
				lstTableName.add(rs.getString("TABLE_NAME"));
			}
		}
		return lstTableName.toArray(new String[0]);
	}

	/**
	 * <h2>Devuelve los nombres de las columnas de una tabla indicada.</h2>
	 *
	 * @param sql Nombre de la tabla
	 * @return Array de nombres de columnas
	 * @throws Exception Si ocurre un error al recuperar los nombres de las columnas
	 */
	// Devuelve los nombres de las columnas de una tabla indicada
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

	/**
	 * <h2>Cuenta el número de filas en una tabla.</h2>
	 *
	 * @param sql Nombre de la tabla
	 * @return Número de filas
	 * @throws Exception Si ocurre un error al contar las filas
	 */
	// Método que cuenta el número de filas de una tabla.
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

	/**
	 * <h2<Ejecuta una consulta SQL preparada con parámetros y devuelve el conjunto
	 * de resultados.</h2>
	 *
	 * @param sql Cadena de consulta SQL
	 * @param parametros Los parámetros para la declaración preparada
	 * @return Conjunto de resultados de la consulta
	 * @throws Exception Si ocurre un error al ejecutar la consulta
	 */
	// Método que ejecuta una consulta SQL preparada con parámetros
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

	/**
	 * <h2>Ejecuta una declaración DML SQL preparada con parámetros.</h2>
	 *
	 * @param sql La declaración SQL
	 * @param parametros Los parámetros para la declaración preparada
	 * @return Número de filas afectadas
	 * @throws Exception Si ocurre un error al ejecutar la declaración
	 */
	// Método que ejecuta una declaración DML preparada con parámetros
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

	/**
	 * <h2>Inicializa una transacción de base de datos configurando auto-commit a
	 * false.</h2>
	 *
	 * @throws Exception Si ocurre un error al iniciar la transacción
	 */
	// Método que inicializa una transacción
	public static void initTransaction() throws Exception {
		conn.setAutoCommit(false);
	}

	/**
	 * <h2>Realiza un commit de la transacción actual de la base de datos.</h2>
	 *
	 * @throws Exception Si ocurre un error al hacer commit de la transacción
	 */
	// Método que realiza un commit
	public static void commit() throws Exception {
		conn.commit();
	}

	/**
	 * <h2>Realiza un rollback de la transacción actual de la base de datos.</h2>
	 *
	 * @throws Exception Si ocurre un error al hacer rollback de la transacción
	 */
	// Método que realiza un rollback
	public static void rollback() throws Exception {
		conn.rollback();
	}

}
