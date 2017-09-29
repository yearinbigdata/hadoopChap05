package com.example;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class BeanSortTest {

	static class DateKey implements Comparable<DateKey> {
		private String year;
		private String month;

		@Override
		public String toString() {
			return year + "," + month;
		}

		@Override
		public boolean equals(Object obj) {
			DateKey dk = (DateKey) obj;

			return year.equals(dk.year) && month.equals(dk.month);
		}

		@Override
		public int compareTo(DateKey o) {
			int cmp = this.year.compareTo(o.year);

			if (cmp == 0) {
				int m1 = Integer.parseInt(this.month);
				int m2 = Integer.parseInt(o.month);

				if (m1 < m2)
					cmp = -1;
				else if (m1 == m2)
					cmp = 0;
				else
					cmp = 1;
			}

			return cmp;
		}

		public DateKey() {

		}

		public DateKey(String year, String month) {
			this.year = year;
			this.month = month;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getMonth() {
			return month;
		}

		public void setMonth(String month) {
			this.month = month;
		}

	}

	List<DateKey> keys;

	@Before
	public void setUp() throws Exception {
		keys = new ArrayList<>();
		Random r = new Random();

		for (int i = 0; i < 10000000; i++) {
			String year = r.nextInt(22) + 2008 + "";
			String month = r.nextInt(12) + 1 + "";
			keys.add(new DateKey(year, month));
		}
	}

	@Test
	public void testAsc() {
		
//		System.out.println(keys); // sort before
		LocalDateTime start = LocalDateTime.now();
		keys.sort(null);
		LocalDateTime end = LocalDateTime.now();
		System.out.println(Duration.between(start, end).toMinutes());
//		System.out.println(keys); // sort before

	}

//	@Test
//	public void testDesc() {
//		//
//		// year : desc , month : desc
//		//
//
//		System.out.println(Arrays.toString(keys)); // sort before
//		// Arrays.sort(keys);
//		Arrays.sort(keys, new Comparator<DateKey>() {
//
//			@Override
//			public int compare(DateKey o1, DateKey o2) {
//				return -o1.compareTo(o2);
//			}
//		});
//		System.out.println(Arrays.toString(keys)); // sort after
//
//		assertArrayEquals(new DateKey[] { new DateKey("2017", "7"), new DateKey("2017", "3"), new DateKey("2008", "10"),
//				new DateKey("2008", "5"), new DateKey("2007", "10"), new DateKey("2007", "7"), }, keys);
//	}
//
//	@Test
//	public void testDesc2() {
//		//
//		// year : desc , month : asc
//		//
//
//	}

}
