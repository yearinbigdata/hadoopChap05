package com.example.mr2;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper extends Mapper<Text, Text, Text, LongWritable> {

	static final LongWritable one = new LongWritable(1);
	
	@Override
	protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
		
		context.write(key, one);
		
	}
}
