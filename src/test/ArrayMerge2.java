package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArrayMerge2 {
	// 算法：合并区间
	// 以数组intervals表示若干个区间的集合，其中单个区间为intervals[i] = [starti, endi]。
	// 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

	// 输入：intervals = [[1,3], [2,6], [8,10], [15,18]]
	// 输出：[[1,6], [8,10], [15,18]]
	// 解释：区间[1,3]和[2,6]重叠，将它们合并为[1,6]。
	public static int[][] merge(int[][] intervals) {// 时间复杂度O(nlogn)，空间复杂度O(logn)
		Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
		int st = intervals[0][0], ed = intervals[0][1];
		List<int[]> ans = new ArrayList<>();// 注：不算空间复杂度
		for (int i = 1; i < intervals.length; ++i) {
			int s = intervals[i][0], e = intervals[i][1];
			if (ed < s) {
				ans.add(new int[] { st, ed });
				st = s;
				ed = e;
			} else {
				ed = Math.max(ed, e);
			}
		}
		ans.add(new int[] { st, ed });
		return ans.toArray(new int[ans.size()][]);
	}

	public static void main1(String[] args) {
		int[][] intervals = new int[][] {
			new int[] { 1, 3 },
			new int[] { 2, 6 },
			new int[] { 8, 10 },
			new int[] { 15, 18 }
		};
		int[][] ans = merge(intervals);
		System.out.println(Arrays.deepToString(ans));
	}

	// 算法2：给你一个无重叠的，按照区间起始端点排序的区间列表intervals，
	// 其中intervals[i] = [starti, endi]表示第i个区间的开始和结束，并且intervals按照starti升序排列。
	// 同样给定一个区间newInterval = [start, end]表示另一个区间的开始和结束。
	// 在intervals中插入区间newInterval，使得intervals依然按照starti升序排列，且区间之间不重叠（如果有必要的话，可以合并区间）。
	// 返回插入之后的intervals。
	// 注意：你不需要原地修改intervals。你可以创建一个新数组然后返回它。
	public static void main(String[] args) {
		int[][] intervals = new int[][] {
			new int[] { 1, 2 },
			new int[] { 3, 5 },
			new int[] { 6, 7 },
			new int[] { 8, 10 },
			new int[] { 12, 16 }
		};
		int[] newInterval = new int[] { 4, 8 };
		int[][] ans = insert2(intervals, newInterval);
		System.out.println(Arrays.deepToString(ans));
	}

	public static int[][] insert(int[][] intervals, int[] newInterval) {// 方法一：排序 + 区间合并。时间复杂度O(nlogn)，空间复杂度O(n)
		int[][] newIntervals = new int[intervals.length + 1][newInterval.length];
		for (int i = 0, n = intervals.length; i < n; i++) {
			newIntervals[i] = intervals[i];
		}
		newIntervals[intervals.length] = newInterval;
		return merge(newIntervals);
	}

	public static int[][] insert2(int[][] intervals, int[] newInterval) {// 方法二：一次遍历
		// 我们可以遍历区间列表intervals，记当前区间为interval，对于每个区间有三种情况：
		// 1) 当前区间在新区间的右侧，即newInterval[1] < interval[0]，
		//    此时如果新区间还没有被加入，那么将新区间加入到答案中，然后将当前区间加入到答案中。
		// 2) 当前区间在新区间的左侧，即interval[1] < newInterval[0]，此时将当前区间加入到答案中。
		// 3) 否则，说明当前区间与新区间有交集，我们取当前区间的左端点和新区间的左端点的最小值，
		//    以及当前区间的右端点和新区间的右端点的最大值，作为新区间的左右端点，然后继续遍历区间列表。
		//    遍历结束，如果新区间还没有被加入，那么将新区间加入到答案中。
		// 时间复杂度O(n)，其中n是区间的数量。忽略答案数组的空间消耗，空间复杂度O(1)。
		List<int[]> ans = new ArrayList<>();
		int st = newInterval[0], ed = newInterval[1];
		boolean insert = false;
		for (int[] interval : intervals) {
			int s = interval[0], e = interval[1];
			if (ed < s) {
				if (!insert) {
					ans.add(new int[] { st, ed });
					insert = true;
				}
				ans.add(interval);
			} else if (e < st) {
				ans.add(interval);
			} else {
				st = Math.min(st, s);
				ed = Math.max(ed, e);
			}
		}
		if (!insert) {
			ans.add(new int[] { st, ed });
		}
		return ans.toArray(new int[ans.size()][]);
	}
}
