package com.tkd.data.structure.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class IntersectionOfTwoSortedArray {
	public static void main(String[] args) {
		int[] arr1 = new int[] { 1, 1, 2, 4, 4, 5 };
		int[] arr2 = new int[] { 1, 1, 2, 3, 3, 4, 4, 7 };
		System.out.println(intersectionOfArrayWithoutRepeatingCharacter(arr1, arr2));
		System.out.println(intersectionOfArrayOptimized(arr1, arr2));
	}

	private static List<Integer> intersectionOfArrayOptimized(int[] arr1, int[] arr2) {
		int m = arr1.length;
		int n = arr2.length;
		int i = 0;
		int j = 0;
		List<Integer> ans = new ArrayList<>();
		while (i < m && j < n) {
			if (arr1[i] < arr2[j]) {
				i++;
			} else if (arr1[i] > arr2[j]) {
				j++;
			} else {
				ans.add(arr1[i]);
				i++;
				j++;
			}
		}
		return ans;
	}

	private static List<Integer> intersectionOfArrayWithoutRepeatingCharacter(int[] arr1, int[] arr2) {
		Set<Integer> set = new TreeSet<>();
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr2.length; j++) {
				if (arr1[i] == arr2[j]) {
					set.add(arr1[i]);
				}
			}
		}
		return set.stream().collect(Collectors.toList());
	}
}
