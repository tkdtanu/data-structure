package com.tkd.data.structure.dp;

public class FebonacciNumber {
	public static void main(String[] args) {
		int n = 50;
		System.out.println("Before");
		System.out.println(getFebonacciRecursion(n));
		System.out.println("After");
		System.out.println("Before");
		System.out.println(getFebonacciWithMemoization(n, new int[n + 1]));
		System.out.println("After");
		System.out.println("Before");
		System.out.println(getFibonacciWthConstantSpace(n));
		System.out.println("After");
	}

	private static int getFibonacciWthConstantSpace(int n) {

		int prev2 = 0;
		int prev = 1;

		if (n == 0)
			return prev2;
		if (n == 1)
			return prev;

		for (int i = 2; i <= n; i++) {
			int curr = prev2 + prev;
			prev2 = prev;
			prev = curr;
		}
		return prev;

	}

	private static int getFebonacciWithMemoization(int n, int[] dp) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}
		if (dp[n] != 0) {
			return dp[n];
		}

		int result = getFebonacciWithMemoization(n - 1, dp) + getFebonacciWithMemoization(n - 2, dp);
		dp[n] = result;
		return result;
	}

	private static int getFebonacciRecursion(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}

		return getFebonacciRecursion(n - 1) + getFebonacciRecursion(n - 2);
	}
}
