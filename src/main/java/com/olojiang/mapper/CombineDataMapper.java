package com.olojiang.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class CombineDataMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		// Get filename
		FileSplit fs = (FileSplit)context.getInputSplit();
		String filename = fs.getPath().getName();
		
		// Skip header
		if(key.get()>0) {
			String[] items = value.toString().split(",");
			
			if(filename.equals("users")) {
				// id,name,reputation
				context.write(new Text(items[0]), new Text(items[1]));
			} else {
				// id,type,subject,body,userid
				context.write(new Text(items[4]), new Text(items[3]));
			}
		}
	}
}
