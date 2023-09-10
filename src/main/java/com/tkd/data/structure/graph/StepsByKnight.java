package com.tkd.data.structure.graph;

import java.util.LinkedList;
import java.util.Queue;

public class StepsByKnight {

	public static void main(String[] args) {
		System.out.println(new StepsByKnight().minStepToReachTarget(new int[] { 4, 5 }, new int[] { 6, 6 }, 6));
		System.out.println(new StepsByKnight().minStepToReachTarget(new int[] { 4, 5 }, new int[] { 1, 1 }, 6));
		System.out.println(new StepsByKnight().minStepToReachTarget(new int[] { 4, 5 }, new int[] { 2, 2 }, 6));
		System.out.println(new StepsByKnight().minStepToReachTarget(new int[] { 4, 5 }, new int[] { 2, 5 }, 6));
		System.out.println(new StepsByKnight().minStepToReachTarget(new int[] { 4, 5 }, new int[] { 1, 5 }, 6));
	}

	private int[][] indices = new int[][] { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 },
			{ -1, -2 } };

	public int minStepToReachTarget(int KnightPos[], int TargetPos[], int N) {
		// Below subtraction added because, on Question its given as 1 Based Indexing.
		// To Make it 0 based Indexing, reducing 1 from each co-ordinates
		KnightPos[0] = KnightPos[0] - 1;
		KnightPos[1] = KnightPos[1] - 1;
		TargetPos[0] = TargetPos[0] - 1;
		TargetPos[1] = TargetPos[1] - 1;

		int result = -1;

		Queue<Integer[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		boolean[][] added = new boolean[N][N];
		q.add(new Integer[] { KnightPos[0], KnightPos[1] });
		added[KnightPos[0]][KnightPos[1]] = true;
		int currentPath = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			while (size > 0) {
				Integer[] current = q.remove();
				int i = current[0];
				int j = current[1];
				visited[i][j] = true;
				if (i == TargetPos[0] && j == TargetPos[1]) {
					return currentPath;
				}
				for (int[] ind : indices) {
					int x = i + ind[0];
					int y = j + ind[1];
					if (isValidIndex(x, y, N, N) && !visited[x][y] && !added[x][y]) {
						q.add(new Integer[] { x, y });
						added[x][y] = true;
					}
				}

				size--;
			}
			currentPath++;
		}

		return result;
	}

	private boolean isValidIndex(int i, int j, int m, int n) {
		return i >= 0 && i < m && j >= 0 && j < n;
	}

}
