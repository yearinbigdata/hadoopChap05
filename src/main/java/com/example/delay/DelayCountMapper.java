package com.example.delay;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.example.parser.AirLinePerformanceParser;

public class DelayCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	final IntWritable one = new IntWritable(1);

	Text outputKey = new Text();

	String workType;

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		workType = context.getConfiguration().get("workType");
	}

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		AirLinePerformanceParser parser = new AirLinePerformanceParser(value);

		if ("departure".equals(workType)) {
			/*
			 * 출발지연
			 */
			if (parser.isDepatureDelayAvailable()) {
				if (parser.getDepartureDelayTime() > 0) {
					outputKey.set(parser.getYear() + "," + parser.getMonth());
					context.write(outputKey, one);
				}
			}
		} else {
			/*
			 * 도착지연
			 */
			if (parser.isArriveDelayAvailable()) {
				if (parser.getArriveDelayTime() > 0 ) {
					outputKey.set(parser.getYear() + "," + parser.getMonth());
					context.write(outputKey, one);
				}
			}
		}

	}

}
