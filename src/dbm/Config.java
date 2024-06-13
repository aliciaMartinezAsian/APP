package dbm;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Config {

	public static String url = null;
	
	private final String FILE_CONFIG = "db/configuracion.properties";

	public Config() throws Exception {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(new FileInputStream(FILE_CONFIG)));

		String TYPE = prop.getProperty("TYPE");
		String NAME = prop.getProperty("NAME");

		switch (SGBR.fromString(TYPE)) {
			case SQLITE    -> url = "jdbc:sqlite:" + NAME;
			case ACCESS    -> url = "jdbc:ucanaccess://" + NAME + ";memory=true";
		}
	}
	
}