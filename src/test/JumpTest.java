package test;

public class JumpTest {
	// 算法1：给定一个长度为n的整数数组nums。初始位置为nums[0]。
	// 每个元素nums[i]表示从索引i向前跳转的最大长度。
	// 换句话说，如果你在nums[i]处，你可以跳转到任意nums[i+j]处：
	// 0 <= j <= nums[i] 且 i + j < n
	// 返回到达nums[n-1]的最小跳跃次数。生成的测试用例可以到达nums[n-1]。
	public static int jump(int[] nums) {
		int ans = 0, mx = 0, last = 0;
		for (int i = 0; i < nums.length - 1; ++i) {
			mx = Math.max(mx, i + nums[i]);
			if (last == i) {
				++ans;
				last = mx;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int ans = jump(new int[] { 2, 3, 1, 1, 4 });
		System.out.println(ans);

		boolean res = canJump(new int[] { 2, 3, 1, 1, 4 });
		System.out.println(res);
	}

	// 算法2：给你一个非负整数数组nums，你最初位于数组的第一个下标。
	// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
	// 判断你是否能够到达最后一个下标。如果可以，返回true；否则，返回false。
	public static boolean canJump(int[] nums) {
		int mx = 0;
		for (int i = 0; i < nums.length; ++i) {
			if (mx < i) {
				return false;
			}
			mx = Math.max(mx, i + nums[i]);
		}
		return true;
	}
}
