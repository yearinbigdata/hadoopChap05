package com.example.calc;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalcuratorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("##### setUpBeforeClass()...");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("##### tearDownAfterClass()...");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("@@@ setUp()...");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@@@ tearDown()...");
	}

	@Test
	public void testAdd() {
		System.out.println("** testAdd()...");
		
		assertEquals(10, Calcurator.add(5, 5));
	}
	
	@Test
	public void testSubstract() {
		System.out.println("** testSubstract()...");
		
		assertThat(Calcurator.substract(10, 5), is(5));
	}

	@Test
	public void testMultipy() {
		System.out.println("** testMultipy()...");
		
		assertThat(Calcurator.multipy(10, 5), is(equalTo(5)));
	}
	
	@Test
	public void testDivide() {
		System.out.println("** testDivide()...");
	}	

	@Test
	public void testMod() {
		System.out.println("** testMod()...");
	}	

}
