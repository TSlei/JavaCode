package com.zl.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaDemo {
//	public static void main(String[] args) {
//		LambdaDemo.test2();
//	}
//
//	public static void test1() {
//		List<String> names = Arrays.asList("peter", "anna", "abcc", "mike", "xenia");
//		Collections.sort(names, new Comparator<String>() {
//			@Override
//			public int compare(String a, String b) {
//				return a.compareTo(b);
//			}
//		});
//		systest(names);
//	}
//
//	public static void test2() {
//		List<String> names = Arrays.asList("peter", "anna", "abcc", "mike", "xenia");
//		Collections.sort(names, (String a, String b) -> a.compareTo(b));
//		// Java编译器可以自动推导出参数类型，所以你可以不用再写一次类型。
//		// Collections.sort(names, (a, b) -> a.compareTo(b));
//		systest(names);
//	}
//
//
//
//	public static void systest(List<String> lists) {
//		lists.forEach(list -> {
//			System.out.println(list);
//		});
//	}
}
