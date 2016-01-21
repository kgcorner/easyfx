/***
* Class for creating Message repository
***/

package com.easyfx.util;

import java.util.HashMap;
import java.util.Map;

public class MessageRepo {
	private static Map<String, String> errorMessage= new HashMap<>();
	public static void setErrorMessage(String key,String message)
	{
		errorMessage.put(key,message);
	}
	public static String getErrorMessage(String key)
	{
		return errorMessage.get(key);
		
	}
}
