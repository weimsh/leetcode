package test;

public class RegexTest {
	// 算法：给你一个字符串s和一个字符规律p，请你来实现一个支持'.'和'*'的正则表达式匹配。
	// '.'：匹配任意单个字符
	// '*'：匹配零个或多个前面的那一个元素
	// 所谓匹配，是要 涵盖 整个字符串s的，而不是部分字符串。
	private Boolean[][] f;
	private String s;
	private String p;
	private int m;
	private int n;

	public boolean isMatch(String s, String p) {// 方法一：记忆搜索
		m = s.length();
		n = p.length();
		f = new Boolean[m + 1][n + 1];
		this.s = s;
		this.p = p;
		return dfs(0, 0);
	}

	private boolean dfs(int i, int j) {
		if (j >= n) {
			return i == m;
		}
		if (f[i][j] != null) {
			return f[i][j];
		}
		boolean res = false;
		if (j + 1 < n && p.charAt(j + 1) == '*') {
			res = dfs(i, j + 2) || (i < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && dfs(i + 1, j));
		} else {
			res = i < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && dfs(i + 1, j + 1);
		}
		return f[i][j] = res;
	}

	public boolean isMatch2(String s, String p) {// 方法二：动态规划
		int m = s.length(), n = p.length();
		boolean[][] f = new boolean[m + 1][n + 1];
		f[0][0] = true;
		for (int j = 1; j <= n; j++) {
			if (j > 1 && p.charAt(j - 1) == '*') {
				f[0][j] = f[0][j - 2];
			}
		}

		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (j > 1 && p.charAt(j - 1) == '*') {
					f[i][j] = f[i][j - 2];// *表示0个元素
					if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
						f[i][j] |= f[i - 1][j];// *表示一个或多个前面的元素
					}
				} else if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
					f[i][j] = f[i - 1][j - 1];
				}
			}
		}
		return f[m][n];
	}

	public static void main(String[] args) {
		RegexTest rt = new RegexTest();

		System.out.println("1: " + rt.isMatch2("aaab", "a*b"));// 1
		System.out.println("2: " + rt.isMatch2("aaab", "*b"));// 2
		System.out.println("3: " + rt.isMatch2("aaab", "aab"));// 3
		System.out.println("4: " + rt.isMatch2("aaab", "a*."));// 4
		System.out.println("5: " + rt.isMatch2("aaab", "a.*"));// 5
		System.out.println("6: " + rt.isMatch2("aaab", "aaab"));// 6
		System.out.println("7: " + rt.isMatch2("aaab", "aaab*"));// 7
		System.out.println("8: " + rt.isMatch2("aaab", "aaab."));// 8
		System.out.println("9: " + rt.isMatch2("aaab", "aaabb*"));// 9
		System.out.println("10: " + rt.isMatch2("aaab", "a*b*"));// 10
		System.out.println("11: " + rt.isMatch2("aaab", "a*bb*"));// 11
		System.out.println("12: " + rt.isMatch2("aaab", "acab"));// 12
	}
}
