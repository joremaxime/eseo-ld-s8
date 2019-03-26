package come.SERVER_API_REST.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOConnection {
	
	public static final String CHEMIN_PROPERTIES = "SERVER_API_REST/src/main/resources/application.properties";
	
	public Connection getConnection() throws Exception {
		String[] properties = this.loadProperties();
		
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection connect = DriverManager.getConnection("");
		
		return connect;
	}
	
	private String[] loadProperties() throws Exception {
		Properties properties = new Properties();
		String url;
		String user;
		String password;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(CHEMIN_PROPERTIES);

		if (fichierProperties == null) {
			throw new Exception("Fichier de properties introuvable");
		}

		try {
			properties.load(fichierProperties);
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (FileNotFoundException e) {
			throw new Exception("Fichier non trouvé" + e);
		} catch (IOException e) {
			throw new Exception("Impossible de charger le fichier properties " + CHEMIN_PROPERTIES, e);
		}
		
		return new String[] { url, user, password };
	}
	
	public void bonjour() {
		System.out.println("bonjour");
	}
	
}
