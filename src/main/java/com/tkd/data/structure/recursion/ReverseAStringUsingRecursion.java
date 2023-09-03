package com.tkd.data.structure.recursion;

public class ReverseAStringUsingRecursion {
	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder();
		reverse("abcde", 0, builder);
		System.out.println(builder.toString());
	}

	private static void reverse(String s, int i, StringBuilder builder) {
		if (i != s.length() - 1) {
			reverse(s, i + 1, builder);
			builder.append(s.charAt(i));
		} else {
			builder.append(s.charAt(i));
		}
	}
}
