package com.spud.nimbus.leaf.segment.model;

import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author spud
 * @date 2024/2/24
 */
@Data
@ToString
public class Segment {

	private final SegmentBuffer buffer;

	private AtomicLong value = new AtomicLong(0);

	private volatile long max;

	private volatile int step;

	private volatile int randomStep;

	public Segment(SegmentBuffer buffer) {
		this.buffer = buffer;
	}

	public long getIdle() {
		return this.getMax() - getValue().get();
	}

}
