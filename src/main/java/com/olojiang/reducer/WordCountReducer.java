package com.olojiang.reducer;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	static private String testParameter = null;

	@Override
	protected void reduce(Text text, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
//		super.reduce(text, values, context);
		
		if(testParameter  == null) {
			Configuration config = context.getConfiguration();
			testParameter = config.get("testParameter");
			System.out.println("Reducer, testParameter=" + testParameter);
		}
		
		System.out.println("Reducer, Get Key: " + text);
		
		int sum = 0;
		for(IntWritable value : values) {
			sum += value.get();
		}
		
		// Reducer write out result to disk
		context.write(text, new IntWritable(sum));
	}
}
