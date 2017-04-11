package com.zl.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo {
	
	public static void main(String[] args) {
		RandomAccessFile aFile = null;
		try {
			aFile = new RandomAccessFile("D:\\test.txt", "rw");
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buff = ByteBuffer.allocate(48);   //每次读取48个长度的字节
			int bytesRead = inChannel.read(buff);   //read into buffer,返回读取的长度
			
			//read from buffer into channel.
			//int bytesWritten = inChannel.write(buf);

			while (bytesRead != -1) {
				System.out.println("Read ：" + bytesRead);
				buff.flip();	//flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。
				while (buff.hasRemaining()) {
					System.out.print((char) buff.get());
				}
				buff.clear();	//position将被设回0，limit被设置成 capacity的值，Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据。
				bytesRead = inChannel.read(buff); //再次接着position的位置去通过channel读取buff长度的数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				aFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
