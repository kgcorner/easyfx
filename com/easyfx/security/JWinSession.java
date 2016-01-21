/***
*Provides session scope for each run. Session will be valid until the application is up.
***/

package com.easyfx.security;

import java.util.HashMap;
import java.util.Map;

import com.easyfx.exception.NoSessionAvailableException;

public class JWinSession {
	private static Map<String, Object> jwinSessionMap;
	public static void initializeJWinSession()
	{
		if(jwinSessionMap==null)
			jwinSessionMap= new HashMap<String, Object>();
	}
	public static void add(String key,Object value)
	{
		if(jwinSessionMap!=null)
			jwinSessionMap.put(key, value);
		else
		{
			initializeJWinSession();
			jwinSessionMap.put(key, value);
		}
	}
	public static Object get(String key) throws NoSessionAvailableException
	{
		
		if(jwinSessionMap!=null && jwinSessionMap.get(key)!=null)
			return jwinSessionMap.get(key);
		else
			throw new NoSessionAvailableException("No Session Found for "+key);
	}
	public static void invalidate()
	{
		if(jwinSessionMap!=null)
			jwinSessionMap.clear();
	}
}
