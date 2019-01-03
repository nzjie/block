package com.block.sort;

import java.util.Random;

/**
 * 冒泡
 * 
 * @author ajie
 *
 */
public class BubbleSort {

	static public void main(String... args) {

		int[] arr = new int[1000000];
		Random rad = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rad.nextInt();
		}
		// int arr[] = { 9, 12, 4, 21, 22, 10, 3, 5, 20 };
		long start = System.currentTimeMillis();
		// int[] arr = { 2, 3, 1, 0, 9, 4, 12, 5 };
		BubbleSort.sort(arr);
	/*	for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}*/
		System.out.println("耗时:" + (System.currentTimeMillis() - start) / 1000);
	}

	public static void sort(int[] arr) {
		if (null == arr || arr.length <= 1) {
			return;
		}
		int i = 0, j = 0, temp = 0, len = arr.length;
		for (; i < len - 1; i++) {
			for (j = 0; j < len - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}

			}

		}

	}

}