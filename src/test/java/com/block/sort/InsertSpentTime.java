package com.block.sort;

import java.util.Random;

public class InsertSpentTime {

	public static void main(String[] args) {
		int[] arr = new int[1000000];
		Random rad = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rad.nextInt();
		}
		long start = System.currentTimeMillis();
		sort(arr);
		System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
	}

	public static void sort(int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		int i = 0, j, len = arr.length, temp = 0;
		for (i = 1; i < len; i++) {
			temp = arr[i];
			for (j = i - 1; j >= 0; j--) {
				if (temp < arr[j]) {
					arr[j + 1] = arr[j];
				} else {
					break;
				}
			}
			// 一定要放在这里，不能在else里面赋值，因为当j正常退出循环时，并不会进入else块
			arr[j + 1] = temp;
		}
	}
}
