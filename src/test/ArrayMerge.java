package test;

import java.util.Arrays;

public class ArrayMerge {
	// 算法：合并两个有序数组
	// 给你两个按 非递减顺序 排列的整数数组nums1和nums2，另有两个整数m和n，分别表示nums1和nums2中的元素数目。
	// 请你合并nums2到nums1中，使合并后的数组同样按 非递减顺序 排列。
	// 注意：最终，合并后数组不应由函数返回，而是存储在数组nums1中。为了应对这种情况，nums1的初始长度为m+n，
	// 其中前m个元素表示应合并的元素，后n个元素为0，应忽略。nums2的长度为n。

	// 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
	// 输出：[1,2,2,3,5,6]
	// 解释：需要合并[1,2,3]和[2,5,6]，合并结果是[1,2,2,3,5,6]。
	// 要求：时间复杂度O(m+n)，空间复杂度为O(1)。
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		for (int i = m - 1, j = n - 1, k = m + n - 1; j >= 0; k--) {
			nums1[k] = nums1[i] <= nums2[j] ? nums2[j--] : nums1[i--];
		}
	}

	public static void main(String[] args) {
		int[] nums1 = new int[] { 1, 2, 3, 0, 0, 0 };
		int[] nums2 = new int[] { 2, 5, 6 };
		int m = 3;
		int n = 3;
		merge(nums1, m, nums2, n);
		System.out.println(Arrays.toString(nums1));
	}
}
