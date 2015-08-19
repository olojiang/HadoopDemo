package com.olojiang.reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SelfJoinReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text text, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
				
		// Reducer write out result to disk
		boolean hasChild = false;
		boolean hasGrandParent = false;
		Set<String> childL = new HashSet<String>();
		Set<String> grandParentL = new HashSet<String>();
		for(Text v : values) {
			String value = v.toString();
			if(value.startsWith("1#")) {
				hasChild = true;
				
				String child=value.split("#")[1];
				childL.add(child);
			} else if(value.startsWith("2#")) {
				hasGrandParent = true;
				
				String grandParent=value.split("#")[1];
				grandParentL.add(grandParent);
			}
		}
		
		// Only have single relationship child->parent or parent->grand-parent, 
		// will not be recognized as expected.
		if (hasChild && hasGrandParent) {
			for(String c : childL) {
				for(String gp : grandParentL) {
					context.write(new Text(c), new Text(gp));
				}
			}
		}
	}
}
