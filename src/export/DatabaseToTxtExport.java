package export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.connection.ConnectionDB;

public class DatabaseToTxtExport {
	private static final String SQL_FIND_CARS_IN_DATABASE = "SELECT * FROM cars.auto";
	private static Connection con;
	private static PrintWriter writer;

	public static void main(String[] args) {
		String classPath = System.getProperty("user.dir");
		String txtFilePath = classPath + File.separator + "src" + File.separator + "exported" + File.separator
				+ "base.txt";
		try {
			con = ConnectionDB.getConnection();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_FIND_CARS_IN_DATABASE);
			writer = new PrintWriter(new FileWriter(String.valueOf(txtFilePath)));
			while (resultSet.next()) {
				StringBuilder sb = new StringBuilder();
				String delimiter = "";
				for (int i = 1; i <= 6; i++) {
					sb.append(delimiter);
					sb.append(resultSet.getString(i));
					delimiter = ", ";
				}
				writer.println(sb.toString());
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (writer != null) {
				writer.close();
			}
		}
	}
}
