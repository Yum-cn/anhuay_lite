package com.common.id;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * Snowflake Twitter
 *  0 41 51 64 +-----------+------+------+ |time |pc |inc |
 *  +-----------+------+------+ 前41bits是以微秒为单位的timestamp。
 *  接着10bits是事先配置好的机器ID。 最后12bits是累加计数器。
 * @author Yum
 */
public class IdWorker {

	private final long workerId;
	// 1376 1103 3134 6 13*4
	private final static long twepoch = 1303895660503L;
	private long sequence = 0L;
	private final long workerIdBits = 10L;
	private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;
	private final long sequenceBits = 12L;

	private final long workerIdShift = this.sequenceBits;
	private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;
	private final long sequenceMask = -1L ^ -1L << this.sequenceBits;

	private long lastTimestamp = -1L;

	public IdWorker(long workerId) {
		super();
		if (workerId > this.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
		}
		this.workerId = workerId;
	}
	
	public IdWorker() {
		super();
		long workerId = NumberUtils.toLong(System.getProperty("idWorker"),1);
		if (workerId > this.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
		}
		this.workerId = workerId;
	}

	public synchronized long nextId() {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) {
			this.sequence = this.sequence + 1 & this.sequenceMask;
			if (this.sequence == 0) {
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}
		if (timestamp < this.lastTimestamp) {
			new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
		}

		this.lastTimestamp = timestamp;
		return timestamp - this.twepoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
	}

	private long tilNextMillis(long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}
	/**
	 * 取时间戳
	 * @author   Yum
	 */
	public static long getTimeStamp(final long workerId){
//		IdWorker idWorker = new IdWorker();
//		long timeStamp = workerId>>idWorker.timestampLeftShift;
//		return timeStamp+idWorker.twepoch;
		
		//private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;
		long timeStamp = workerId>>22L;
		//private final long twepoch = 1303895660503L;
		return timeStamp+twepoch;
	}
	
	public static void main(String[] args) {
//		long workerIdBits = 10L;
//		long maxWorkerId = -1L ^ -1L << workerIdBits;
//		System.out.println(maxWorkerId);
//		IdWorker worker2 = new IdWorker(1);
//		for (int i = 0; i < 1; i++) {
//			long id = worker2.nextId();
//			System.out.println(("生成ID:"+id + ""));
//			System.out.println(System.currentTimeMillis()+">>"+IdWorker.getTimeStamp(id));
//			//System.out.println(new Date(1449586998057l));
//		}
		
		
		System.out.println(DateFormatUtils.format(IdWorker.getTimeStamp(608816848992997376l), "yyyy/MM/dd/"));

	}
}
