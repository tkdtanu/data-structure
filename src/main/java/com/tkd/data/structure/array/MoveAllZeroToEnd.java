package com.tkd.data.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveAllZeroToEnd {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(moveZeros(new int[] { 3, 2, 0, 4, 0, 1, 7, 0 })));
		System.out.println(Arrays.toString(moveZeroBetter(new int[] { 3, 2, 0, 4, 0, 1, 7, 0 })));
		
		System.out.println(Arrays.toString(moveZeroOptimized(new int[] { 3, 2, 0, 4, 0, 1, 7, 0 })));
	}
	
	private static int[] moveZeroOptimized(int[] arr) {
		int x=-1;
		int y=-1;
		
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==0) {
				x=i;
				y=i+1;
				break;
			}
		}
		if(x==-1 || x==arr.length-1) {
			return arr;
		}
		while(y<arr.length-1) {
			if(arr[y]!=0) {
				arr[x] = arr[y];
				arr[y] = 0;
				y++;
				x++;
			}else {
				y++;
			}
		}
		
		return arr;
		
	}
	private static int[] moveZeroBetter(int[] arr) {
		int nonZeroIndex = 0;
		for (int num : arr) {
			if (num != 0) {
				arr[nonZeroIndex] = num;
				nonZeroIndex++;
			}
		}
		for (int i = nonZeroIndex; i < arr.length; i++) {
			arr[i] = 0;
		}
		return arr;
	}

	private static int[] moveZeros(int[] arr) {
		List<Integer> list = new ArrayList<>();
		for (int num : arr) {
			if (num != 0) {
				list.add(num);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}

		for (int i = list.size(); i < arr.length; i++) {
			arr[i] = 0;
		}
		return arr;
	}
}
