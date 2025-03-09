package test;

public class StringTest2 {
	// 算法：给出集合[1,2,3,...,n]，其所有元素共有n!种排列。
	// 按大小顺序列出所有排列情况，并一一标记，当n = 3时，所有排列如下：
	// "123", "132", "213", "231", "312", "321"
	// 给定n和k，返回第k个排列。
	// 输入：n = 3, k = 3
	// 输出："213"
	public static String getPermutation(int n, int k) {
		// 我们知道，集合[1,2,..n]一共有n!种排列，如果我们确定首位，那剩余位能组成的排列数量为(n-1)!。
		// 因此，我们枚举每一位i，如果此时k大于当前位置确定后的排列数量，那么我们可以直接减去这个数量；
		// 否则，说明我们找到了当前位置的数。

		// 对于每一位i，其中0<=i<n，剩余位能组成的排列数量为(n-i-1)!，我们记为fact。过程中已使用的数记录在vis中。
		// 时间复杂度O(n^2)，空间复杂度O(n)。
		StringBuilder ans = new StringBuilder();
		boolean[] vis = new boolean[n + 1];
		for (int i = 0; i < n; ++i) {
			int fact = 1;
			for (int j = 1; j < n - i; ++j) {
				fact *= j;
			}
			for (int j = 1; j <= n; ++j) {
				if (!vis[j]) {
					if (k > fact) {
						k -= fact;
					} else {
						ans.append(j);
						vis[j] = true;
						break;
					}
				}
			}
		}
		return ans.toString();
	}

	public static void main(String[] args) {
		String s = getPermutation(3, 3);
		System.out.println(s);
	}
}
