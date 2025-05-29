package com.javacore.utils;

public class StringUtils {
	public static Boolean isNullOrEmpty(String value) {
		if(value != null && value != "") {
			return false;
		}
		return true;
	}
}
