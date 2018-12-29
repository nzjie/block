package com.block.sort;

import java.util.Random;

/**
 * 测试一般的遍历查找最大值耗时 10000000个数
 * 
 * @author ajie
 *
 */
public class SnipGetMaxConsumingTestpet {

	public static void main(String[] args) {
		int[] arr = new int[10000000];
		Random rad = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rad.nextInt();
		}
		long start = System.currentTimeMillis();
		getMax(arr);
		System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
	}

	public static int getMax(int[] arr) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}
}
