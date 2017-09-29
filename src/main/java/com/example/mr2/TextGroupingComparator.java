package com.example.mr2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TextGroupingComparator extends WritableComparator {

	protected TextGroupingComparator() {
		super(Text.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		Text k1 = (Text)a;
		Text k2 = (Text)b;
		
		int i1 = Integer.parseInt(k1.toString());
		int i2 = Integer.parseInt(k2.toString());
//		System.out.println(i1 + " = " + i2);
		
		return i1 / 97 == i2 / 97 ? 0 : 1;
	}
	
}
