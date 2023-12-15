package utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	
	public static List<HashMap<String, String>> data(String filepath, String sheetName) {
		
		List<HashMap<String, String>> mydata = new ArrayList<>();
		
		
		try {
			FileInputStream fs = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Row HeaderRow = sheet.getRow(0);
			
			for (int r = 1; r < sheet.getPhysicalNumberOfRows(); r++) //outer for loop for row(r)
			{
				Row currentRow = sheet.getRow(r);
				HashMap<String, String> currentHash = new HashMap<String, String>();
				for (int c = 0; c < currentRow.getPhysicalNumberOfCells(); c++) //(inner for loop for col(c))
				{
						Cell currentCell = currentRow.getCell(c);
						
						switch (currentCell.getCellType()) 
						{
							case STRING:
								currentHash.put(HeaderRow.getCell(c).getStringCellValue(), currentCell.getStringCellValue());
							break;
						}
				}
				mydata.add(currentHash);
			}
			fs.close();
			} catch (Exception e) {
			e.printStackTrace();
		}
		return mydata;
	}
}

