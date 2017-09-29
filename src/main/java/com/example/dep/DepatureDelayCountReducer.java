package com.example.dep;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DepatureDelayCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	IntWritable outValue = new IntWritable();		//성능향상
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
	//						 연도별		list를 count해야함
		int sum=0;
		for(IntWritable v : values){
			sum += v.get();
		}//연도별로 들어온 리스트를 '집계' 처리함.
		
		outValue.set(sum);							//성능향상
		
		context.write(key, outValue);				//재활용->성능향상
	
	}

}
