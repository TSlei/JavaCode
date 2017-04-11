package com.zl.nio;


import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
	
	public static void main(String[] args) {
		RandomAccessFile fromFile;
		try {
			fromFile = new RandomAccessFile("D:\\fromFile.txt", "rw");
			FileChannel fromChannel = fromFile.getChannel();

			RandomAccessFile toFile = new RandomAccessFile("D:\\toFile.txt", "rw");
			FileChannel toChannel = toFile.getChannel();

			long position = 0;
			long count = fromChannel.size();
			System.out.println(count);//读取的大小
			toChannel.transferFrom(fromChannel, position, count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
