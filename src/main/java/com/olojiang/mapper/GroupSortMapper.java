package com.olojiang.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.olojiang.util.StringUtil;
import com.olojiang.writable.GroupSortWritable;

public class GroupSortMapper extends Mapper<LongWritable, Text, GroupSortWritable, Text> {
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, GroupSortWritable, Text>.Context context)
			throws IOException, InterruptedException {
		
		// Skip first line of CSV header
		if(key.get()>0) {
//			id,name,institute,grade
			String[] items = value.toString().split(",");
			
			float grade = Float.parseFloat(items[3]);
			
			if(grade > 60f) {
				context.write(
						new GroupSortWritable(Integer.parseInt(items[2]), grade), 
						new Text(StringUtil.join(", ", items[0], items[1])));
			}
		}
	}
}
