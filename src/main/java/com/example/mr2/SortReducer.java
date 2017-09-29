package com.example.mr2;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SortReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	Text xxx = new Text();
	@Override
	protected void reduce(Text key, Iterable<LongWritable> values, Context context)	throws IOException, InterruptedException {
		
		long sum = 0;
		
		LongWritable value = new LongWritable();
		xxx.set(key.toString());
		
		System.out.print(xxx + " : ");
		for (LongWritable v : values) {
			sum += v.get();
			System.out.print(v.get() + ", ");
		}
		value.set(sum);
		
		context.write(key, value);
		System.out.println();
	}

}
