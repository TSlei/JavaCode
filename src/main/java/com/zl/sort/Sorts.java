package com.zl.sort;

import com.zl.utils.TimeComp;

public class Sorts {
	static int swapCount = 0;

	public static void main(String[] args) {
		int[] arr = { 10, 3, 5, 2, 7, 9, 8, 1, 300, 200, 250 };

		TimeComp tc = new TimeComp();
		quicksort(arr, 0, arr.length - 1);
		System.out.println("use:" + tc.comsum());
		print(arr);
//		System.out.println("swapCount = " + swapCount);
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

	//快速排序
	public static void quicksort(int a[], int low, int hight) {
		int i, j, index;
		if (low > hight) {
			return;
		}
		i = low;
		j = hight;
		index = a[i]; // 用子表的第一个记录做基准
		while (i < j) { // 从表的两端交替向中间扫描
			while (i < j && a[j] >= index)
				j--;
			if (i < j)
				a[i++] = a[j];// 用比基准小的记录替换低位记录
			while (i < j && a[i] < index)
				i++;
			if (i < j) // 用比基准大的记录替换高位记录
				a[j--] = a[i];
		}
		a[i] = index;// 将基准数值替换回 a[i]
		quicksort(a, low, i - 1); // 对低子表进行递归排序
		quicksort(a, i + 1, hight); // 对高子表进行递归排序
	}

	public static void heapsort(int[] arr) {

	}

	public static void buildMaxheap(int[] arr, int start, int end) {
		// 根结点就是root = arr[start]

		for (int i = start; i < end; i++) {

		}

	}


	public static void compareAndSwap(int[] arr, int m, int n) {
		if (arr[m] > arr[n]) {
			swap(arr, m, n);
		}
	}

	public static void print(int[] arr){
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static void swap(int[] arr, int m, int n) {
		swapCount++;
		int tmp = arr[m];
		arr[m] = arr[n];
		arr[n] = tmp;
	}
}
