package test;

import java.util.Arrays;

public class ArrayRotate {
	// 算法： 实现旋转N个整数的数组，将每个整数循环向右移M(M>=0)个位置
	// 如：6,2,{1,2,3,4,5,6} -> {5,6,1,2,3,4}
	// 要求：时间复杂度O(n)，空间复杂度O(1)
	public static void main(String[] args) {
		int[] array = new int[] { 1, 2, 3, 4, 5, 6 };
		int n = array.length;
		int m = 1;
		rotate2(array, n, m);
		System.out.println(Arrays.toString(array));
	}

	public static void rotate(int[] array, int n, int m) {// 时间复杂度O(n)，空间复杂度O(n)
		m = m % n;// 处理m大于n的情况
		int[] temp = new int[n];
		for (int i = 0; i < n; i++) {
			int j = i + m >= n ? i + m - n : i + m;
			temp[j] = array[i];
		}
		System.arraycopy(temp, 0, array, 0, n);
	}

	public static void rotate2(int[] array, int n, int m) {// 时间复杂度O(n)，空间复杂度O(1)
		m = m % n;// 处理m大于n的情况
		// 1) 反转整个数组
		reverse(array, 0, n - 1);
		// 2) 反转前m个元素
		reverse(array, 0, m - 1);
		// 3) 反转剩下的n-m个元素
		reverse(array, m, n - 1);
	}

	private static void reverse(int[] array, int from, int to) {
		while (from < to) {
			int temp = array[from];
			array[from] = array[to];
			array[to] = temp;
			from++;
			to--;
		}
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
