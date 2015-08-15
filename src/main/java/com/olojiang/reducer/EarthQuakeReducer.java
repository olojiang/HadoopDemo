package com.olojiang.reducer;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.olojiang.util.P;

public class EarthQuakeReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private static int fieldIndex = -1;

	@Override
	protected void reduce(Text text, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
//		super.reduce(text, values, context);
		
		if(fieldIndex == -1) {
			Configuration config = context.getConfiguration();
			fieldIndex = Integer.parseInt(config.get("fieldIndex"));
			System.out.println("Mapper, fieldIndex=" + fieldIndex);
		}
		
		int sum = 0;
		for(IntWritable value : values) {
			sum += value.get();
		}
		
		P.p(text.toString(), sum);
		
		// Reducer write out result to disk
		context.write(text, new IntWritable(sum));
	}
}
