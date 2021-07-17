package com.demo.file.model;

public class FileInputModel {
	
	private final String filename;
	private final String sheetName;
	private final String idColumnName;
	
	public FileInputModel(String filename, String sheetName, String idColumnName) {
		this.filename = filename;
		this.sheetName = sheetName;
		this.idColumnName = idColumnName;
	}	

	public String getFilename() {
		return filename;
	}


	public String getSheetName() {
		return sheetName;
	}


	public String getIdColumnName() {
		return idColumnName;
	}


	@Override
	public String toString() {
		return "FileInputModel [filename=" + filename + ", sheetName=" + sheetName + ", idColumnName=" + idColumnName
				+ "]";
	}	
	
}
