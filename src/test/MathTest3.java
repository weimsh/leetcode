package test;

public class MathTest3 {

	// 算法：实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）。

	public static double myPow(double x, int n) {
		return n >= 0 ? qpow(x, n) : 1 / qpow(x, -(long) n);
	}

	private static double qpow(double a, long n) {
		double ans = 1;
		for (; n > 0; n >>= 1) {
			if ((n & 1) == 1) {// 奇数
				ans = ans * a;
			}
			a = a * a;
		}
		return ans;
	}

	public static void main(String[] args) {
		double d1 = myPow(2, 3);
		System.out.println(d1);
		double d2 = myPow(4, -2);
		System.out.println(d2);
	}
}
