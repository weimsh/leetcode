package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringTest {
	// 算法：给定一个字符串s和一个字符串数组words。 words中所有字符串长度相同。
	// 字符串s中的 串联子串 是指一个包含words中所有字符串以任意顺序排列连接起来的子串。
	// 例如，如果words = ["ab","cd","ef"]，那么"abcdef", "abefcd", "cdabef", "cdefab", "efabcd"和"efcdab"都是串联子串。
	// "acdbef"不是串联子串，因为他不是任何words排列的连接。
	// 返回所有串联子串在s中的开始索引。你可以以 任意顺序 返回答案。
	public static List<Integer> findSubstring(String s, String[] words) {// 哈希表 + 滑动窗口
		// 我们用哈希表cnt统计words中每个单词出现的次数，用哈希表cnt1统计当前滑动窗口中每个单词出现的次数。
		// 我们记字符串s的长度为m，字符串数组words中单词的数量为n，每个单词的长度为k。
		// 我们可以枚举滑动窗口的起点i，其中0<i<k。对于每个起点，我们维护一个滑动窗口，左边界为l，右边界为r，滑动窗口中的单词个数为t，
		// 另外用一个哈希表cnt1统计滑动窗口中每个单词出现的次数。

		// 每一次，我们提取字符串s[r:r+k]，如果s[r:r+k]不在哈希表cnt中，说明当前滑动窗口中的单词不合法，我们将左边界l更新为r，同时将哈希表cnt1清空，单词个数t重置为0。
		// 如果s[r:r+k]在哈希表cnt中，说明当前滑动窗口中的单词合法，我们将单词个数t加1，将哈希表cnt1中s[r:r+k]的次数加1。
		// 如果cnt1[s[r:r+k]]大于cnt[s[r:r+k]]，说明当前滑动窗口中s[r:r+k]出现的次数过多，我们需要将左边界l右移，直到cnt1[s[r:r+k]] = cnt[s[r:r+k]]。
		// 如果t = n，说明当前滑动窗口中的单词正好合法，我们将左边界l加入答案数组。
		// 时间复杂度O(m*k)，空间复杂度O(n*k)。其中m和n分别是字符串s和字符串数组words的长度，而k是字符串数组words中单词的长度。
		Map<String, Integer> cnt = new HashMap<>();
		for (String w : words) {
			cnt.merge(w, 1, Integer::sum);
		}
		int m = s.length(), n = words.length;
		int k = words[0].length();
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < k; ++i) {
			Map<String, Integer> cnt1 = new HashMap<>();
			int l = i, r = i;
			int t = 0;
			while (r + k <= m) {
				String w = s.substring(r, r + k);
				r += k;
				if (!cnt.containsKey(w)) {
					cnt1.clear();
					l = r;
					t = 0;
					continue;
				}
				cnt1.merge(w, 1, Integer::sum);
				++t;
				while (cnt1.get(w) > cnt.get(w)) {
					String remove = s.substring(l, l + k);
					l += k;
					cnt1.merge(remove, -1, Integer::sum);
					--t;
				}
				if (t == n) {
					ans.add(l);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		String s = "barfoofoobarthefoobarman";
		String[] words = new String[] { "bar", "foo", "the" };
		List<Integer> indexes = findSubstring(s, words);
		System.out.println(indexes);
	}
}
