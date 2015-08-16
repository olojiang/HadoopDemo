package com.olojiang.reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.olojiang.util.P;

public class CombineDataReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text text, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
				
		// Reducer write out result to disk
		StringBuilder sb = new StringBuilder();
		int n = 0;
		for(Text value : values) {
			if(n>0) {
				sb.append(", ");
			}
			sb.append(value.toString());
			n++;
		}
		P.p("Reducer: key", text);
		context.write(new Text(text), new Text(sb.toString()));
	}
}
