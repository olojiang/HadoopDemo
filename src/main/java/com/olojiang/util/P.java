package com.olojiang.util;

/**
 * Print class
 * @author Hunter
 */
public class P {
	public static void p(String key, Object value) {
		System.out.println(key + "=" + value);
	}
	
	public static void e(String key, Object value) {
		System.err.println(key + "=" + value);
	}
	
	public static void p() {
		System.out.println();
	}

	public static void p(Object key) {
		System.out.println(key);
	}
	
	public static void p(String name, byte[] byteArray) {
		System.out.print(name + "=");
		for(byte b : byteArray) {
			System.out.print(b + " ");
		}
		System.out.println();
	}
	
	public static void p(String key, String[] items) {
		System.out.println(key);
		for(String item : items) {
			System.out.println("\t"+item);
		}
	}
}
