package com.olojiang.driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
// mapreduce package is new in version 2.x with YARN
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.olojiang.mapper.EarthQuakeMapper;
import com.olojiang.partitioner.EarthQuakePartitioner;
import com.olojiang.reducer.EarthQuakeReducer;

public class EarthQuake {
	public static void main(String[] args) {
		/*
		 * Data Source:
		 * - http://data.earthquake.cn/data/index.jsp?no11&number=28
		 * Field Index
		 * - 日期,时间,纬度(°),经度(°),深度(km),震级类型,震级值,事件类型,参考地名
		 * - 事件类型代码含义：eq:天然地震 un:非天然地震 ep:爆破 sp:疑爆 ss:塌陷 se:可疑事件 ve:火山构造地震 le:长周期事件 ve:火山混合事件 vp:火山爆炸 vt:火山颤动 ot:其它								
		 */
		if(args.length != 3) {
			System.err.println("Missing Parameter: WordCount inputDir outputDir fieldIndexInCsvFile");
			System.exit(2);
		}
		
		Path inputPath = new Path(args[0]);
		Path outputPath = new Path(args[1]);
		
		// Configuration
		Configuration conf = new Configuration(true);
		conf.set("fieldIndex", args[2]);
		
		// Create Job
		try {
			Job job = Job.getInstance(conf, "Earth Quake");
			
			// Setup Mapper, Reducer Class
			job.setMapperClass(EarthQuakeMapper.class);
			job.setReducerClass(EarthQuakeReducer.class);
			
			// Setup Combiner
			job.setCombinerClass(EarthQuakeReducer.class);
			
			// Number of Reduce Task
			job.setNumReduceTasks(3);
			job.setPartitionerClass(EarthQuakePartitioner.class);
			
			// Set Mapper output, Key, Value Class
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);
			
			// Specify Key, Value Class
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
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
			System.out.printf("Exit Code=%s\n", code);
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
