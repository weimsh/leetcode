package test;

public class MathTest {
	// 算法：给你两个整数，被除数dividend和除数divisor。将两数相除，要求：不使用乘法、除法和取余运算。
	// 整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345将被截断为8，-2.7335将被截断至-2。
	// 返回被除数dividend除以除数divisor得到的商。
	public static int divide(int a, int b) {
		if (b == 1) {
			return a;
		}
		if (a == Integer.MIN_VALUE && b == -1) {
			return Integer.MAX_VALUE;
		}
		boolean sign = (a > 0 && b > 0) || (a < 0 && b < 0);
		a = a > 0 ? -a : a;
		b = b > 0 ? -b : b;
		int ans = 0;
		while (a <= b) {
			int x = b;
			int cnt = 1;
			while (x >= (Integer.MIN_VALUE >> 1) && a <= (x << 1)) {
				x <<= 1;
				cnt <<= 1;
			}
			ans += cnt;
			a -= x;
		}
		return sign ? ans : -ans;
	}

	public static void main(String[] args) {
		System.out.println(divide(100, 13));
	}
}
