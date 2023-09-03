package com.tkd.data.structure.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tarun.das
 * 
 * 
 *         Given an array nums of n integers where nums[i] is in the range [1,
 *         n], return an array of all the integers in the range [1, n] that do
 *         not appear in nums.
 */
public class FindAllNumberDisappearedInArray {

	public static void main(String[] args) {
		System.out.println(findDisappearedNumbers(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 }));
		System.out.println(findDisappearedNumbers(new int[] { 1, 1 }));
	}

	public static List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> list = new ArrayList<>();
		int[] arr = new int[nums.length + 1];
		for (int num : nums) {
			arr[num] = 1;
		}
		for (int i = 1; i < nums.length + 1; i++) {
			if (arr[i] == 0) {
				list.add(i);
			}
		}
		return list;
	}

}
