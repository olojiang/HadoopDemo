package com.olojiang.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SelfJoinMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String[] items = value.toString().split(",");
		String child = items[0];
		String parent = items[1];
		
		// Skip CSV header
		if(key.get()>0) {
			/*
			 * Setup the child -> parent
			 *   - Parent as link key
			 */
			context.write(new Text(parent), new Text("1#"+child));
			
			/*
			 * Setup the parent -> grand-parent
			 *   - Child as link key
			 */
			context.write(new Text(child), new Text("2#"+parent));
		}
	}
}
