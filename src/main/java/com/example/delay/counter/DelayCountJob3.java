package com.example.delay.counter;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DelayCountJob3 extends Configured implements Tool {
	
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new DelayCountJob3(), args);
	}
	
	@Override
	public int run(String[] args) throws Exception {
		String workType = getConf().get("workType");
		System.out.println("workType = " + workType);
		
		Configuration conf = getConf();
		
		Job job = new Job(conf, "DelayCount");
		
		job.setJarByClass(DelayCountJob3.class);
		
		FileInputFormat.setInputPaths(job, "/home/java/dataexpo/1987_nohead.csv");
//		FileInputFormat.addInputPaths(job, "/home/java/dataexpo/1988_nohead.csv");
		
//		FileInputFormat.setInputPaths(job, "/home/java/dataexpo");
		job.setInputFormatClass(TextInputFormat.class);	// 생략가능
		
		job.setMapperClass(DelayCountMapper.class);
		job.setMapOutputKeyClass(Text.class);				// 생략가능
		job.setMapOutputValueClass(IntWritable.class);	// 생략가능
		
		job.setReducerClass(DelayCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setOutputFormatClass(TextOutputFormat.class);// 생략가능
		Path outputDir = new Path("/home/java/dataexpo_out/1987");
//		Path outputDir = new Path("/home/java/dataexpo_out/total");
		FileOutputFormat.setOutputPath(job, outputDir);
		
		FileSystem hdfs = FileSystem.get(conf);
		hdfs.delete(outputDir, true);
		
		return job.waitForCompletion(true) ? 0 : -1;

	}



}
