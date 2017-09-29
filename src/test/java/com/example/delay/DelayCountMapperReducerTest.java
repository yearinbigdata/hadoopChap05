package com.example.delay;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DelayCountMapperReducerTest {
	
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduce;
	Configuration conf;
	
    //               year, month, dayofmonth,.......,uniqueCarrier,.........,arrDelay, depDelay, distance
	String recode1 = "2008,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,34,34,IND,BWI,515,3,10,0,,0,2,0,0,0,32";
	String recode2 = "2008,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,60,NA,IND,BWI,NA,3,10,0,,0,2,0,0,0,32";
	String recode3 = "1987,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,34,34,IND,BWI,515,3,10,0,,0,2,0,0,0,32";
	String recode4 = "1987,7,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,NA,50,IND,BWI,NA,3,10,0,,0,2,0,0,0,32";

	@Before
	public void setUp() throws Exception {
		mapReduce = MapReduceDriver.newMapReduceDriver(new DelayCountMapper(), new DelayCountReducer());
		conf = new Configuration();
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testMapReduce1() throws IOException {
		conf.setStrings("workType", "arrive");
		
		mapReduce.setConfiguration(conf);
		mapReduce.withInput(new LongWritable(), new Text(recode1));
		mapReduce.withInput(new LongWritable(), new Text(recode2));
		mapReduce.withInput(new LongWritable(), new Text(recode3));
		mapReduce.withInput(new LongWritable(), new Text(recode4));
		mapReduce.withOutput(new Text("1987,1"), new IntWritable(1));
		mapReduce.withOutput(new Text("2008,1"), new IntWritable(2));
		
		mapReduce.runTest();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMapReduce2() throws IOException {
		conf.setStrings("workType", "departure");
		
		mapReduce.setConfiguration(conf);
		mapReduce.withInput(new LongWritable(), new Text(recode1));
		mapReduce.withInput(new LongWritable(), new Text(recode2));
		mapReduce.withInput(new LongWritable(), new Text(recode3));
		mapReduce.withInput(new LongWritable(), new Text(recode4));
		mapReduce.withOutput(new Text("1987,1"), new IntWritable(1));
		mapReduce.withOutput(new Text("1987,7"), new IntWritable(1));
		mapReduce.withOutput(new Text("2008,1"), new IntWritable(1));
		
		mapReduce.runTest();
	}	

}
