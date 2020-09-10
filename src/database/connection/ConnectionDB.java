package database.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionDB {

	public static Connection getConnection()
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		ResourceBundle bundle = ResourceBundle.getBundle("database.connection.db");
		String dbUrl = bundle.getString("db.url");
		String dbLogin = bundle.getString("db.login");
		String dbPass = bundle.getString("db.password");
		String dbDriver = bundle.getString("db.driverClassName");
		Class.forName(dbDriver);
		return DriverManager.getConnection(dbUrl, dbLogin, dbPass);

	}

}
