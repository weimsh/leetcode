package test;

import java.util.Arrays;

public class BinaryArray {
	// 算法：实现无重复数字的有序数组的二分查找：
	// 如：[-1,0,1,2,3]数组中查找2，返回下标3；查找4，返回下标-1。
	// 要求：时间复杂度O(logn)，空间复杂度O(1)
	public static void main(String[] args) {
		int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 0 };
		mergeSort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void main1(String[] args) {
		// int[] array = new int[] { -1, 0, 1, 2, 3 };
		int[] array = new int[] { 0, 1, 2, 3, -1 };
		quickSort(array);
		System.out.println(Arrays.toString(array));

		int index = search(array, 0);
		System.out.println(index);
	}

	public static int search(int[] array, int target) {// 二分查找
		if (array == null || array.length == 0) {
			return -1;
		}
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int min = left + (right - left) / 2;
			if (array[min] == target) {
				return min;
			} else if (array[min] < target) {
				left = min + 1;
			} else {
				right = min - 1;
			}
		}
		return -1;
	}

	public static void quickSort(int[] array) {// 快速排序。时间复杂度O(nlogn)，最坏情况O(n^2)，空间复杂度O(1)
		if (array == null || array.length <= 1) {
			return;
		}
		quickSort(array, 0, array.length - 1);
	}
	private static void quickSort(int[] array, int left, int right) {
		if (left < right) {
			int pivotIndex = partition(array, left, right);
			quickSort(array, left, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, right);
		}
	}
	private static int partition(int[] array, int left, int right) {
		int pivot = array[right];// 选择最后一个元素作为基准元素
		int i = left - 1;
		for (int j = left; j < right; j++) {
			if (array[j] < pivot) {
				i++;
				swap(array, i, j);// 将小于基准的元素交换到左边
			}
		}
		swap(array, i + 1, right);// 将基准元素移动至正确位置
		return i + 1;
	}
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void mergeSort(int[] array) {// 归并排序。时间复杂度O(nlogn)，空间复杂度O(n)
		if (array == null || array.length <= 1) {
			return;
		}
		int[] temp = new int[array.length];
		mergeSort(array, 0, array.length - 1, temp);
	}
	private static void mergeSort(int[] array, int left, int right, int[] temp) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSort(array, left, mid, temp);
			mergeSort(array, mid + 1, right, temp);
			merge(array, left, mid, right, temp);
		}
	}
	private static void merge(int[] array, int left, int mid, int right, int[] temp) {
		int i = left;
		int j = mid + 1;
		int k = 0;
		System.out.println("Left:" + Arrays.toString(Arrays.copyOfRange(array, i, j)));
		System.out.println("Right:" + Arrays.toString(Arrays.copyOfRange(array, j, right + 1)));

		while (i <= mid && j <= right) {
			if (array[i] <= array[j]) {
				temp[k++] = array[i++];
			} else {
				System.out.println("逆序对个数:" + (mid - i + 1));
				temp[k++] = array[j++];
			}
		}
		while (i <= mid) {
			temp[k++] = array[i++];
		}
		while (j <= right) {
			temp[k++] = array[j++];
		}
		// 将临时数组中的元素复制到原数组：
		copy(array, left, right, temp);
	}
	private static void copy(int[] array, int left, int right, int[] temp) {
		int k = 0;
		while (left <= right) {
			array[left++] = temp[k++];
		}
	}
}
