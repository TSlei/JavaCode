package com.zl.nio;

import java.util.StringTokenizer;

public class BufferDemo {
	public static void main(String[] args) {
		//BufferDemo.test();
		try {
			BufferDemo.testSpeed();
			BufferDemo.testSpeed2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void writerFile() throws Exception{
		String data = "a,b,c,d"; 
		data.split(",");
		StringTokenizer st = new StringTokenizer(data,",");
		for(int i = 0; i < 3; i++){
			
			System.out.println(st.nextToken());
		}
		st = new StringTokenizer(data,","); 
		System.out.println(st.nextElement());
		System.out.println(data);
	}

	public static void test(){
		String[] data = "a,b;c.d".split("[,|;|.]");
		for(int i = 0; i<data.length; i++){
			System.out.print(data[i] + " ");
		}
	}
	
	public static void testSpeed(){
		String str = null;
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 1000000; i++){
			sb.append(i);
			sb.append(",");
		}
		str = sb.toString();
		long start = System.currentTimeMillis();
		String[] list = str.split(",");
		long time = System.currentTimeMillis() - start;
		System.out.println(time + " size = " + list.length);
		System.out.println(list[50000] + list[999999]);
	}
	
	public static void testSpeed2(){
		String str = null;
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 1000000; i++){
			sb.append(i);
			sb.append(",");
		}
		str = sb.toString();
		String[] list = new String[1000000];
		String tmp = str;
		long start = System.currentTimeMillis();
		int i = 0;
		while(true){
			String split = null;
			int j = tmp.indexOf(",");
			if(j < 0) 
				break;
			split = tmp.substring(0,j);
			list[i] = split;
			tmp = tmp.substring(j+1);
			i++;
		}
		
		long time = System.currentTimeMillis() - start;
		System.out.println(time + " size = " + list.length);
		System.out.println(list[50000] + list[999999]);
	}
}	
