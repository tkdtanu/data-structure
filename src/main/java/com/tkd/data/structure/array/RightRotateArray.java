package com.tkd.data.structure.array;

import java.util.Arrays;

public class RightRotateArray {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(rightRotate(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 3)));
	}

	private static int[] rightRotate(int[] arr, int k) {
		k = k % arr.length; // If K size is more than the size of Array
		reverse(arr, arr.length - k, arr.length - 1);
		reverse(arr, 0, arr.length - k - 1);
		reverse(arr, 0, arr.length - 1);
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
