package test;

import java.util.ArrayList;
import java.util.List;

public class Bracket {
	// 数字n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
	// 输入：n = 3
	// 输出：["((()))","(()())","(())()","()(())","()()()"]
	private List<String> ans = new ArrayList<>();
	private int n;

	public List<String> generateParenthesis(int n) {
		this.n = n;
		dfs(0, 0, "");
		return ans;
	}

	private void dfs(int l, int r, String t) {
		if (l > n || r > n || l < r) {
			return;
		}
		if (l == n && r == n) {
			ans.add(t);
			return;
		}
		dfs(l + 1, r, t + "(");
		dfs(l, r + 1, t + ")");
	}

	public static void main(String[] args) {
		Bracket b = new Bracket();
		List<String> list = b.generateParenthesis(3);
		for (String item : list) {
			System.out.println(item);
		}
	}
}
