package com.olojiang.partitioner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class EarthQuakePartitioner extends Partitioner<Text, IntWritable> {

	@Override
	public int getPartition(Text key, IntWritable value, int numReduceTasks) {
		// Random partitioner, you can implement as others, the number of partition result should be the same as numReduceTask;
		return (int)(Math.random()*numReduceTasks);
	}
}
