package com.olojiang.reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DedupReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text text, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
				
		// Reducer write out result to disk
		// Dedup, means no matter how many times the value appear, only count the key ONCE
		context.write(text, new Text(""));
	}
}
