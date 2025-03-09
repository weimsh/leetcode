package test;

public class Chart {
	// 算法：给定一个长度为n的整数数组height。有n条垂线，第i条线的两个端点是(i, 0)和(i, height[i])。
	// 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
	// 返回容器可以储存的最大水量。说明：你不能倾斜容器。
	// 输入：[1,8,6,2,5,4,8,3,7]
	// 输出：49
	public static int maxArea(int[] height) {
		int i = 0, j = height.length - 1;
		int ans = 0;
		while (i < j) {
			int t = Math.min(height[i], height[j]) * (j - i);
			ans = Math.max(ans, t);
			if (height[i] < height[j]) {
				++i;
			} else {
				--j;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] height = new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		int ans = maxArea(height);
		System.out.println(ans);
	}
}
