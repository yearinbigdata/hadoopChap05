package com.example.core;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

public class CounterTest {
	
	static enum XXX {
		cnt, cnt2;
	}

	static class MyCounterMapper extends Mapper<Text, IntWritable, Text, IntWritable> {

		@Override
		protected void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
			
			for (int i=0; i<value.get()%10; i++ ) {
				context.getCounter("XXX", "cnt").increment(1);
				context.getCounter(XXX.cnt2).increment(2);
			}
			
			context.write(key, value);
		}
	}

	MapDriver<Text, IntWritable, Text, IntWritable> mapDriver;
	
	@Before
	public void setUp() throws Exception {
		mapDriver = MapDriver.newMapDriver(new MyCounterMapper());
	}

	@Test
	public void testMap() throws IOException {
		
		mapDriver.withInput(new Text("java"), new IntWritable(14))
				  .withOutput(new Text("java"), new IntWritable(14))
				  .withCounter("XXX", "cnt", 4)
				  .withCounter(XXX.cnt2, 8)
				  .runTest();

	}
	
	@Test
	public void testMap2() throws IOException {
		
		mapDriver.withInput(new Text("java"), new IntWritable(14))
				  .withOutput(new Text("java"), new IntWritable(14))
				  .withCounter("XXX", "cnt", 4)
//				  .withCounter(XXX.cnt2, 8)
				  .withCounter("com.example.core.CounterTest$XXX", "cnt2", 8)
				  .runTest();

//		System.out.println(XXX.class.getName());
	}
	
	
	@Test
	public void testMethodChainning() {
		String result = "Hello World".toLowerCase()
										 .substring(0, 5)
										 .concat(" Java !")
										 .toUpperCase();
		
//		assertThat(result, is("hello world"));
//		assertThat(result, is("hello"));
//		assertThat(result, is("hello Java !"));
		assertThat(result, is("HELLO JAVA !"));
		
	}

}
