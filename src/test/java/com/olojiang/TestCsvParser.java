package com.olojiang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVParser;

import com.olojiang.util.P;

public class TestCsvParser {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
		// 日期	时间	纬度(°)	经度(°)	深度(km)	震级类型	震级值	事件类型	参考地名
		String line = "2015-07-18,13:19:42.9,29.59,104.85,2,ML,1.8,eq,四川资中";
		
		String[] items = null;
		try {
			items = new CSVParser().parseLine(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertNotNull(items);
		assertEquals(9, items.length);
		
		P.p("Items:", items);
	}
}
