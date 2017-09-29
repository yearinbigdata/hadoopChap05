package com.example.dep;

import static org.junit.Assert.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DepatureDelayCountMapperReducerTest {
	//					mapInput			mapOutput&reduceInput	reduceOutput
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduce;
	
	//				   year, month, dayofmonth.......,uniqueCarrier,.........,arrdelay, depdelay, distance
	String recode1 = "2008,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,34,34,IND,BWI,515,3,10,0,,0,2,0,0,0,32";
	String recode2=  "2008,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,NA,NA,IND,BWI,NA,3,10,0,,0,2,0,0,0,32";
	String recode3 = "1987,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,34,34,IND,BWI,515,3,10,0,,0,2,0,0,0,32";
	String recode4=  "1987,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,NA,50,IND,BWI,NA,3,10,0,,0,2,0,0,0,32";
	//출력 2008, 1987이므로 출력도 2개여야 함.
	
	@Before
	public void setUp() throws Exception {
		mapReduce = MapReduceDriver.newMapReduceDriver(new DepartureDelayCountMapper(), new DepatureDelayCountReducer());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMapReduce() {
		mapReduce.withInput(new LongWritable(), new Text(recode1));
		mapReduce.withInput(new LongWritable(), new Text(recode2));		//na이므로 필터링되어서 들어오지 않음
		mapReduce.withInput(new LongWritable(), new Text(recode3));		
		mapReduce.withInput(new LongWritable(), new Text(recode4));
		
		mapReduce.withOutput(new Text("1987"), new IntWritable(2));		
		mapReduce.withOutput(new Text("2008"), new IntWritable(1));
		
//		mapReduce.runTest();
	}

}
