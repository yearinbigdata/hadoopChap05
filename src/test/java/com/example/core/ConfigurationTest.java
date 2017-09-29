package com.example.core;

import static org.junit.Assert.*;

import java.util.Map.Entry;
import java.util.function.Consumer;

import org.apache.hadoop.conf.Configuration;
import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {

	Configuration conf;
	
	@Before
	public void setUp() {
		conf = new Configuration();
	}
	
	@Test
	public void testPrint1() {
		conf.forEach(new Consumer<Entry<String, String>>() {

			@Override
			public void accept(Entry<String, String> t) {
				System.out.println(t.getKey() + "=" + t.getValue());
			}
		});
	}
	
	@Test
	public void testPrint2() {
		conf.forEach(t -> {
			System.out.println(t.getKey() + "=" + t.getValue());
		});
	}
	
	@Test
	public void testWorkType1() {
		conf.setStrings("workType", "departure");
		System.out.println("workType=" + conf.get("workType"));
		assertEquals("departure", conf.get("workType"));
	}
	
	@Test
	public void testWorkType2() {
		conf.setStrings("workType", "arrive");
		
		assertThat(conf.get("workType"), is("arrive"));
	}
	
	
}
