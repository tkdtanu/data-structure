package com.tkd.data.structure.graph;

/*
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
	A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * 
 * 
 *
 */

public class SurroundedRegions {

	public static void main(String[] args) {
		System.out.println("***********Graph Start***********");
		graph1();
		System.out.println("***********Graph Start***********");
		graph2();
		System.out.println("***********Graph Start***********");
		graph3();
		System.out.println("***********Graph Start***********");
		graph4();
	}

	private static void graph4() {
		/*-
		 * Input = 
		 * {
		 * {'X','X','X','X'},
		 * {'X','O','O','X'},
		 * {'X','X','O','X'},
		 * {'X','O','X','X'}
		 * }
		 * 
		 * OutPut = 
		 * {
		 * {'X','X','X','X'},
		 * {'X','X','X','X'},
		 * {'X','X','X','X'},
		 * {'X','O','X','X'}
		 * }
		 */
		char[][] graph = new char[][] { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' },
				{ 'X', 'O', 'X', 'X' } };
		solve(graph);
		for (char[] row : graph) {
			for (char ch : row) {
				System.out.print(ch + ",");
			}
			System.out.println();
		}
	}

	private static void graph3() {
		char[][] graph = new char[][] { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'O', 'O', 'X' },
				{ 'X', 'O', 'X', 'X' } };
		solve(graph);
		for (char[] row : graph) {
			for (char ch : row) {
				System.out.print(ch + ",");
			}
			System.out.println();
		}

	}

	private static void graph2() {
		char[][] graph = new char[][] { { 'X', 'X', 'X', 'X' }, { 'O', 'O', 'X', 'O' }, { 'X', 'X', 'O', 'X' },
				{ 'X', 'X', 'O', 'X' } };
		solve(graph);
		for (char[] row : graph) {
			for (char ch : row) {
				System.out.print(ch + ",");
			}
			System.out.println();
		}
	}

	private static void graph1() {
		char[][] graph = new char[][] { { 'O', 'X', 'X', 'O', 'X' }, { 'X', 'O', 'O', 'X', 'O' },
				{ 'X', 'O', 'X', 'O', 'X' }, { 'O', 'X', 'O', 'O', 'O' }, { 'X', 'X', 'O', 'X', 'O' } };
		solve(graph);
		for (char[] row : graph) {
			for (char ch : row) {
				System.out.print(ch + ",");
			}
			System.out.println();
		}
	}

	private static int[][] indices = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void solve(char[][] board) {

		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O' && !visited[i][j] && (j == 0 || j == n - 1 || i == 0 || i == m - 1)) {
					dfs(board, i, j, visited, m, n);
				}
			}

		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}

	}

	private static boolean isValidIndex(int i, int j, int m, int n) {
		return i >= 0 && i < m && j >= 0 && j < n;
	}

	private static void dfs(char[][] board, int i, int j, boolean[][] visited, int m, int n) {
		visited[i][j] = true;
		for (int[] ind : indices) {
			int x = i + ind[0];
			int y = j + ind[1];
			if (isValidIndex(x, y, m, n) && !visited[x][y] && board[i][j] == 'O') {
				dfs(board, x, y, visited, m, n);
			}
		}
	}

}
