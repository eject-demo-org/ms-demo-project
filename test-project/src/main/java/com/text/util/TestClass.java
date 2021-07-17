package com.text.util;

public class TestClass {

	public static void main(String[] args) {
		
		String dataString = "10001001|~|Anna|~|Dsouza|~|31137.0|~|1.0|~|1.0|~|2.0|~|2.0|~|";
		for(String data:dataString.split("~")) {
			System.out.println(data);
		}
	}

}
