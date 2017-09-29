package com.example.dep;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class DepatureDelayCountJob {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		
		Job job = new Job(conf, "DepatureDelayCount");
		
		//jar파일 정의
		job.setJarByClass(DepatureDelayCountJob.class);
		
		FileInputFormat.setInputPaths(job, "/home/java/dataexpo");


		job.setInputFormatClass(TextInputFormat.class);	//생략 가능
		
		//맵퍼 정의
		job.setMapperClass(DepartureDelayCountMapper.class);
		job.setMapOutputKeyClass(Text.class);				//생략 가능(reducer의 출력타입과 같다면)
		job.setMapOutputValueClass(IntWritable.class);	//생략 가능
		
		//리듀서 정의
		job.setReducerClass(DepatureDelayCountReducer.class);
		job.setOutputKeyClass(Text.class);					//reducer의 출력은 정의해야 한다
		job.setOutputValueClass(IntWritable.class);		//그래야 outputFormat에서 string처리해서 출력됨
		
		job.setOutputFormatClass(TextOutputFormat.class);//생략 가능. 디폴트가 TextOutputFormat이므로.
		Path outputDir = new Path("/home/java/dataexpo_out/total");
		FileOutputFormat.setOutputPath(job, outputDir);
		
		//폴더 지우기(이미 있을 경우)
		FileSystem hdfs = FileSystem.get(conf);
		hdfs.delete(outputDir, true);	//폴더니까 true 줘야 함
		
		job.waitForCompletion(true);
	}

}
