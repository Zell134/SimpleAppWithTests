package simpleapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private final String excelFilePath;
	private XSSFSheet sheet;
	private XSSFWorkbook book;

	public ExcelReader(String excelFilePath, String sheetName) throws IOException {
		this.excelFilePath = excelFilePath;
		File file = new File(excelFilePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			book = new XSSFWorkbook(fileInputStream);
			sheet = book.getSheet(sheetName);
		} catch (IOException e) {
			throw new IOException("Не поддерживаемый формат");
		}
	}

	private String cellToString(XSSFCell cell) throws Exception {
		Object result = null;
		CellType type = cell.getCellType();
		switch (type) {
		case NUMERIC:
			result = cell.getNumericCellValue();
			break;
		case STRING:
			result = cell.getStringCellValue();
			break;
		case FORMULA:
			result = cell.getCellFormula();
			break;
		case BLANK:
			result = "";
			break;
		default:
			throw new Exception("Ошибка чтения ячейки");
		}
		return result.toString();
	}

	public List<User> getSheetData() throws Exception {
		File file = new File(excelFilePath);
		FileInputStream fileInputStream = new FileInputStream(file);
		book = new XSSFWorkbook(fileInputStream);
		sheet = book.getSheet("Лист1");
		int numberOfColumn = 5;
		int numberOfRows = 6;
		List<User> data = new ArrayList<>();
		

		for (int i = 1; i <= numberOfRows; i++) {
			User user = new User();
			for (int j = 0; j < numberOfColumn; j++) {

				XSSFRow row = sheet.getRow(i);
				XSSFCell cell = row.getCell(j);
				String value = cellToString(cell);

				switch (j) {
				case (0):
					user.setId((int) Double.parseDouble(value));
					break;
				case (1):
					user.setEmail(value);
					break;
				case (2):
					user.setFirst_name(value);
					break;
				case (3):
					user.setLast_name(value);
					break;
				case (4):
					user.setAvatar(value);
					break;
				}

				if (value == null) {
					System.out.println("Пустые ячейки");
				}
			}
			data.add(user);
		}
		return data;
	}

}
