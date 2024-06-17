package dbm;

import java.io.FileInputStream; 
import java.io.InputStreamReader;
import java.util.Properties;
/**
 * <h2>La clase Config carga la configuración de la base de datos desde un archivo
 * de propiedades y establece la URL de conexión según el tipo de base de datos especificado.</h2>
 *
 * @author Alicia Martinez Asian
 * @version 1.0
 * @since 15/06/2024
 */
public class Config {

    /**
     * Variable que hace referencia a la URL de conexión de la base de datos.
     */
    public static String url = null;

    /**
     * Variable que hace referencia a la ruta del archivo de configuración de la base de datos.
     */
    private final String FILE_CONFIG = "db/configuracion.properties";

    /**
     * Constructor de la clase Config. Carga las propiedades desde el archivo de configuración
     * y establece la URL de conexión de acuerdo con el tipo de base de datos especificado en el archivo.
     *
     * @throws Exception Si ocurre un error al cargar el archivo de configuración o si las propiedades
     *                   necesarias no están presentes en el archivo.
     */
    // Constructor que establece la url de conexión según el tipo de la base de datos y coge la información de un archivo Properties
    public Config() throws Exception {
        Properties prop = new Properties();
        prop.load(new InputStreamReader(new FileInputStream(FILE_CONFIG)));

        String TYPE = prop.getProperty("TYPE");
        String NAME = prop.getProperty("NAME");

        switch (SGBR.fromString(TYPE)) {
            case SQLITE -> url = "jdbc:sqlite:" + NAME;
            case ACCESS -> url = "jdbc:ucanaccess://" + NAME + ";memory=true";
        }
    }
}
