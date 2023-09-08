package com.tkd.data.structure.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

	public static void main(String[] args) {
		printPascalTriangle(1);
		printPascalTriangle(2);
		printPascalTriangle(3);
		printPascalTriangle(4);
		printPascalTriangle(5);
	}

	public static void printPascalTriangle(int numRows) {
		List<List<Integer>> result = generate(numRows);
		System.out.println("Pascal Triangle of No of Rows:" + numRows);
		for (List<Integer> row : result) {
			for (Integer num : row) {
				System.out.print(num);
			}
			System.out.println();
		}
		System.out.println("=====================");
	}

	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<>();

		for (int i = 1; i <= numRows; i++) {
			List<Integer> temp = new ArrayList<>();
			List<Integer> pre = i > 1 ? result.get(i - 2) : null;
			for (int j = 1; j <= i; j++) {
				if (j == 1 || j == i) {
					temp.add(1);
				} else {
					temp.add(pre.get(j - 1) + pre.get(j - 2));
				}
			}
			result.add(temp);
		}
		return result;
	}

}
