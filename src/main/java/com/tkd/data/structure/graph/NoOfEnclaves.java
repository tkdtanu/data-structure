package com.tkd.data.structure.graph;

public class NoOfEnclaves {

	public static void main(String[] args) {
		NoOfEnclaves obj = new NoOfEnclaves();
		example1(obj);
		example2(obj);
	}

	private static void example2(NoOfEnclaves obj) {
		int[][] grid = new int[][] { { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } };
		run(obj, grid);
	}

	private static void example1(NoOfEnclaves obj) {
		int[][] grid = new int[][] { { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } };
		run(obj, grid);
	}

	private static void run(NoOfEnclaves obj, int[][] grid) {

		System.out.println("No Of Land Cells which can't be Walk of Boundary:" + obj.numEnclaves(grid));
	}

	private int[][] indices = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public int numEnclaves(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		boolean[][] visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
					if (!visited[i][j] && grid[i][j] == 1) {
						dfs(grid, i, j, m, n, visited);
					}
				}
			}
		}
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && grid[i][j] == 1) {
					count++;
				}
			}
		}

		return count;
	}

	private boolean isValid(int i, int j, int m, int n) {
		return i >= 0 && i < m && j >= 0 && j < n;
	}

	private void dfs(int[][] grid, int i, int j, int m, int n, boolean[][] visited) {
		visited[i][j] = true;

		for (int[] ind : indices) {
			int x = i + ind[0];
			int y = j + ind[1];
			if (isValid(x, y, m, n) && !visited[x][y] && grid[x][y] == 1) {
				dfs(grid, x, y, m, n, visited);
			}
		}
	}

}
