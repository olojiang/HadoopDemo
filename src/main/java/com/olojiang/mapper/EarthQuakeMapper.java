package com.olojiang.mapper;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.olojiang.util.P;

import au.com.bytecode.opencsv.CSVParser;

public class EarthQuakeMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final IntWritable ONE = new IntWritable(1);
	private static int fieldIndex = -1;
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
//		super.map(key, value, context);
		
		if(fieldIndex == -1) {
			Configuration config = context.getConfiguration();
			fieldIndex = Integer.parseInt(config.get("fieldIndex"));
			System.out.println("Mapper, fieldIndex=" + fieldIndex);
		}
		
		// Default Input is just one line string
		String line = value.toString();
		
		if( key.get()>0 ) { // Make sure first line is not parsed
			String[] items = new CSVParser().parseLine(line);
			String field = items[fieldIndex];
			P.p("Field", field);
			context.write(new Text(field), ONE);
		}
	}
	
}
