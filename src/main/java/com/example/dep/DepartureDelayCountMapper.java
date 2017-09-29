package com.example.dep;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.example.parser.AirLinePerformanceParser;

public class DepartureDelayCountMapper extends Mapper<LongWritable	, Text, Text, IntWritable>{
	
	//성능 향상 위해서 메소드 밖에서 생성
	final IntWritable one = new IntWritable(1);
	Text outputKey = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	
		AirLinePerformanceParser parser = new AirLinePerformanceParser(value);
		
		if (parser.isDepatureDelayAvailable()){	//true여야. false라면 무시
			if (parser.getDepartureDelayTime() > 0) {//0보다 커야 지연된 것
				outputKey.set(parser.getYear() + "");	//string으로 만들면서 년도로 셋팅
				context.write(outputKey, one);
			}//출발지연데이터를 사용. if를 통해서 출발지연데이터를 출력. 
			//여기에서 연도로만 출력한다. 1건 발생했다고 알려주면서.
			
		}
	
	}
}
