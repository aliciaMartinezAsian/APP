package dbm;

public enum SGBR {
	SQLITE, MYSQL, ACCESS, ORACLE;
	
	public static SGBR fromString(String str) {
		for (SGBR sgbdr : SGBR.values()) {
			if (sgbdr.name().equalsIgnoreCase(str)) {
				return sgbdr;
			}
		}
		throw new IllegalArgumentException("Tipo de SGBDR no válido: " + str);
	}

}
