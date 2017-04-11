package com.zl.nio;

import java.io.RandomAccessFile;


public class RandomAccessFileDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getFileData();
	}
	
	public static void getFileData(){
		try {
			RandomAccessFile rf=new RandomAccessFile("D:\\test.txt","rw");   //读写操作
			for(int i=0;i<10;i++){
				rf.writeLong(i*1000);
			}
			rf.seek(1*8);  //跳过第一个long数据，把后面一个数据设置为1234，
			rf.writeLong(1234);
			rf.seek(0);		//将读写指针定位到文件开头
			for(int i=0;i<10;i++){
				System.out.println("Value"+i+":"+rf.readLong());
			}
			rf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

