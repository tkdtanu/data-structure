package com.tkd.data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeftRotateArray {
	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println(Arrays.toString(leftRotateArray(arr, 3)));
		System.out.println(Arrays.toString(leftRotateArrayOptimized(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 3)));
	}

	private static int[] leftRotateArrayOptimized(int[] arr, int k) {
		if (k < 0) {
			throw new RuntimeException("K value can't be negative");
		}
		if (k == 0 || arr.length == 1) {
			return arr;
		}
		reverse(arr, 0, k - 1);
		reverse(arr, k, arr.length - 1);
		reverse(arr, 0, arr.length - 1);
		return arr;
	}

	private static int[] leftRotateArray(int[] arr, int k) {
		if (k < 0) {
			throw new RuntimeException("K value can't be negative");
		}
		if (k == 0 || arr.length == 1) {
			return arr;
		}
		k = k % arr.length;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			list.add(arr[i]);
		}
		for (int i = k; i < arr.length; i++) {
			arr[i - k] = arr[i];
		}
		for (int i = 0; i < k; i++) {
			arr[k + i + 1] = list.get(i);
		}
		return arr;
	}

	private static void reverse(int[] arr, int start, int end) {
		while (start < end) {
			int temp = arr[end];
			arr[end] = arr[start];
			arr[start] = temp;
			start++;
			end--;
		}
	}
}
