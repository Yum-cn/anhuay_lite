package com.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

public class Crc {

	/**
	 * 使用CheckedInputStream计算CRC
	 */
	public static long checksumInputStream(String filepath) throws IOException {
		InputStream inputStreamn = new FileInputStream(filepath);
		CRC32 crc = new CRC32();

		int cnt;

		while ((cnt = inputStreamn.read()) != -1) {
			crc.update(cnt);
		}
		return crc.getValue();
	}

	/**
	 * 采用BufferedInputStream的方式加载文件
	 */
	public static long checksumBufferedInputStream(String filepath) throws IOException {
		InputStream inputStream = new BufferedInputStream(new FileInputStream(filepath));

		CRC32 crc = new CRC32();

		int cnt;

		while ((cnt = inputStream.read()) != -1) {
			crc.update(cnt);
		}
		return crc.getValue();
	}

	public static long checksumRandomAccessFile(String filepath) throws IOException {
		RandomAccessFile randAccfile = new RandomAccessFile(filepath, "r");
		long length = randAccfile.length();
		CRC32 crc = new CRC32();

		for (long i = 0; i < length; i++) {
			randAccfile.seek(i);
			int cnt = randAccfile.readByte();
			crc.update(cnt);
		}
		return crc.getValue();
	}

	public static long checksumMappedFile(String filepath) throws IOException {
		FileInputStream inputStream = new FileInputStream(filepath);
		FileChannel fileChannel = inputStream.getChannel();

		int len = (int) fileChannel.size();

		MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, len);

		CRC32 crc = new CRC32();

		for (int cnt = 0; cnt < len; cnt++) {

			int i = buffer.get(cnt);
			crc.update(i);
		}
		return crc.getValue();
	}

	public static void main(String[] args) {
		String filepath = "D:\\var\\uploaded_files\\0d279347-100d-4793-b6e4-55acbf72ad9c.txt";
		try {
			System.out.println("Input Strea method:");

			long start_timer = System.currentTimeMillis();
			long crc = checksumInputStream(filepath);
			long end_timer = System.currentTimeMillis();
			System.out.println(Long.toHexString(crc));
			System.out.println((end_timer - start_timer) + " ms");
			System.out.println("///////////////////////////////////////////////////////////");

			System.out.println("Buffered Input Stream method:");
			start_timer = System.currentTimeMillis();
			crc = checksumBufferedInputStream(filepath);
			end_timer = System.currentTimeMillis();
			System.out.println(Long.toHexString(crc));
			System.out.println((end_timer - start_timer) + " ms");

			System.out.println("///////////////////////////////////////////////////////////");

			System.out.println("Random Access File method:");
			start_timer = System.currentTimeMillis();
			crc = checksumRandomAccessFile(filepath);
			end_timer = System.currentTimeMillis();
			System.out.println(Long.toHexString(crc));
			System.out.println((end_timer - start_timer) + " ms");

			System.out.println("///////////////////////////////////////////////////////////");

			System.out.println("Mapped File method:");
			start_timer = System.currentTimeMillis();
			crc = checksumMappedFile(filepath);
			end_timer = System.currentTimeMillis();
			System.out.println(Long.toHexString(crc));
			System.out.println((end_timer - start_timer) + " ms");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}