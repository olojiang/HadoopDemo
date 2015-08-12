package com.olojiang;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.olojiang.mapper.WordCountMapper;
import com.olojiang.reducer.WordCountReducer;

public class TestWordCountMapReduce {
	private Mapper mapper;
	private Reducer reducer;
	private MapReduceDriver driver;
	
	@Before
	public void setUp() throws Exception {
		mapper = new WordCountMapper();
		reducer = new WordCountReducer();
		driver = new MapReduceDriver(mapper, reducer);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
		
		String line = "Ji Wei is testing MRUnit here, MRUnit is working.";
		try {
			// Sort by Upper and Lower case of key
			driver.withInput(new IntWritable(1), new Text(line))
				.withOutput(new Text("Ji"), new IntWritable(1))
				.withOutput(new Text("MRUnit"), new IntWritable(2))
				.withOutput(new Text("Wei"), new IntWritable(1))
				.withOutput(new Text("here,"), new IntWritable(1))
				.withOutput(new Text("is"), new IntWritable(2))
				.withOutput(new Text("testing"), new IntWritable(1))
				.withOutput(new Text("working."), new IntWritable(1))
				.runTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
