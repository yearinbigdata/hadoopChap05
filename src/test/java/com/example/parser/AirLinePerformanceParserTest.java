package com.example.parser;

import static org.junit.Assert.*;

import org.apache.hadoop.io.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AirLinePerformanceParserTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("############");
		System.out.println("# setUp()...");
		System.out.println("############");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("###############");
		System.out.println("# tearDown()...");
		System.out.println("###############");
	}

	//				   year, month, dayofmonth.......,uniqueCarrier,.........,arrdelay, depdelay, distance
	String string = "2008,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,34,34,IND,BWI,515,3,10,0,,0,2,0,0,0,32";
	String string2= "2008,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,NA,NA,IND,BWI,NA,3,10,0,,0,2,0,0,0,32";
	
	@Test
	public void testConstructor() {
		System.out.println("testConstructor()...");
		Text text = new Text(string);
		
		/*
		 * year = 2008
		 * month = 1
		 * arrDelay = 34, arriveAvailable = true
		 * depDelay = 34, departureAvailable = true
		 * distance = 515, distanceAvailable = true
		 */
		
		AirLinePerformanceParser parser = new AirLinePerformanceParser(text);
		//대조 테스트
		assertEquals(2008, parser.getYear());	//같지 않으면 exception발생
		assertEquals(1, parser.getMonth());
		assertEquals(34, parser.getArriveDelayTime());
		assertEquals(34, parser.getDepartureDelayTime());
		assertEquals(515, parser.getDistance());
		assertTrue(parser.isArriveDelayAvailable());	//true가 아니면 exception발생
		assertTrue(parser.isDepatureDelayAvailable());
		assertTrue(parser.isDistanceAvailable());
		
		System.out.println("year =" + parser.getYear());
		System.out.println("month = " + parser.getMonth());
		System.out.println("arrDelay = " + parser.getArriveDelayTime());
		System.out.println("depDelay = " + parser.getDepartureDelayTime());
		System.out.println("distance = " + parser.getDistance());
		System.out.println("arriveAvailable = " + parser.isArriveDelayAvailable());
		System.out.println("departureAvailable = " + parser.isDepatureDelayAvailable());
		System.out.println("distanceAvailable = " + parser.isDistanceAvailable());
	}
	
	@Test
	public void testConstructor2() {
		System.out.println("testConstructor2()...");
		AirLinePerformanceParser parser = new AirLinePerformanceParser(new Text(string2));
		
		assertEquals(2008, parser.getYear());
		assertEquals(1, parser.getMonth());
		
		assertFalse(parser.isArriveDelayAvailable());
		assertFalse(parser.isDepatureDelayAvailable());
		assertFalse(parser.isDistanceAvailable());
	}

}
