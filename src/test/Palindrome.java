package test;

public class Palindrome {
	// 算法：求给定字符串中最大的回文子串
	// 如：babad -> bab 或者 aba
	// 要求：时间复杂度O(n^2)，空间复杂度O(1)
	public static String longestPalindrome(String s) {
		if (s == null || s.length() < 1)
			return "";

		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			// 以单个字符为中心扩展
			int len1 = expandAroundCenter(s, i, i);
			// 以两个字符为中心扩展
			int len2 = expandAroundCenter(s, i, i + 1);
			// 取较长的回文子串长度
			int len = Math.max(len1, len2);

			// 如果找到更长的回文子串，更新起始和结束位置
			// 以len为当前中间点，计算start和end
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		// 返回最长的回文子串
		return s.substring(start, end + 1);
	}

	private static int expandAroundCenter(String s, int left, int right) {
		// 向左右扩展，直到字符不匹配或超出字符串边界
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		// 返回回文子串的长度
		return right - left - 1;
	}

	public static void main(String[] args) {
		String s = "babad";
		System.out.println("Longest Palindromic Substring: " + longestPalindrome(s));
	}
}
