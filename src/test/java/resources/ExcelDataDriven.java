package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataDriven {

	public ArrayList<String> getData(String sheetName, String testCase) throws IOException {

		ArrayList<String> a = new ArrayList<String>();

		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\excelDataDriven.xlsx";

		FileInputStream fileInputStream = new FileInputStream(filePath);

		XSSFWorkbook xssfWorkBook = new XSSFWorkbook(fileInputStream);

		int totalSheets = xssfWorkBook.getNumberOfSheets();

		for (int i = 0; i < totalSheets; i++) {
			if (xssfWorkBook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				// Extract specific sheet
				XSSFSheet sheet = xssfWorkBook.getSheetAt(i);

				// Extract columns first
				Iterator<Row> rowIterator = sheet.rowIterator();
				Row firstRow = rowIterator.next();

				// Extract cell value
				Iterator<Cell> cellIterator = firstRow.cellIterator();

				int k = 0;
				int column = -1;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getStringCellValue().equalsIgnoreCase("test cases")) {
						column = k;
						break;
					}
					k++;
				}
				if (column == -1) {
					System.out.println("Column is not present in excel.");
				} else {
					System.out.println("Column number is: " + column);
				}

				// Once identified column now scan complete column to find 'purchase' test case.

				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();

					if (row.getCell(column).getStringCellValue().equalsIgnoreCase(testCase)) {

						cellIterator = row.cellIterator();

						while (cellIterator.hasNext()) {

//							DataFormatter formatter = new DataFormatter();
							Cell cell = cellIterator.next();

							if (cell.getCellType() == CellType.STRING) {
								a.add(cell.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
							}
//							a.add(formatter.formatCellValue(cell));

						}
						return a;
					}

				}

			}
		}
		return a;
	}

	public static void main(String args[]) throws IOException {

		ExcelDataDriven excel = new ExcelDataDriven();

		System.out.println(excel.getData("testdata", "logIN"));

		ArrayList data = excel.getData("testdata", "purchase");
		System.out.println("Value 1 " + data.get(0));
		System.out.println("Value 2 " + data.get(1));
		System.out.println("Value 3 " + data.get(2));
		System.out.println("Value 4 " + data.get(3));

	}
}