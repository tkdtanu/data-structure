package com.tkd.data.structure.graph;

public class MaxAreadOfIsland {

	public static void main(String[] args) {
		graph1();
		graph2();
	}

	private static void graph1() {
		int[][] graph = new int[][] { { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } };
		System.out.println("Max Area Of Island is:" + maxAreaOfIsland(graph));
	}

	private static void graph2() {
		int[][] graph = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 } };
		System.out.println("Max Area Of Island is:" + maxAreaOfIsland(graph));
	}

	private static int[][] indices = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	private static int maxAreaOfIsland(int[][] board) {
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		int max = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 1 && !visited[i][j]) {
					int size = dfs(board, i, j, visited, m, n, 0);
					max = Math.max(max, size);
				}
			}

		}
		return max;
	}

	private static boolean isValidIndex(int i, int j, int m, int n) {
		return i >= 0 && i < m && j >= 0 && j < n;
	}

	private static int dfs(int[][] board, int i, int j, boolean[][] visited, int m, int n, int size) {
		visited[i][j] = true;
		size += 1;
		for (int[] ind : indices) {
			int x = i + ind[0];
			int y = j + ind[1];
			if (isValidIndex(x, y, m, n) && !visited[x][y] && board[x][y] == 1) {
				size = dfs(board, x, y, visited, m, n, size);
			}
		}
		return size;
	}

}
