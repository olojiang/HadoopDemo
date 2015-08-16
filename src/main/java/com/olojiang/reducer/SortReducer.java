package com.olojiang.reducer;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SortReducer extends Reducer<IntWritable, Text, IntWritable, Text> {

	@Override
	protected void reduce(IntWritable num, Iterable<Text> values,
			Reducer<IntWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
				
		// Reducer write out result to disk
		// Dedup, means no matter how many times the value appear, only count the key ONCE
		Iterator<Text> it = values.iterator();
		StringBuilder sb = new StringBuilder();
		int n = 0;
		
		while(it.hasNext()) {
			if( n>0 ) {
				sb.append(", ");
			}
			sb.append(it.next().toString());
			n++;
		}
		context.write(num, new Text(sb.toString()));
	}
}
