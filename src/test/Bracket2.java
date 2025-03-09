package test;

import java.util.Stack;

public class Bracket2 {
	// 算法：给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
	// 输入：s = ")()())"
	// 输出：4
	// 解释：最长有效括号子串是"()()"
	public static int longestValidParentheses(String s) {
		int ans = 0;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.size() == 0) {
					stack.push(i);
				} else {
					int j = stack.get(stack.size() - 1);
					ans = Math.max(ans, i - j);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(longestValidParentheses(")()())"));
	}
}
