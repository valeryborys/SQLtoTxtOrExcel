package export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import database.connection.ConnectionDB;

public class DatabaseToExcelExport {
	private static final String SQL_FIND_CARS_IN_DATABASE = "SELECT * FROM cars.auto";
	private static Connection con;

	public static void main(String[] args) {
		printDbToExcel();
	}

	private static void printDbToExcel() {
		String classPath = System.getProperty("user.dir");
		String excelFilePath = classPath + File.separator + "src" + File.separator + "exported" + File.separator
				+ "base.xls";
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet("Cars");
		try {
			createHeader(sheet);
			writeDataLines(sheet);
			FileOutputStream output = new FileOutputStream(excelFilePath);
			workBook.write(output);
			workBook.close();

		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	private static void createHeader(HSSFSheet sheet) {
		String[] headerValues = { "Owner Login", "Car make", "Car model", "Production year", "Engine volume",
				"Engine Fuel Type" };
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headerValues.length; i++) {
			Cell headerCell = headerRow.createCell(i);
			headerCell.setCellValue(headerValues[i]);
		}

	}

	private static void writeDataLines(HSSFSheet sheet) throws SQLException, ClassNotFoundException, IOException {
		con = ConnectionDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_FIND_CARS_IN_DATABASE);
		int rowCount = 1;
		while (resultSet.next()) {
			Row row = sheet.createRow(rowCount++);
			for (int i = 0; i < 6; i++) {
				String result = resultSet.getString(i + 1);
				Cell cell = row.createCell(i);
				cell.setCellValue(result);
				System.out.println(result);
			}

		}
	}
}
