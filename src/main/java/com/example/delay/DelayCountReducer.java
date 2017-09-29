package com.example.delay;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DelayCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	IntWritable outValue = new IntWritable();
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

		/*
		 * 출발지연
		 */
		int sum=0;
		for (IntWritable v : values) {
			sum += v.get();
		}
		outValue.set(sum);
		
		context.write(key, outValue);
	}
}
