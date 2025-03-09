package test;

public class NQueens {
	// 主函数，解决N皇后问题
	public static void solveNQueens(int n) {
		int[][] board = new int[n][n]; // 初始化棋盘，0表示空位，1表示皇后
		placeQueen(board, 0, n);
	}

	// 递归放置皇后
	private static void placeQueen(int[][] board, int row, int n) {
		if (row == n) {
			// 所有皇后都放置完毕，打印结果
			printBoard(board);
			return;
		}

		for (int col = 0; col < n; col++) {
			if (isSafe(board, row, col, n)) {
				board[row][col] = 1; // 放置皇后
				placeQueen(board, row + 1, n); // 递归放置下一行的皇后
				board[row][col] = 0; // 回溯，撤销当前放置的皇后
			}
		}
	}

	// 检查当前位置是否安全
	private static boolean isSafe(int[][] board, int row, int col, int n) {
		// 检查当前列是否有皇后
		for (int i = 0; i < row; i++) {
			if (board[i][col] == 1) {
				return false;
			}
		}

		// 检查左上对角线是否有皇后
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}

		// 检查右上对角线是否有皇后
		for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
			if (board[i][j] == 1) {
				return false;
			}
		}

		return true;
	}

	// 打印棋盘
	private static void printBoard(int[][] board) {
		int n = board.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int n = 8; // 8皇后问题
		solveNQueens(n);
	}
}