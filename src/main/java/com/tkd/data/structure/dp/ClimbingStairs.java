package com.tkd.data.structure.dp;

public class ClimbingStairs {

	public static void main(String[] args) {
		int n = 8;
		int dp[] = new int[n];
		// System.out.println(findMinimumStairToReachNthFloor(n));
		System.out.println(findMinimumStairToReachNthFloorRecursion(0, n, dp));
	}

	private static int findMinimumStairToReachNthFloor(int n) {
		// TODO Auto-generated method stub
		return n;
	}

	private static int findMinimumStairToReachNthFloorRecursion(int source, int n, int[] dp) {
		if (n == 0 || source > n ) {
			return 0;
		}
		if (source == n) {
			return 1;
		}
		if (dp[source] != 0) {
			return dp[source];
		}
		int step1 = findMinimumStairToReachNthFloorRecursion(source + 1, n, dp);
		int step2 = findMinimumStairToReachNthFloorRecursion(source + 2, n, dp);
		dp[source] = step1 + step2;
		return step1 + step2;
	}

}
