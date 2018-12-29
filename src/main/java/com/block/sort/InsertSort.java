package com.block.sort;

/**
 * 插入排序
 * 
 * @author niezhenjie
 */
public class InsertSort {

	/**
	 * 升序 <br>
	 * 
	 * 实现思路：从0-j都是有序的，如果temp（本次循环做对比的数）小于data[j],则将data[j]向后移一位
	 * 如：此次对比的是第五次，则temp为data[5],data[0]--data[4]都是有序的（升序），将data[5]与data[4]作对比<br>
	 * 如果大于data[4]，则什么都不用做，直接调到下一个，如果小于，则继续比较data[3]，如果小于，则继续比较data[2]<br>
	 * 如果大于data[2]了，则temp放在data[2]之后，即data[3]，但是这样会使data[3]被占用，所以就需要在每次匹配的时候<br>
	 * 如果不成立，则将元素向后移动一位，如temp>data[4]不成立，则data[4]向后移一位，变成了data[5]如此类推
	 * 
	 * @param data
	 */
	public static void insertSortAsc(int data[]) {
		if (null == data || data.length <= 1) {
			return;
		}
		int i = 0, j = 0, len = data.length, temp = 0;
		for (i = 1; i < len; i++) {
			temp = data[i];
			for (j = i - 1; j >= 0; j--) {
				if (data[j] > temp) {
					data[j + 1] = data[j];
				} else {
					break;
				}
			}
			// 因为在最后一次条件满足时，已经执行了j--，所以在这里需要使用j+1
			data[j + 1] = temp;
		}
	}

	/**
	 * 降序
	 * 
	 * @param data
	 */
	public static void insertSortDesc(int data[]) {
		int i, j;
		for (i = 1; i < data.length; i++) {
			int temp = data[i];
			for (j = i - 1; j >= 0; j--) {
				if (temp > data[j]) {
					data[j + 1] = data[j];
				} else {
					break;
				}
			}
			data[j + 1] = temp;
		}
	}

	public static void main(String[] args) {
		int data[] = { 2, 324, 0, 1, 53, 5311, 3401, 1234, 6, -7, 568, 7, 878, 657, 987, 556, 64,
				23 };
		InsertSort.insertSortAsc(data);
		int j = 1;
		for (int i : data) {
			System.out.print(i + "\t\t");
			if (j++ % 4 == 0) {
				System.out.println();
			}
		}
		System.out.println("\r\n");
		InsertSort.insertSortDesc(data);
		for (int i : data) {
			System.out.print(i + "\t\t");
			if (j++ % 4 == 0) {
				System.out.println();
			}
		}
	}
}