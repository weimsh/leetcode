package test;

public class ArraySearch3 {
	// 给你一个整数数组nums，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
	// 子数组是数组中的一个连续部分。
	// 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
	// 输出：6
	// 解释：连续子数组 [4,-1,2,1] 的和最大，为6。
	public static void main(String[] args) {
		int ans = maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 });
		System.out.println(ans);
	}

	public static int maxSubArray(int[] nums) {// 动态规划
		// 我们定义f[i]表示以元素nums[i]为结尾的连续子数组的最大和，初始时f[0]=nums[0]，
		// 那么最终我们要求的答案即为max{f[i]}，其中0<=i<n。

		// 考虑f[i]，其中i>=1，它的状态转移方程为：f[i]=max{f[i-1] + nums[i], nums[i]}
		// 也即：f[i] = max{f[i-1], 0} + nums[i]
		// 由于f[i]只与f[i-1]有关系，因此我们可以只用一个变量f来维护对于当前f[i]的值是多少，然后进行状态转移即可。
		// 答案为max{f}，其中0<=i<n。
		// 时间复杂度O(n)，其中n为数组nums的长度。我们只需要遍历一遍数组即可求得答案。空间复杂度O(1)，我们只需要常数空间存放若干变量。
		int ans = nums[0];
		for (int i = 1, f = nums[0]; i < nums.length; ++i) {
			f = Math.max(f, 0) + nums[i];
			ans = Math.max(ans, f);
		}
		return ans;
	}
}
