package test;

public class RegexTest {

	// 算法：给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
	// '.' 匹配任意单个字符
	// '*' 匹配零个或多个前面的那一个元素
	// 所谓匹配，是要涵盖 整个 字符串 s 的，而不是部分字符串。

	private static boolean isMatch2(String s, String p) {
		int m = s.length(), n = p.length();
		boolean[][] f = new boolean[m + 1][n + 1];
		f[0][0] = true;
		for (int i = 0; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (j > 1 && p.charAt(j - 1) == '*') {
					f[i][j] = f[i][j - 2];// *表示0个元素
					if (i > 0 && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1))) {
						f[i][j] |= f[i - 1][j];// *表示一个或多个前面的元素
					}
					System.out.println("i:" + i + ",j:" + j + "," + f[i][j]);
				} else if (i > 0 && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))) {
					f[i][j] = f[i - 1][j - 1];
					System.out.println("i:" + i + ",j:" + j + "," + f[i][j]);
				}
			}
		}
		return f[m][n];
	}

	private static boolean isMatch(String s, String p) {
		int m = s.length();
		int n = p.length();

		int j = 0;
		for (int i = 0; i < m; i++) {
			char x = s.charAt(i);
			char y = j < n ? p.charAt(j) : 0;
			if (x == y || y == '.') {
				j++;
			} else if (y == '*') {
				if (j == 0) {
					return false;
				} else {
					char prev = p.charAt(j - 1);
					if (prev == '*') {
						return false;
					} else if (x == prev || prev == '.') {
						continue;
					} else if (j + 1 < n) {
						char next = p.charAt(j + 1);
						if (x == next || next == '.') {
							j = j + 2;
						}
					}

				}
			} else {
				return false;
			}
		}
		if (n == j + 1 && p.charAt(j) != '*') {
			return false;
		}
		if (n > j + 1) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("1: " + isMatch("aaab", "a*b"));// 1
		System.out.println("2: " + isMatch("aaab", "*b"));// 2
		System.out.println("3: " + isMatch("aaab", "aab"));// 3
		System.out.println("4: " + isMatch("aaab", "a*."));// 4
		System.out.println("5: " + isMatch("aaab", "a.*"));// 5
		System.out.println("6: " + isMatch("aaab", "aaab"));// 6
		System.out.println("7: " + isMatch("aaab", "aaab*"));// 7
		System.out.println("8: " + isMatch("aaab", "aaab."));// 8
		System.out.println("9: " + isMatch("aaab", "aaabb*"));// 9
		System.out.println("10: " + isMatch("aaab", "a*b*"));// 10
		System.out.println("11: " + isMatch("aaab", "a*bb*"));// 11
		System.out.println("12: " + isMatch("aaab", "acab"));// 12
	}

}
