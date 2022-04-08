package simpleapp.util;

import java.util.List;

public class ExcelDataProvider {
	
	    public List<User> usersFromSheet() throws Exception {
	        String path = "src/test/resources/users.xlsx";
	        String sheet ="Лист1";
	        ExcelReader excelReader = new ExcelReader(path, sheet);
	        return excelReader.getSheetData();
	    }	   
}
