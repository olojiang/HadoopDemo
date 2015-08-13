package com.olojiang.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text text, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
//		super.reduce(text, values, context);
		
		int sum = 0;
		for(IntWritable value : values) {
			sum += value.get();
		}
		
		System.out.println("Combiner, Get Key: " + text + ", Value=" + sum);
		
		// Combiner write out result to disk, and reduced the network data transfer
		context.write(text, new IntWritable(sum));
	}
}
