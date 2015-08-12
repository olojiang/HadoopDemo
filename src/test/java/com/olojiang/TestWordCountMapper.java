package com.olojiang;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.olojiang.mapper.WordCountMapper;

public class TestWordCountMapper {

	private Mapper mapper;
	private MapDriver driver;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mapper = new WordCountMapper();
		driver = new MapDriver(mapper);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
		String line = "Hello Ji Wei, let's go on this process.";
		
		try {
			driver.withInput(new IntWritable(1), new Text(line))
				.withOutput(new Text("Hello"), new IntWritable(1))
				.withOutput(new Text("Ji"), new IntWritable(1))
				.withOutput(new Text("Wei,"), new IntWritable(1))
				.withOutput(new Text("let's"), new IntWritable(1))
				.withOutput(new Text("go"), new IntWritable(1))
				.withOutput(new Text("on"), new IntWritable(1))
				.withOutput(new Text("this"), new IntWritable(1))
				.withOutput(new Text("process."), new IntWritable(1))
				.runTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
