package com.olojiang.mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper extends Mapper<Object, Text, IntWritable, Text> {
	
	@Override
	protected void map(Object key, Text value,
			Mapper<Object, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] items = line.split(",");
		int num = Integer.parseInt(items[1]);
		String name = items[0];
		
		context.write(new IntWritable(num), new Text(name));
	}
}
