package com.example.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class SortJob {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		Job job = new Job(new Configuration(), "Sort Test");
		
		/*
		 * 1. JarByClass
		 */
		job.setJarByClass(SortJob.class);
		
		/*
		 * 2. InputFormatClass
		 */
		FileInputFormat.setInputPaths(job, new Path("/home/java/mr/random.txt"));
		job.setInputFormatClass(KeyValueTextInputFormat.class);
//		job.setInputFormatClass(TextInputFormat.class);
		
		/*
		 * 3. MapperClass 
		 */
		job.setMapperClass(Mapper.class);	// 아이덴티티 메퍼
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		
//		job.setGroupingComparatorClass(TextGroupingComparator.class);
		job.setSortComparatorClass(TextGroupingComparator.class);
		/*
		 * 4. ReduceClass 
		 */
		job.setReducerClass(Reducer.class);// 아이덴티티 리듀서
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		/*
		 * 5. OutputFormatClass
		 */
		FileOutputFormat.setOutputPath(job, new Path("/home/java/mr/out"));
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileSystem hdfs = FileSystem.get(job.getConfiguration());
		hdfs.delete(new Path("/home/java/mr/out"), true);
		
		job.waitForCompletion(true);

	}

}
