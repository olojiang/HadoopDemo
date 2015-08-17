package com.olojiang.reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.olojiang.writable.GroupSortWritable;

public class GroupSortReducer extends Reducer<GroupSortWritable, Text, GroupSortWritable, Text> {

	@Override
	protected void reduce(GroupSortWritable text, Iterable<Text> values,
			Reducer<GroupSortWritable, Text, GroupSortWritable, Text>.Context context)
			throws IOException, InterruptedException {
				
		// Reducer write out result to disk
		// Dedup, means no matter how many times the value appear, only count the key ONCE
		for(Text t : values) {
			context.write(text, t);
		}
	}
}
