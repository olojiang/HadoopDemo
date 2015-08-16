package com.olojiang.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtil<T> {
	/**
	 * Get an array for a (key=>Object) map
	 * @param map
	 * @param key
	 * @return
	 */
	public T get(Map<String, T> map, Class<T> classT, String key) {
		T arr = null;
		if (map.containsKey(key)) {
			arr = map.get(key);
		} else {
			try {
				arr = classT.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			map.put(key, arr);
		}
		
		return arr;
	}
	
	/**
	 * Get an array for a (key=>arrayList) map
	 * @param map
	 * @param key
	 * @return
	 */
	public List<T> getArray(Map<String, List<T>> map, String key) {
		List<T> arr = null;
		if (map.containsKey(key)) {
			arr = map.get(key);
		} else {
			arr = new ArrayList<T>();
			map.put(key, arr);
		}
		
		return arr;
	}
	
	/**
	 * Append (key=>arrayList) map with new value for the array list
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 */
	public List<T> appendArray(Map<String, List<T>> map, String key, T value) {
		return appendArray(map, key, value, false/*removeDuplicate*/);
	}
	
	/**
	 * Append (key=>arrayList) map with new value for the array list
	 * @param map
	 * @param key
	 * @param value
	 * @param removeDuplicate
	 * @return
	 */
	public List<T> appendArray(Map<String, List<T>> map, String key, T value, boolean removeDuplicate) {
		List<T> arr = getArray(map, key);
		if(!removeDuplicate || arr.indexOf(value) == -1) {
			arr.add(value);
		}
		
		return arr;
	}
	
	/**
	 * Append (key=>arrayList) map with new value for the array list
	 * @param map
	 * @param newMap
	 */
	public void appendMap(Map<String, List<T>> map, Map<String, T> newMap) {
		for(String key : newMap.keySet()) {
			T value = newMap.get(key);
			
			List<T> arr = getArray(map, key);
			arr.add(value);
		}
	}
	
	/**
	 * Reverse sort the hash map by value
	 * @param map
	 * @return
	 */
	public static <K extends Comparable, V extends Comparable> Map<K, V> reverseSortByValues(Map<K, V> map) {
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		
		Collections.sort(entries, new Comparator<Map.Entry<K, V>>(){
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		Map<K, V> result = new LinkedHashMap<K, V>();
		
		for(Entry<K, V> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		} 
		
		return result;
	}
}
