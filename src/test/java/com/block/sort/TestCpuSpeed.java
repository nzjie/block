package com.block.sort;

import java.util.Random;

public class TestCpuSpeed {

	public static void main(String[] args) {

		int[] arr = new int[100000000];
		Random rad = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rad.nextInt();
		}
		int temp = 0;
		long start = System.currentTimeMillis();
		for (int i = 0, len = arr.length; i < len - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				temp = arr[i];
			}
		}
		System.out.println("耗时:" + (System.currentTimeMillis() - start));
		System.out.println(temp);

	}
}
