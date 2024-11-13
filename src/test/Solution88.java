package test;

import java.util.Arrays;

public class Solution88 {
	// 88. 合并两个有序数组
	public static void main(String[] args) {
		int[] nums1 = new int[] { -1,0,0,3,3,3,0,0,0 };
		int m = 6;
		int[] nums2 = new int[] { 1,2,2 };
		int n = 3;
		merge(nums1, m, nums2, n);
		System.out.println(Arrays.toString(nums1));
	}

	private static void merge(int[] nums1, int m, int[] nums2, int n) {
		int[] x = new int[nums1.length];
		for (int i = 0, k = 0, l = 0; i < nums1.length; i++) {
			for (int j = l; j < nums2.length; j++) {
				if (nums1[i] > nums2[j]) {
					l++;
					x[k++] = nums2[j];
				} else if (nums1[i] == 0 && i >= m) {
					l++;
					x[k++] = nums2[j];
				}

			}
			if (k < nums1.length) {
				x[k++] = nums1[i];
			}
		}
		System.arraycopy(x, 0, nums1, 0, x.length);
	}
}
                                                                                                                                                                                                                     
