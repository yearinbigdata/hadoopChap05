package com.example.dep;

import static org.junit.Assert.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DepartureDelayCountMapperTest {
	
	MapDriver<LongWritable, Text, Text, IntWritable> map;	//맵리듀스 실행환경 로드

	//				   year, month, dayofmonth.......,uniqueCarrier,.........,arrdelay, depdelay, distance
	String recode1 = "2008,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,34,34,IND,BWI,515,3,10,0,,0,2,0,0,0,32";
	String recode2= "2008,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,NA,NA,IND,BWI,NA,3,10,0,,0,2,0,0,0,32";
	
	@Before
	public void setUp() throws Exception {		//초기화
		map = MapDriver.newMapDriver(new DepartureDelayCountMapper());
		//맵드라이버를 통해서 mapper 테스트
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMapperAvailable() {
		map.withInput(new LongWritable(0), new Text(recode1));	//입력
		map.withOutput(new Text("2008"), new IntWritable(1));	//출력있음. 출력이 없거나 틀리면 (2008,1) 에러
		
//		map.runTest();
		
	}
	
	@Test
	public void testMapperNA() {
		map.withInput(new LongWritable(0), new Text(recode2));	//입력
//		map.withOutput(new Text("2008"), new IntWritable(1));	//출력없음. 따라서 출력이 있으면 에러난다.
		
//		map.runTest();
		
	}

}
