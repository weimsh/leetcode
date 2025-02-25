package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySearch {
	// 算法1：给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
	// 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
	// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

	// 输入：candidates = [2,3,6,7], target = 7
	// 输出：[[2,2,3],[7]]

	private List<List<Integer>> ans;
	private List<Integer> t;
	private int[] candidates;

	private void search1(int[] candidates, int target) {
		Arrays.sort(candidates);
		this.candidates = candidates;
		this.t = new ArrayList<>();
		this.ans = new ArrayList<>();
		dfs1(0, target);
	}

	private void dfs1(int i, int s) {
		if (s == 0) {
			ans.add(new ArrayList<Integer>(t));
			return;
		}
		if (s < candidates[i]) {
			return;
		}
		for (int j = i; j < candidates.length; ++j) {
			t.add(candidates[j]);
			dfs1(j, s - candidates[j]);
			t.remove(t.size() - 1);
		}
	}

	public static void main(String[] args) {
		ArraySearch as = new ArraySearch();
		int[] candidates = new int[] { 2, 3, 6, 7 };
		int target = 7;

		as.search1(candidates, target);
		System.out.println(as.ans);

		as.search2(candidates, target);
		System.out.println(as.ans);
	}

	// 算法2：给定一个候选人编号的集合 candidates 和一个目标数 target ，
	// 找出 candidates 中所有可以使数字和为 target 的组合。
	// candidates 中的每个数字在每个组合中只能使用 一次 。
	// 注意：解集不能包含重复的组合。
	private void search2(int[] candidates, int target) {
		Arrays.sort(candidates);
		this.candidates = candidates;
		this.t = new ArrayList<>();
		this.ans = new ArrayList<>();
		dfs2(0, target);
	}

	private void dfs2(int i, int s) {
		if (s == 0) {
			ans.add(new ArrayList<Integer>(t));
			return;
		}
		if (i >= candidates.length || s < candidates[i]) {
			return;
		}
		for (int j = i; j < candidates.length; ++j) {
			if (j > i && candidates[j - 1] == candidates[j]) {
				continue;
			}

			t.add(candidates[j]);
			dfs2(j + 1, s - candidates[j]);
			t.remove(t.size() - 1);
		}
	}
}
