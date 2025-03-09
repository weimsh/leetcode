package test;

public class RegexTest2 {
	// 算法：给你一个输入字符串s和一个字符模式p，请你实现一个支持'?'和'*'匹配规则的通配符匹配：
	// '?'：可以匹配任何单个字符。
	// '*'：可以匹配任意字符序列（包括空字符序列）。
	// 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
	// 输入：s = "aa", p = "a"
	// 输出：false
	// 输入：s = "aa", p = "*"
	// 输出：true
	private Boolean[][] f;
	private char[] s;
	private char[] p;
	private int m;
	private int n;

	public boolean isMatch(String s, String p) {// 方法一：记忆搜索
		this.s = s.toCharArray();
		this.p = p.toCharArray();
		m = s.length();
		n = p.length();
		f = new Boolean[m][n];
		return dfs(0, 0);
	}

	private boolean dfs(int i, int j) {
		if (i >= m) {
			return j >= n || (p[j] == '*' && dfs(i, j + 1));
		}
		if (j >= n) {
			return false;
		}
		if (f[i][j] != null) {
			return f[i][j];
		}
		if (p[j] == '*') {
			f[i][j] = dfs(i + 1, j) || dfs(i + 1, j + 1) || dfs(i, j + 1);
		} else {
			f[i][j] = (p[j] == '?' || s[i] == p[j]) && dfs(i + 1, j + 1);
		}
		return f[i][j];
	}

	public boolean isMatch2(String s, String p) {// 方法二：动态规划
		int m = s.length(), n = p.length();
		boolean[][] f = new boolean[m + 1][n + 1];
		f[0][0] = true;
		for (int j = 1; j <= n; ++j) {
			if (p.charAt(j - 1) == '*') {
				f[0][j] = f[0][j - 1];
			}
		}
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (p.charAt(j - 1) == '*') {
					f[i][j] = f[i - 1][j] || f[i][j - 1] || f[i - 1][j - 1];
				} else {
					f[i][j] = f[i - 1][j - 1] && (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1));
				}
			}
		}
		return f[m][n];
	}

	public static void main(String[] args) {
		RegexTest2 rt = new RegexTest2();

		System.out.println("1: " + rt.isMatch("aaab", "a*b"));// 1
		System.out.println("2: " + rt.isMatch("aaab", "*b"));// 2
		System.out.println("3: " + rt.isMatch("aaab", "aab"));// 3
		System.out.println("4: " + rt.isMatch("aaab", "a*."));// 4
		System.out.println("5: " + rt.isMatch("aaab", "a.*"));// 5
		System.out.println("6: " + rt.isMatch("aaab", "aaab"));// 6
		System.out.println("7: " + rt.isMatch("aaab", "aaab*"));// 7
		System.out.println("8: " + rt.isMatch("aaab", "aaab."));// 8
		System.out.println("9: " + rt.isMatch("aaab", "aaabb*"));// 9
		System.out.println("10: " + rt.isMatch("aaab", "a*b*"));// 10
		System.out.println("11: " + rt.isMatch("aaab", "a*bb*"));// 11
		System.out.println("12: " + rt.isMatch("aaab", "acab"));// 12
	}
}
