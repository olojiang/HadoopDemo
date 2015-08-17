package com.olojiang.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 *  @author Hunter
 **/
public class StringUtil {
	
	/**
	 * Is empty or null string
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str)
	{
		return ((str == null) || (str.trim().length() == 0));
	}
	
	/**
	 * Is not empty or null string
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str)
	{
		return ((str != null) && (str.trim().length() > 0));
	}
	
	/**
	 * Upper first characters
	 * @param str
	 * @return
	 */
	public static String upperFirstChar(String str)
	{
		if(StringUtil.isEmpty(str))
		{
			return str;
		}
		if(str.length() == 1)
		{
			return str.toUpperCase();
		}
		String firstChar = str.substring(0,1);
		return firstChar.toUpperCase() + str.substring(1);
	}

	
	
	/**
	 * Get response time key string
	 * @param driverid
	 * @param dbType
	 * @param rtimetype
	 * @param timeunit
	 * @param time
	 * @return
	 */
	public static String getResponseTimeKey(String driverid, String dbType, String rtimetype, String timeunit, 
			String time) {
		String key = driverid + "#" + dbType + "#" + rtimetype + "#" + timeunit + "#" + time;
		return key;
	}
	
	/**
	 * Get count key string
	 * @param driverid
	 * @param counttype
	 * @param timeunit
	 * @param time
	 * @return
	 */
	public static String getCountKey(String driverid, int counttype, String timeunit, 
			String time) {
		String key = driverid + "#" + counttype + "#" + timeunit + "#" + time;
		return key;
	}

	/**
	 * Convert set of string to string
	 * @param set
	 * @param separator
	 * @return
	 */
	public static String setToListString(Set<String> set, String separator) {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		for(String val: set) {
			if(i!=0) {
				sb.append(separator);
			}
			
			sb.append(val);
			
			i++;
		}
		return sb.toString();
	}
	
	/**
	 * Join method for java
	 * @param separator
	 * @param items
	 * @return
	 */
	public static String join(String separator, String... items) {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		for(String val: items) {
			if(i!=0) {
				sb.append(separator);
			}
			
			sb.append(val);
			
			i++;
		}
		return sb.toString();
	}

	/**
	 * Join method for java
	 * @param separator
	 * @param items
	 * @return
	 */
	public static String join(String separator, Collection<String> list) {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		for(String val: list) {
			if(i!=0) {
				sb.append(separator);
			}
			
			sb.append(val);
			
			i++;
		}
		return sb.toString();
	}
	
	/**
	 * Do MD5 functions
	 * @param input
	 * @return
	 */
	public static synchronized String md5(String input) {
		StringBuilder after = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte[] bytes = md.digest(input.getBytes("utf-8"));
			
			for(byte b : bytes) {
				String temp = Integer.toHexString(b & 0xff);
				
				if(temp.length() == 1) {
					after.append("0");
				}
				
				after.append(temp);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return after.toString();
	}
	
	/**
	 * Split string and trim the separated strings
	 * @param s
	 * @param reg
	 * @return
	 */
	public static String[] split(String s, String reg){
		if(s==null || reg == null) {
			return null;
		}
		String[] sa = s.split(reg);
		ArrayList<String> stringList = new ArrayList<String>();
		for(String str: sa){
			str = str.trim();
			if(str.length()>0){
				stringList.add(str);
			}
		}
		return stringList.toArray(new String[0]);
	}
	
	/**
	 * Generate Unique Id
	 * @return
	 */
	public static String getUniqueId() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Not Empty Append new String
	 * @param newString
	 * @param existingString
	 * @return
	 */
	public static String notEmptyAppend(String existingString, String separator, String newString) {
		if(StringUtil.isNotEmpty(newString)) {
			existingString += separator + newString;
		}
		return existingString;
	}
}
