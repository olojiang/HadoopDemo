package com.olojiang.driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
// mapreduce package is new in version 2.x with YARN
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.olojiang.mapper.GroupSortMapper;
import com.olojiang.reducer.GroupSortReducer;
import com.olojiang.writable.GroupSortWritable;

public class GroupSort {
	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println("Missing Parameter: GroupSort inputDir outputDir");
			System.exit(2);
		}
		
		Path inputPath = new Path(args[0]);
		Path outputPath = new Path(args[1]);
		
		// Configuration
		Configuration conf = new Configuration(true);
		
		// Create Job
		try {
			Job job = Job.getInstance(conf, "Filter, Group & Sort");
			
			// Setup Mapper, Reducer Class
			job.setMapperClass(GroupSortMapper.class);
			job.setReducerClass(GroupSortReducer.class);
			
			// Number of Reduce Task
			job.setNumReduceTasks(1);
			
			// Set Mapper output, Key, Value Class
			job.setMapOutputKeyClass(GroupSortWritable.class);
			job.setMapOutputValueClass(Text.class);
			
			// Specify Key, Value Class
			job.setOutputKeyClass(GroupSortWritable.class);
			job.setOutputValueClass(Text.class);
			
			// Input
			FileInputFormat.addInputPath( job, inputPath);
			job.setInputFormatClass(TextInputFormat.class);
			
			// Output
			FileOutputFormat.setOutputPath( job, outputPath);
			job.setOutputFormatClass(TextOutputFormat.class);
			
			// Delete output if exists
			FileSystem hdfs = FileSystem.get(conf);
			if(hdfs.exists(outputPath)) {
				hdfs.delete(outputPath, true);
			}
			
			// Execute job
			int code = job.waitForCompletion(true)? 0 : 1;
			System.out.printf("code=%s\n", code);
			System.exit(code);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
