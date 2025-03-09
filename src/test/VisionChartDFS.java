package test;

import java.util.HashMap;
import java.util.Map;

public class VisionChartDFS {
	// 小强看到的视力表是一张𝑁×𝑁的表格，但是由于小强视力太差，他无法看清表格中的符号。
	// 不过热爱数学的他给自己出了这样一个问题：假设现在有a个向上的符号，b个向下的符号，c个向左的符号，d个向右的符号，把这些符号填到视力表中，总共有多少种可能的情况呢？
	// 简单来说，我们有一个𝑁×𝑁的表格，共有𝑁²个格子。每个格子需要填入一个符号，符号有四种类型：向上、向下、向左、向右，分别有a、b、c、d个。
	// 我们的任务是计算将这些符号填入表格中的所有可能情况的总数。
	// 这个问题涉及到组合数学中的排列组合。具体来说，我们需要将a个向上、b个向下、c个向左、d个向右的符号分配到𝑁²个格子中，每个格子只能放一个符号。
	// 首先，我们需要确认a、b、c、d的和是否等于𝑁²，即：
	// a + b + c + d = 𝑁²
	// 如果这个等式不成立，那么问题无解。因此，我们假设a + b + c + d = 𝑁²。
	private static int a, b, c, d;// 定义符号的数量
	private static int N; // 表格大小 N x N
	private static int totalCells; // 总格子数
	// private static long result = 0; // 结果总数
	private static Map<String, Long> memo;// 记忆化缓存

	public static void main(String[] args) {
		// 输入符号数量和表格大小
		N = 3; // 假设表格大小为 3x3
		a = 2; // 2个向上
		b = 3; // 3个向下
		c = 2; // 2个向左
		d = 2; // 2个向右

		totalCells = N * N; // 总格子数
		if (a + b + c + d != totalCells) {
			System.out.println("符号总数与格子数不匹配！");
			return;
		}

		// 初始化记忆化缓存
		memo = new HashMap<>();

		// 开始DFS搜索
		long result = dfs(0, 0, 0, 0, 0);

		// 输出结果
		System.out.println("总共有 " + result + " 种排列方式。");
	}

	private static long dfs(int index, int countA, int countB, int countC, int countD) {
		// 如果所有格子都填满，且符号数量匹配，则找到一种有效排列
		if (index == totalCells) {
			if (countA == a && countB == b && countC == c && countD == d) {
				return 1;
			}
		}
		// 生成当前状态的唯一键
		String key = index + "," + countA + "," + countA + "," + countC + "," + countD;

		// 如果当前状态已经计算过，则直接返回缓存结果
		if (memo.containsKey(key)) {
			return memo.get(key);
		}

		long count = 0;
		// 尝试填入向上符号
		if (countA < a) {
			count += dfs(index + 1, countA + 1, countB, countC, countD);
		}

		// 尝试填入向下符号
		if (countB < b) {
			count += dfs(index + 1, countA, countB + 1, countC, countD);
		}

		// 尝试填入向左符号
		if (countC < c) {
			count += dfs(index + 1, countA, countB, countC + 1, countD);
		}

		// 尝试填入向右符号
		if (countD < d) {
			count += dfs(index + 1, countA, countB, countC, countD + 1);
		}

		// 将当前状态的结果存入缓存
		memo.put(key, count);
		return count;
	}
}