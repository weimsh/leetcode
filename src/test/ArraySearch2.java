package test;

import java.util.Arrays;

public class ArraySearch2 {
	// 整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。

	// 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
	// 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
	// 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
	// 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

	// 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
	// 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
	// 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
	// 给你一个整数数组 nums ，找出 nums 的下一个排列。

	// 必须 原地 修改，只允许使用额外常数空间。即空间复杂度O(1)，时间复杂度O(n)

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 2, 3 };
		nextPermutation(nums);// 1,3,2
		nextPermutation(nums);// 2,1,3
		nextPermutation(nums);// 2,3,1
		nextPermutation(nums);// 3,1,2
	}

	public static void nextPermutation(int[] nums) {
		int n = nums.length;
		int i = n - 2;
		for (; i >= 0; --i) {
			if (nums[i] < nums[i + 1]) {
				break;
			}
		}
		if (i >= 0) {
			for (int j = n - 1; j > i; --j) {
				if (nums[j] > nums[i]) {
					swap(nums, i, j);
					break;
				}
			}
		}

		for (int j = i + 1, k = n - 1; j < k; ++j, --k) {
			swap(nums, j, k);
		}
		System.out.println(Arrays.toString(nums));
	}

	private static void swap(int[] nums, int i, int j) {
		int t = nums[j];
		nums[j] = nums[i];
		nums[i] = t;
	}
}
