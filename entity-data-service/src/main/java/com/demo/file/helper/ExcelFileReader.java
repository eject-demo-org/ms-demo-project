package com.demo.file.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.demo.file.model.FileInputModel;
import com.demo.util.ApplicationConstant;
import com.demo.util.FileResourceUtility;

public class ExcelFileReader {

	private final static String[] FILE_TYPE = {".xlsx", ".xls"};

	public static StringBuilder readExcelSingleRow(FileInputModel inputModel, String idValue){	

		return readExcel(inputModel, idValue, true);
	}  
	
	public static StringBuilder readExcelMultipleRow(FileInputModel inputModel, String idValue) {
		return readExcel(inputModel, idValue, false);
	}
	
	private static StringBuilder readExcel(FileInputModel inputModel, String idValue, boolean isSingleRow) {
		StringBuilder retObj = null;
		try {
			File file =    new File(FileResourceUtility.getResourceProperties().getProperty("base.path")
					+"\\"+inputModel.getFilename());

			FileInputStream inputStream = new FileInputStream(file);

			Workbook workBook = null;

			String fileExtensionName = inputModel.getFilename().substring(inputModel.getFilename().indexOf("."));

			if(Arrays.stream(FILE_TYPE).anyMatch(e->e.contains(fileExtensionName))) {
				workBook = ".xls".equalsIgnoreCase(fileExtensionName)?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
			}

			Sheet sheet = workBook.getSheet(inputModel.getSheetName());

			Integer idIndex = getIdIndex(sheet.getRow(0), inputModel.getIdColumnName());

			if(idIndex.compareTo(-1)!=0) {

				int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

				for (int i = 1; i < rowCount+1; i++) {

					Row row = sheet.getRow(i);

					StringBuilder rowData = readDataRow(row, idIndex, idValue);
					if(rowData!=null) {
						if(isSingleRow) {
							return rowData;
						}
						
						if(retObj == null) {
							retObj = rowData;
						}else {
							retObj.append(ApplicationConstant.ROW_DELIMETER)
							.append(rowData);
						}
					}					
				} 
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return retObj;
	}

	private static int getIdIndex(Row row, String idColumnName) {

		for (int j = 0; j < row.getLastCellNum(); j++) {

			Cell cell = row.getCell(j);

			if(idColumnName.equalsIgnoreCase(cell.getStringCellValue())){
				return j;
			}						
		}
		return -1;
	}

	private static StringBuilder readDataRow(Row row, Integer idIndex, String idValue) {
		StringBuilder dataString = null;
		if(!idValue.equalsIgnoreCase(getCellDataInString(row.getCell(idIndex))))
			return null;
		
		System.out.println("ID found:"+idValue);
		dataString = new StringBuilder();
		for (int j = 0; j < row.getLastCellNum(); j++) {
			
			dataString
			.append(getCellDataInString(row.getCell(j)))
			.append(ApplicationConstant.DATA_DELIMETER);
		}

		return dataString;
	}

	private static String getCellDataInString(Cell cell) {
		String retValue = null;

		switch (cell.getCellType().name()) {
		case "NUMERIC":
			//System.out.println(cell.getNumericCellValue());
			Object o = cell.getNumericCellValue();
			//System.out.println(new BigDecimal(o.toString()).toPlainString());
			retValue = new BigDecimal(o.toString()).toPlainString();
			break;
		case "DATE":
			String pattern = "MM/dd/yyyy HH:mm:ss";
			DateFormat df = new SimpleDateFormat(pattern);
			retValue = df.format(cell.getDateCellValue());
			break;

		case "BOOLEAN":
			retValue = Boolean.toString(cell.getBooleanCellValue());
			break;

		default:
			retValue = cell.getStringCellValue();
			break;
		}
		System.out.println("retValue:"+retValue+":");
		return retValue;
	}


	public static void main(String...strings) throws IOException{

		FileInputModel fileModel = FileResourceUtility.getFileDetails(ApplicationConstant.FAMILY_DETAILS_FILE_TYPE);
		System.out.println(fileModel);

		System.out.println(readExcel(fileModel, "10001001", false));

	}

}
