package com.block.sort;

import java.util.Random;

/**
 * 快速排序<br>
 * 
 * 算法思想：基于分治的思想，是冒泡排序的改进型。首先在数组中选择一个基准点（该基准点的选取可能影响快速排序的效率，后面讲解选取的方法），
 * 然后分别从数组的两端扫描数组
 * ，设两个指示标志（lo指向起始位置，hi指向末尾)，首先从后半部分开始，如果发现有元素比该基准点的值小，就交换lo和hi位置的值
 * ，然后从前半部分开始扫秒，发现有元素大于基准点的值
 * ，就交换lo和hi位置的值，如此往复循环，直到lo>=hi,然后把基准点的值放到hi这个位置。一次排序就完成了
 * 。以后采用递归的方式分别对前半部分和后半部分排序，当前半部分和后半部分均有序时该数组就自然有序了。
 * 
 * @author ajie
 *
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] arr = new int[100000000];
		Random rad = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rad.nextInt();
		}
		// int arr[] = { 9, 12, 4, 21, 22, 10, 3, 5, 20 };
		long start = System.currentTimeMillis();
		quickSort(arr);
		/*for (int i : arr) {
			System.out.println(i);
		}*/
		System.out.println("耗时:" + (System.currentTimeMillis() - start) );
	}

	public static void quickSort(int arr[]) {
		sort(arr, 0, arr.length - 1);
	}

	/**
	 * 递归调用该方法
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 */
	public static void sort(int arr[], int low, int high) {
		if (low < high) {
			int partition = partition(arr, low, high);
			sort(arr, partition + 1, high);
			sort(arr, low, partition - 1);
		}

	}

	public static int partition(int[] arr, int low, int high) {
		while (low < high) {
			// 第一个和最后一个对比，如果小于，则后指针-1
			while (arr[high] >= arr[low] && low < high) {
				high--;
			}
			swap(arr, high, low);
			// 第一个和后指针位置的数作对比，如果小于，前指针+1;
			while (arr[low] <= arr[high] && low < high) {
				low++;
			}
			swap(arr, high, low);
		}
		return low;
	}

	/**
	 * high和low交换
	 * 
	 * @param arr
	 * @param high
	 * @param low
	 */
	public static void swap(int[] arr, int high, int low) {
		int temp = arr[high];
		arr[high] = arr[low];
		arr[low] = temp;
	}

}
