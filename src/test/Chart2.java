package test;

import java.util.Arrays;

public class Chart2 {

	// 算法：给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
	// 输入：height = [4,2,0,3,2,5]
	// 输出：9

	public static int trap(int[] height) {
		int n = height.length;
		int[] left = new int[n];
		int[] right = new int[n];
		left[0] = height[0];
		right[n - 1] = height[n - 1];
		for (int i = 1; i < n; ++i) {
			left[i] = Math.max(left[i - 1], height[i]);
			right[n - i - 1] = Math.max(right[n - i], height[n - i - 1]);
		}
		System.out.println("left:" + Arrays.toString(left));
		System.out.println("right:" + Arrays.toString(right));
		int ans = 0;
		for (int i = 0; i < n; ++i) {
			ans += Math.min(left[i], right[i]) - height[i];
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] height = new int[] { 4, 2, 0, 3, 2, 5 };
		int ans = trap(height);
		System.out.println(ans);
	}
}
