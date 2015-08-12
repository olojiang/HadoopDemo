package com.olojiang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.olojiang.reducer.WordCountReducer;

public class TestWordCountReducer {
	private Reducer reducer;
	private ReduceDriver driver;

	@Before
	public void setUp() throws Exception {
		reducer = new WordCountReducer();
		driver = new ReduceDriver(reducer);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
		String key = "HunterGame";
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(5));
		values.add(new IntWritable(4));
		
		try {
			driver.withInput(new Text(key), values)
				.withOutput(new Text(key), new IntWritable(9))
				.runTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
