package test;

import java.util.Arrays;

public class MatrixArray {
	// 算法：顺时针旋转n*n的矩阵（二维数组）
	// 要求：时间复杂度O(n^2)，空间复杂度O(1)
	// 如：[[1,2,3], [4,5,6], [7,8,9]] -> [[7,4,1], [8,5,2], [9,6,3]]
	public static void main(String[] args) {
		int[][] matrix = new int[][] {
			new int[] { 1, 2, 3 },
			new int[] { 4, 5, 6 },
			new int[] { 7, 8, 9 }
		};

		rotate(matrix);
		System.out.println(Arrays.deepToString(matrix));
	}

	private static void rotate(int[][] matrix) {
		int n = matrix.length;
		// 行转列
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		// 反转每一行
		for (int i = 0; i < n; i++) {
			reverse(matrix[i]);
		}
	}

	private static void reverse(int[] array) {
		int from = 0;
		int to = array.length - 1;
		while (from < to) {
			int temp = array[from];
			array[from] = array[to];
			array[to] = temp;
			from++;
			to--;
		}
	}
}
