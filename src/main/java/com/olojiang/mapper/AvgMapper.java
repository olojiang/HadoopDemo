package com.olojiang.mapper;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgMapper extends Mapper<Object, Text, Text, FloatWritable> {
	
	@Override
	protected void map(Object key, Text value,
			Mapper<Object, Text, Text, FloatWritable>.Context context)
			throws IOException, InterruptedException {
		String[] items = value.toString().split(",");
		float score = Float.parseFloat(items[1]);
		context.write(new Text(items[0]), new FloatWritable(score));
	}
}
