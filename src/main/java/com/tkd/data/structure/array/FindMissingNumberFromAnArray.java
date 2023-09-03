package com.tkd.data.structure.array;

public class FindMissingNumberFromAnArray {

	public static void main(String[] args) {
		System.out.println(findMissingNumber(new int[] { 1, 2, 3, 4 }, 5));
		System.out.println(findMissingNumberBitManipulation(new int[] { 1, 2, 3, 4 }, 5));
	}

	private static int findMissingNumber(int[] arr, int n) {
		int sum = (n * (n + 1)) / 2;

		int currentSum = 0;
		for (int num : arr) {
			currentSum += num;
		}

		return sum - currentSum;
	}

	private static int findMissingNumberBitManipulation(int[] arr, int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum = sum ^ i;
		}
		for (int i = 0; i < arr.length; i++) {
			sum = sum ^ arr[i];
		}

		return sum;
	}

}
