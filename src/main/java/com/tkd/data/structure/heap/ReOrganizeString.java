package com.tkd.data.structure.heap;

import java.util.PriorityQueue;

import com.tkd.data.structure.common.Pair;

public class ReOrganizeString {
	/*
	 * Given a string s, rearrange the characters of s so that any two adjacent
	 * characters are not the same.
	 * 
	 * Return any possible rearrangement of s or return "" if not possible.
	 * 
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: s = "aab" Output: "aba" Example 2:
	 * 
	 * Input: s = "aaab" Output: ""
	 * 
	 * Input: s = "aaaabbcc" Output: "ababacaca"  --> this is wrong abcabcaa XX
	 */
	
	public static void main(String[] args) {
		System.out.println("ReOrganized String for 'aab' is -->" + reorganizeString("aab"));
		System.out.println("ReOrganized String for 'aaba' is -->" + reorganizeString("aaba"));
		System.out.println("ReOrganized String for 'aaaabbcc' is -->" + reorganizeString("aaaabbcc"));
	}
	
	private static String reorganizeString(String s) {
		int[] map = new int[26];
		for (char c : s.toCharArray()) {
			int index = c - 'a';
			map[index] = map[index] + 1;
		}
		PriorityQueue<Pair<Character, Integer>> queue = new PriorityQueue<>(
				(e1, e2) -> e2.getValue().compareTo(e1.getValue()));
		for (int i = 0; i < map.length; i++) {
			if (map[i] != 0) {
				char cha = (char) ('a' + i);
				queue.add(new Pair<>(cha, map[i]));
			}
		}

		Pair<Character, Integer> prev = null;
		String result = "";
		while (!queue.isEmpty() || prev != null) {
			if (prev != null && queue.isEmpty()) {
				return "Not Possible";
			}
			Pair<Character, Integer> temp = queue.remove();
			result += temp.getKey();
			temp.setValue(temp.getValue() - 1);

			if (prev != null) {
				queue.add(prev);
				prev = null;
			}
			if (temp.getValue() != 0) {
				prev = temp;
			}
		}

		return result;

	}
}
