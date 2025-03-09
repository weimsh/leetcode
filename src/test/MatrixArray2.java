package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixArray2 {
	// 算法：给你一个m行n列的矩阵matrix，请按照顺时针螺旋顺序，返回矩阵中的所有元素。
	// 要求：时间复杂度O(m*n)，空间复杂度O(m*n)
	// 如：[[1,2,3],[4,5,6],[7,8,9]] -> [1,2,3,6,9,8,7,4,5]
	public static void main1(String[] args) {
		int[][] matrix = new int[][] {
			new int[] { 1, 2, 3 },
			new int[] { 4, 5, 6 },
			new int[] { 7, 8, 9 }
		};

		List<Integer> ans = spiralOrder(matrix);
		System.out.println(ans);
	}

	public static List<Integer> spiralOrder(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		int[] dirs = { 0, 1, 0, -1, 0 };// 即4个方向：[0, 1], [1, 0], [0, -1], [-1, 0]
		int i = 0, j = 0, k = 0;
		List<Integer> ans = new ArrayList<>();
		boolean[][] vis = new boolean[m][n];
		for (int h = m * n; h > 0; --h) {
			ans.add(matrix[i][j]);
			vis[i][j] = true;
			int x = i + dirs[k], y = j + dirs[k + 1];
			if (x < 0 || x >= m || y < 0 || y >= n || vis[x][y]) {
				k = (k + 1) % 4;
			}
			i += dirs[k];
			j += dirs[k + 1];
		}
		return ans;
	}

	// 算法2：给你一个正整数n，生成一个包含1到n^2的所有元素，且元素按顺时针顺序螺旋排列的nxn正方形矩阵matrix。
	public static int[][] generateMatrix(int n) {
		int[][] ans = new int[n][n];
		int i = 0, j = 0, k = 0;
		int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		for (int v = 1; v <= n * n; ++v) {
			ans[i][j] = v;
			int x = i + dirs[k][0], y = j + dirs[k][1];
			if (x < 0 || y < 0 || x >= n || y >= n || ans[x][y] > 0) {
				k = (k + 1) % 4;
				x = i + dirs[k][0];
				y = j + dirs[k][1];
			}
			i = x;
			j = y;
		}
		return ans;
	}

	public static void main(String[] args) {
		int[][] ans = generateMatrix(4);
		System.out.println(Arrays.deepToString(ans));
	}
}
