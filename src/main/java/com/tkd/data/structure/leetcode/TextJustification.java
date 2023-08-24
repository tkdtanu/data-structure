package com.tkd.data.structure.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of strings words and a width maxWidth, format the text such
 * that each line has exactly maxWidth characters and is fully (left and right)
 * justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly maxWidth characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line does not divide evenly between words, the
 * empty slots on the left will be assigned more spaces than the slots on the
 * right.
 * 
 * For the last line of text, it should be left-justified, and no extra space is
 * inserted between words.
 * 
 * Note:
 * 
 * A word is defined as a character sequence consisting of non-space characters
 * only. Each word's length is guaranteed to be greater than 0 and not exceed
 * maxWidth. The input array words contains at least one word.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: words = ["This", "is", "an", "example", "of", "text",
 * "justification."], maxWidth = 16 Output: [ "This is an", "example of text",
 * "justification. " ] Example 2:
 * 
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth =
 * 16 Output: [ "What must be", "acknowledgment ", "shall be " ] Explanation:
 * Note that the last line is "shall be " instead of "shall be", because the
 * last line must be left-justified instead of fully-justified. Note that the
 * second line is also left-justified because it contains only one word. Example
 * 3:
 * 
 * Input: words =
 * ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"],
 * maxWidth = 20 Output: [ "Science is what we", "understand well", "enough to
 * explain to", "a computer. Art is", "everything else we", "do " ]
 *
 */
public class TextJustification {
	public static void main(String[] args) {
		String[] words = new String[] { "Listen", "to", "many,", "speak", "to", "a", "few." };
		int maxWidth = 6;
		String[] answer = new String[] { "Listen", "to    ", "many, ", "speak ", "to   a", "few.  " };
		System.out.println("Current Answer-->" + fullJustify(words, maxWidth));
		System.out.println("Actual Answer-->" + Arrays.toString(answer));

		words = new String[] { "This", "is", "an", "example", "of", "text", "justification." };
		maxWidth = 16;
		answer = new String[] { "This    is    an", "example  of text", "justification.  " };
		System.out.println("Current Answer-->" + fullJustify(words, maxWidth));
		System.out.println("Actual Answer-->" + Arrays.toString(answer));

		words = new String[] { "What", "must", "be", "acknowledgment", "shall", "be" };
		maxWidth = 16;
		answer = new String[] { "What   must   be", "acknowledgment  ", "shall be        " };
		System.out.println("Current Answer-->" + fullJustify(words, maxWidth));
		System.out.println("Actual Answer-->" + Arrays.toString(answer));

		words = new String[] { "Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to",
				"a", "computer.", "Art", "is", "everything", "else", "we", "do" };
		maxWidth = 20;
		answer = new String[] { "Science  is  what we", "understand      well", "enough to explain to",
				"a  computer.  Art is", "everything  else  we", "do                  " };
		System.out.println("Current Answer-->" + fullJustify(words, maxWidth));
		System.out.println("Actual Answer-->" + Arrays.toString(answer));
	}

	public static List<String> fullJustify(String[] words, int maxWidth) {
		List<String> result = new ArrayList<>();
		int currentWordIndex = 0;
		int n = words.length;
		List<List<String>> lineWiseWords = new ArrayList<>();
		int currentLineIndex = 0;

		while (currentWordIndex < n) {

			List<String> lineWords = new ArrayList<>();
			if (lineWiseWords.size() == currentLineIndex) {
				lineWiseWords.add(lineWords);
			} else {
				lineWords = lineWiseWords.get(currentLineIndex);
			}
			int currentLineSize = lineWords.stream().mapToInt(String::length).sum();
			int currentLineSpaceSize = Math.max(1, lineWords.size()) - 1;
			int currentWordLength = words[currentWordIndex].length(); // +1 for space

			if (currentLineSize == 0) {
				lineWords.add(words[currentWordIndex]);
			} else if (currentLineSize + currentLineSpaceSize + currentWordLength >= maxWidth) {
				addToResult(lineWords, result, maxWidth);
				currentLineIndex++;
				continue;
			} else {
				lineWords.add(words[currentWordIndex]);
			}

			currentWordIndex++;
		}
		addToResultLastLine(lineWiseWords.get(lineWiseWords.size() - 1), result, maxWidth);
		return result;
	}

	private static void addToResultLastLine(List<String> lineWords, List<String> result, int maxWidth) {
		int size = lineWords.size();
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < size; i++) {
			b.append(lineWords.get(i));
			if (i != size - 1) {
				b.append(" ");
			}
		}
		int spaceToBeAdded = maxWidth - b.length();
		for (int i = 0; i < spaceToBeAdded; i++) {
			b.append(" ");
		}
		result.add(b.toString());
	}

	private static void addToResult(List<String> lineWords, List<String> result, int maxWidth) {
		int currentLineSize = lineWords.stream().mapToInt(String::length).sum();
		int spaceToBeAdded = maxWidth - currentLineSize;
		int size = lineWords.size();
		int minimumSpaceSize = spaceToBeAdded / Math.max(1, size - 1);
		int rem = spaceToBeAdded % Math.max(1, size - 1);
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < size; i++) {
			b.append(lineWords.get(i));
			for (int j = 0; (size == 1 || i != size - 1) && j < minimumSpaceSize; j++) {
				b.append(" ");
			}
			if (rem != 0) {
				b.append(" ");
				rem--;
			}
		}
		result.add(b.toString());
	}
}
