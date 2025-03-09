package test;

public class MathTest2 {
	// 算法：字符串相乘
	// 给定两个以字符串形式表示的非负整数num1和num2，返回num1和num2的乘积，它们的乘积也表示为字符串形式。
	// 注意：不能使用任何内置的BigInteger库或直接将输入转换为整数。
	public static String multiply(String num1, String num2) {
		if ("0".equals(num1) || "0".equals(num2)) {
			return "0";
		}
		int m = num1.length(), n = num2.length();
		int[] arr = new int[m + n];
		for (int i = m - 1; i >= 0; --i) {
			int a = num1.charAt(i) - '0';
			for (int j = n - 1; j >= 0; --j) {
				int b = num2.charAt(j) - '0';
				arr[i + j + 1] += a * b;
			}
		}
		for (int i = arr.length - 1; i > 0; --i) {
			arr[i - 1] += arr[i] / 10;
			arr[i] %= 10;
		}
		int i = arr[0] == 0 ? 1 : 0;
		StringBuilder ans = new StringBuilder();
		for (; i < arr.length; ++i) {
			ans.append(arr[i]);
		}
		return ans.toString();
	}

	public static void main(String[] args) {
		String s = multiply("123", "32");
		System.out.println(s);
	}
}
