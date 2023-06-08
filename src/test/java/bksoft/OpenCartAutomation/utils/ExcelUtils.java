package bksoft.OpenCartAutomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public FileInputStream fis = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.xlsx";

	public Object[][] getTestData(String sheetName) {
		File path = new File(excelPath);

		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			System.out.println("Excel file not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File input/output error occured!");
			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rowCount][colCount];

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}
		return data;
	}
}