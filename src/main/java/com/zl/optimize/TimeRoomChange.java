package com.zl.optimize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class TimeRoomChange {
	public static int arrayLen=100000;
	
	public static void main(String[] args) {
		int a=3;
		int b=4;
		Time2Room(a, b);
		
		
		//getArray();
	}
	
	//时间空间，计算这里不需要一个temp，稍微分配一个空间，但是多了计算
	public static void Time2Room(int a,int b){
		System.out.println("a="+a);
		System.out.println("b="+b);
		a=a+b;
		b=a-b; 	//现在b等于之前的a了
		a=a-b;	//a-b得到之前的b
		System.out.println("交换之后-------");
		System.out.println("a="+a);
		System.out.println("b="+b);
	}
	
	
	//通过增加空间来节约时间
	public static void getArray(){
		int[] a=new int[arrayLen];
		int[] b=new int[arrayLen];
		Map<Integer, Object> map=new HashMap<Integer, Object>();
		int count=0;
		while(count<a.length){
			int value=(int)(Math.random()*arrayLen*10)+1;
			if(map.get(value)==null){
				map.put(value, value);
				a[count]=value;
				count++;
			}
		}
		System.arraycopy(a, 0, b, 0, a.length);
		long start=System.currentTimeMillis();
		Arrays.sort(a);
		System.out.println((System.currentTimeMillis()-start)+"ms");
		
//		System.arraycopy(b, 0, a, 0, b.length);
//		start=System.currentTimeMillis();
//		Room2Time(a);
//		System.out.println((System.currentTimeMillis()-start)+"ms");
	}
	
	public static void Room2Time(int[] array){
		int i=0;
		int max=array[0];
		int l=array.length;
		
		for(i=1;i<l;i++ ){
			if(array[i]>max){
				max=array[i];
				int[] temp=new int[max+1];
				for(i=0;i<l;i++){
					temp[array[i]]=array[i];
					int j=0;
					int max1=max+1;
					for(i=0;i<max1;i++){
						if(temp[i]>0){
							array[j++]=temp[i];
						}
					}
				}
			}
		}
	}
}
