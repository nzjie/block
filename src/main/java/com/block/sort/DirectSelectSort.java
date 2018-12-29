package com.block.sort;

/**
 * 直接选择排序，基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；<br>
 * 然后在剩下的数当中再找最小的与第二个位置的数交换， 如此循环到倒数第二个数和最后一个数比较为止。
 * 
 * @author niezhenjie
 */
public class DirectSelectSort {

	public static void sort(int[] arr) {
		if (null == arr || arr.length <= 1) {
			return;
		}
		int i = 0, j = 0, minIdx = 0, len = arr.length, temp = 0;
		for (; i < len; i++) {
			for (j = i + 1; j < len; j++) {
				if (arr[minIdx] > arr[j]) {
					minIdx = j;
				}
			}
			temp = arr[i];
			arr[i] = arr[minIdx];
			arr[minIdx] = temp;
			minIdx = i + 1;
		}
	}

	public static void main(String[] args) {
		int[] arr = { 1, 3, 9, 0, 4, 12, -3, 7 };
		DirectSelectSort.sort(arr);
		for (int i : arr) {
			System.out.println(i);

		}
	}

}
