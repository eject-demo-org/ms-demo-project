package com.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.demo.file.helper.ExcelFileReader;
import com.demo.file.model.FileInputModel;

public class FileResourceUtility {

	public static FileInputModel getFileDetails(String type) {

		Properties prop = getResourceProperties();

		return new FileInputModel(prop.getProperty(type+".filename"), 
				prop.getProperty(type+".sheetname"), 
				prop.getProperty(type+".id"));

	}

	public static Properties getResourceProperties() {
		Properties prop = new Properties();
		try(InputStream input = ExcelFileReader.class.getClassLoader().getResourceAsStream("fileresource.properties")){			
			prop.load(input);						
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return prop;
	}
}
