package com.example.arr;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.example.parser.AirLinePerformanceParser;

public class ArriveDelayCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	final IntWritable one = new IntWritable(1);
	Text outputKey = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	
		AirLinePerformanceParser parser = new AirLinePerformanceParser(value);
		
		if (parser.isArriveDelayAvailable()){
			if (parser.getArriveDelayTime() > 0){
				outputKey.set(parser.getYear()+"");
				context.write(outputKey, one);
			}
		}
	
	}

}
