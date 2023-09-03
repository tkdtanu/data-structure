package com.tkd.data.structure.array;

public class MaxConsecutiveOnes {

	public static void main(String[] args) {
		System.out.println(consecutiveOnes(8, new int[] { 0, 1, 1, 0, 0, 1, 1, 1 }));
	}

	public static int consecutiveOnes(int n, int[] arr) {
		int max = 0;
		int curr = 0;

		for (int i = 0; i < n; i++) {
			if (arr[i] == 1) {
				curr++;
				if (curr > max) {
					max = curr;
				}
			} else {
				curr = 0;
			}
		}
		return max;
	}

}
