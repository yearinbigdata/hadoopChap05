package com.example;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class ListSortTest {

	List<Integer> list;
	@Before
	public void setUp() throws Exception {
		list = Arrays.asList(3, 6, 2, 1, 7);
	}

	@Test
	public void testAsc() {
		System.out.println(list);	// sort before
		list.sort(null);
		System.out.println(list);	// sort after

		assertArrayEquals(Arrays.asList(1, 2, 3, 6, 7).toArray(), list.toArray());
		assertThat(list, CoreMatchers.is(Arrays.asList(1, 2, 3, 6, 7)));
	}
	
	@Test
	public void testDesc() {
		System.out.println(list);	// sort before
		list.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return 0;
			}
			
		});
		System.out.println(list);	// sort after
	}
	
	@Test
	public void testDesc2() {
		list.stream().sorted(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return -o1.compareTo(o2);
			}
		}).forEach(e -> {
			System.out.println(e);
		});
	}

}
