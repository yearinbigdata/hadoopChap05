package com.example.delay.counter;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.example.parser.AirLinePerformanceParser;
import com.example.parser.DelayCounters;

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
					context.getCounter(DelayCounters.delay_departure).increment(1);
				} else if (parser.getDepartureDelayTime() == 0) {
					context.getCounter(DelayCounters.scheduled_departure).increment(1);
				} else {
					context.getCounter(DelayCounters.early_departure).increment(1);
				}
			} else {
				context.getCounter(DelayCounters.not_available_departure).increment(1);
			}
		} else {
			/*
			 * 도착지연
			 */
			if (parser.isArriveDelayAvailable()) {
				if (parser.getArriveDelayTime() > 0 ) {
					outputKey.set(parser.getYear() + "," + parser.getMonth());
					context.write(outputKey, one);
					context.getCounter(DelayCounters.delay_arrival).increment(1);
				} else if (parser.getArriveDelayTime() == 0) {
					context.getCounter(DelayCounters.scheduled_arrival).increment(1);
				} else {
					context.getCounter(DelayCounters.early_arrival).increment(1);
				}
			} else {
				context.getCounter(DelayCounters.not_available_arrival).increment(1);
			}
		}

	}

}
