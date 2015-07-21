package com.olojiang.mapper;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {
	private final IntWritable ONE = new IntWritable(1);
	private Text word = new Text();
	
	@Override
	protected void map(Object key, Text value,
			Mapper<Object, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
//		super.map(key, value, context);
		
		// Default Input is just one line string
		String line = value.toString();
		System.out.println("Mapper, Get Line: " + line);
		
		StringTokenizer tokenizer = new StringTokenizer(line);
		while(tokenizer.hasMoreElements()) {
			word.set(tokenizer.nextToken());
			
			// Write Mapper Result (Key, Value) out to Disk
			context.write(word, ONE);
		}
	}
	
}
