package com.olojiang.reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.olojiang.util.MapUtil;
import com.olojiang.util.P;

public class TopNReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private Map<Text, IntWritable> countMap = new HashMap<Text, IntWritable>();
	
	@Override
	protected void reduce(Text text, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for(IntWritable value : values) {
			sum += value.get();
		}
		
		System.out.println("Reducer, Get Key: " + text + "=>" + sum);
		
		// Reducer write out result to disk
		countMap.put(new Text(text), new IntWritable(sum)); // new Text(text) is important, otherwise, it will the the last text
	}

	@Override
	protected void cleanup(
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {

		/*
		 * Although there are 3 reducers, but the clearup() only issue once on a single machine
		 * - I didn't try cluster env for this.
		 */
		
		P.p("countMap", countMap);
		Map<Text, IntWritable> sortedMap = MapUtil.reverseSortByValues(countMap);
		
		P.p("sortedMap", sortedMap);
		int counter = 0;
		for(Text key : sortedMap.keySet()) {
			if (counter == 5) {
				break;
			}
			
			context.write(key, sortedMap.get(key));
			
			counter++;
		}
	}
}
