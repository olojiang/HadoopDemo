package com.olojiang.reducer;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {

	@Override
	protected void reduce(Text text, Iterable<FloatWritable> values,
			Reducer<Text, FloatWritable, Text, FloatWritable>.Context context)
			throws IOException, InterruptedException {
				
		// Reducer write out result to disk
		// Average means total/count
		float sum = 0;
		int count = 0;
		Iterator<FloatWritable> it = values.iterator();
		while(it.hasNext()) {
			sum+=it.next().get();
			count++;
		}
		
		context.write(text, new FloatWritable(sum/count));
	}
}
