package com.demo.file.helper;

public class ExcelFileReaderBuilder {
	
	public static class Builder{
		
		private String fileName;
		private String idColumnName;
		private String id;
		private String sheetNumber;
		
		public Builder fileName(String name) {
			this.fileName = name;
			return this;
		}
		
	}

	private ExcelFileReaderBuilder() {
		
	}
}
