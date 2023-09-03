package com.tkd.data.structure.leetcode;

import java.util.Arrays;

public class MinimumPenaltyOfShop {

	public static void main(String[] args) {
		System.out.println(bestClosingTime("YYNY"));
		System.out.println(bestClosingTime("NNNNN"));
		System.out.println(bestClosingTime("NYNNYYY"));
	}

	public static int bestClosingTime(String customers) {
		char[] ch = customers.toCharArray();

		int minPenalty = Integer.MAX_VALUE;
		int minPenaltyHour = 0;
		int[] penalties0 = getPenaltyFromLToR(ch);
		int[] penaltiesN = getPenaltyFromRToL(ch);
		System.out.println(Arrays.toString(penalties0));
		System.out.println(Arrays.toString(penaltiesN));
		for (int i = 0; i < penalties0.length; i++) {
			int penalty = penalties0[i] + penaltiesN[i];
			if (penalty < minPenalty) {
				minPenalty = penalty;
				minPenaltyHour = i;
			}
		}

		return minPenaltyHour;
	}

	private static int[] getPenaltyFromLToR(char[] ch) {
		int[] penalties = new int[ch.length + 1];
		penalties[0] = 0;
		for (int i = 1; i < ch.length+1; i++) {
			if (ch[i-1] == 'N') {
				penalties[i] = penalties[i - 1] + 1;
			} else {
				penalties[i] = penalties[i - 1];
			}
		}
		return penalties;
	}

	private static int[] getPenaltyFromRToL(char[] ch) {
		int[] penalties = new int[ch.length + 1];
		penalties[ch.length] = 0;
		for (int i = ch.length - 1; i >= 0; i--) {
			if (ch[i] == 'Y') {
				penalties[i] = penalties[i + 1] + 1;
			} else {
				penalties[i] = penalties[i + 1];
			}
		}
		return penalties;
	}

}
