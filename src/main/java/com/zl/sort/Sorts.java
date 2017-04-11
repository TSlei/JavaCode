package com.zl.sort;

import com.zl.utils.TimeComp;

public class Sorts {
	static int swapCount = 0;

	public static void main(String[] args) {
		int[] arr = { 10, 3, 5, 2, 7, 9, 8, 1, 300, 200, 250 };
		// int max = 10000;
		// int[] arr = new int[max];
		// Random random = new Random();
		// for (int i = 0; i < max; i++) {
		// arr[i] = random.nextInt(max * 2);
		// }

		TimeComp tc = new TimeComp();
		sort(arr);
		System.out.println("use:" + tc.comsum());
		//print(arr);
		System.out.println("swapCount = " + swapCount);
	}

	// 选择排序
	public static void sort(int[] arr) {
		int length = arr.length;
		int min = 0;
		for (int n = 0; n < length - 1; n++) {
			min = n;
			// 选出最小的
			for (int m = n + 1; m < length; m++) {
				if (arr[min] > arr[m]) {
					min = m;
				}
			}
			if (min != n) {
				swap(arr, min, n);
			}
		}
	}

	// 冒泡
	public static void mpsort(int[] arr) {
		int length = arr.length;
		for (int n = 0; n < length; n++) {
			for (int m = length - 1; m > n; m--) {
				compareAndSwap(arr, m - 1, m);
			}
		}
	}

	// 插入排序
	public static void insort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			// 取出arr[i]
			for (int m = 0; m < i; m++) {
				compareAndSwap(arr, m, i);
			}
		}
	}

	public static void xesort(int[] arr) {
		// int d = arr.length;
		// while (true) {
		// d = d / 2;
		//
		// for(int i = )
		//
		// if (d == 1) {
		// break;
		// }
		// }
	}

	public static void heapsort(int[] arr) {

	}

	public static void buildMaxheap(int[] arr, int start, int end) {
		// 根结点就是root = arr[start]

		for (int i = start; i < end; i++) {

		}

	}

//	public static void print(int[] arr) {
//		StringJoiner sj = new StringJoiner(",", "[", "]");
//		Arrays.stream(arr).forEach(x -> sj.add("" + x));
//		System.out.println(sj);
//	}

	public static void compareAndSwap(int[] arr, int m, int n) {
		if (arr[m] > arr[n]) {
			swap(arr, m, n);
		}
	}

	public static void swap(int[] arr, int m, int n) {
		swapCount++;
		int tmp = arr[m];
		arr[m] = arr[n];
		arr[n] = tmp;
//		arr[m] = arr[m] ^ arr[n];
//		arr[n] = arr[m] ^ arr[n];
//		arr[m] = arr[m] ^ arr[n];
	}
}
