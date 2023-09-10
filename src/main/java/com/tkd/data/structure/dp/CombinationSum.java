package com.tkd.data.structure.dp;

import java.util.HashMap;
import java.util.Map;

public class CombinationSum {

	public static void main(String[] args) {
		System.out.println(combinationSum4(new int[] { 1, 2, 3 }, 4));
		System.out.println(combinationSum4(new int[] { 1, 2, 3 }, 10));
		System.out.println(combinationSum4(new int[] { 9 }, 3));
	}

	public static int combinationSum4(int[] nums, int target) {

		return recursiveSum(nums, target, new HashMap<>());
	}

	public static int recursiveSum(int[] nums, int target, Map<Integer, Integer> dp) {
		if (target < 0) {
			return 0;
		}
		if (target == 0) {
			return 1;
		}
		Integer existing = dp.get(target);
		if (existing != null) {
			return existing;
		}
		int total = 0;
		for (int num : nums) {
			if (num <= target) {
				int target2 = target - num;
				int temp = recursiveSum(nums, target2, dp);
				total += temp;
			}
		}
		dp.put(target, total);
		return total;

	}

}
