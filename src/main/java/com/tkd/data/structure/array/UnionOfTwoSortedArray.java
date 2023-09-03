package com.tkd.data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UnionOfTwoSortedArray {
	public static void main(String[] args) {
		int[] arr1 = new int[] { 1, 1, 2, 3, 4, 4, 5 };
		int[] arr2 = new int[] { 1, 2, 3, 3, 4, 7 };
		System.out.println(Arrays.toString(unionArray(arr1, arr2)));
		arr1 = new int[] { 1, 1, 2, 3, 4, 4, 5 };
		arr2 = new int[] { 1, 2, 3, 3, 4, 7 };
		System.out.println(Arrays.toString(unionArrayOptimized(arr1, arr2)));
	}

	private static Integer[] unionArrayOptimized(int[] arr1, int[] arr2) {
		int i = 0;
		int j = 0;
		List<Integer> list = new ArrayList<>();
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				if (!list.isEmpty() && arr1[i] == list.get(list.size() - 1)) {

				} else {
					list.add(arr1[i]);
				}
				i++;
			} else {
				if (!list.isEmpty() && arr2[j] == list.get(list.size() - 1)) {

				} else {
					list.add(arr2[j]);
				}
				j++;
			}
		}
		while (i < arr1.length) {
			if (!list.isEmpty() && arr1[i] == list.get(list.size() - 1)) {

			} else {
				list.add(arr1[i]);
			}
			i++;
		}
		while (j < arr2.length) {
			if (!list.isEmpty() && arr2[j] == list.get(list.size() - 1)) {

			} else {
				list.add(arr2[j]);
			}
			j++;
		}
		return list.toArray(new Integer[0]);

	}

	private static int[] unionArray(int[] arr1, int[] arr2) {
		Set<Integer> set = new TreeSet<>();
		for (int num : arr1) {
			set.add(num);
		}
		for (int num : arr2) {
			set.add(num);
		}
		int[] arr = new int[set.size()];
		int i = 0;
		for (int num : set) {
			arr[i] = num;
			i++;
		}
		return arr;
	}
}
