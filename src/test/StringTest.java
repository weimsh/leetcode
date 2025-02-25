package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringTest {
	// 算法：给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
	// s 中的 串联子串 是指一个包含 words 中所有字符串以任意顺序排列连接起来的子串。
	// 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"，
	// "cdefab"，"efabcd" 和 "efcdab" 都是串联子串。"acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
	// 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。

	public static List<Integer> findSubstring(String s, String[] words) {
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
