package com.tkd.data.structure.graph;

public class IslandCountInGraph {

	private static int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) {
		char[][] graph1 = new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
				{ '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' } };
		System.out.println("Expected=1, Current=" + numIslands(graph1));
		char[][] graph2 = new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' },
				{ '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } };
		System.out.println("Expected=3, Current=" + numIslands(graph2));

		char[][] graph3 = new char[][] { { '1', '1', '0', '1', '1' } };
		System.out.println("Expected=2, Current=" + numIslands(graph3));
	}

	public static int numIslands(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] visited = new int[m][n];

		int islands = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1' && visited[i][j] == 0) {
					dfs(grid, visited, i, j);
					islands++;
				}
			}
		}
		return islands;
	}

	private static void dfs(char[][] grid, int[][] visited, int i, int j) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
			return;
		}
		if (grid[i][j] == '0') {
			return;
		}
		if (visited[i][j] == 1) {
			return;
		}
		visited[i][j] = 1;
		for (int[] dir : directions) {
			dfs(grid, visited, i + dir[0], j + dir[1]);
		}
	}

}
