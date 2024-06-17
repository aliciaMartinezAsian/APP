package dbm;
/**
 * <h2>Esta clase enumera los tipos de Sistemas Gestores de Bases de Datos Relacionales (SGBDR)
 * soportados y proporciona una función para convertir cadenas de texto en valores de esta enumeración.</h2>
 *
 * <p>Tipos soportados:</p>
 * <ul>
 *   <li>SQLITE</li>
 *   <li>MYSQL</li>
 *   <li>ACCESS</li>
 *   <li>ORACLE</li>
 * </ul>
 *
 * @author Alicia Martinez Asian
 * @version 1.0
 * @since 15/06/2024
 */
public enum SGBR {
    SQLITE, MYSQL, ACCESS, ORACLE;

    /**
     * <h2>Convierte una cadena de texto en un valor de la enumeración SGBR.</h2>
     *
     * <p>El método recorre todos los valores de la enumeración SGBR y compara sus nombres 
     * con la cadena de entrada (ignorando mayúsculas y minúsculas). Si encuentra una coincidencia, 
     * devuelve el valor correspondiente.</p>
     *
     * @param str la cadena de texto que representa un tipo de SGBDR.
     * @return el valor de la enumeración SGBR correspondiente a la cadena de texto.
     * @throws IllegalArgumentException si la cadena de texto no coincide con ningún valor de SGBR.
     */
	//Método que convierte una cadena de texto en un valor del enumerador
    public static SGBR fromString(String str) {
        for (SGBR sgbdr : SGBR.values()) {
            if (sgbdr.name().equalsIgnoreCase(str)) {
                return sgbdr;
            }
        }
        throw new IllegalArgumentException("Tipo de SGBDR no válido: " + str);
    }
}