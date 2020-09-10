package database.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {

	public static Connection getConnection()
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		String classPath = System.getProperty("user.dir");
		String fullPropertiesPath = classPath + File.separator + "src" + File.separator + "database" + File.separator
				+ "connection" + File.separator + "db.properties";
		Properties prop = new Properties();
		prop.load(new FileInputStream(fullPropertiesPath));
		String dbUrl = prop.getProperty("db.url");
		String dbLogin = prop.getProperty("db.login");
		String dbPass = prop.getProperty("db.password");
		String dbDriver = prop.getProperty("db.driverClassName");
		Class.forName(dbDriver);
		return DriverManager.getConnection(dbUrl, dbLogin, dbPass);

	}

}
