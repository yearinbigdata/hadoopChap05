package com.example.mr2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TextComparator extends WritableComparator {

	protected TextComparator() {
		super(Text.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		Text k1 = (Text)a;
		Text k2 = (Text)b;

		return -k1.compareTo(k2);
	}
	
}
