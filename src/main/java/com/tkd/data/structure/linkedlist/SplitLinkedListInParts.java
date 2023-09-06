package com.tkd.data.structure.linkedlist;

import java.util.Arrays;

import com.tkd.data.structure.common.ListNode;

public class SplitLinkedListInParts {

	public static void main(String[] args) {
		ListNode root = new ListNode(1);
		root.next = new ListNode(2);
		root.next.next = new ListNode(3);
		System.out.println(Arrays.toString(splitListToParts(root, 5)));
	}

	public static ListNode[] splitListToParts(ListNode head, int k) {
		ListNode[] result = new ListNode[k];
		if (head == null) {
			return result;
		}
		ListNode curr = head;
		int size = 0;

		while (curr != null) {
			curr = curr.next;
			size++;
		}
		System.out.println("Total Nodes=" + size);

		int eachSize = size / k;
		int rem = size % k;

		System.out.println("Each group Min Size=" + eachSize + ", Reminder:" + rem);

		int index = 0;
		ListNode newHead = head;
		while (index < k) {
			int currentSize = eachSize;
			if (rem != 0) {
				currentSize++;
				rem--;
			}
			curr = newHead;

			while (curr != null && currentSize > 0) {

				currentSize--;
				if (currentSize == 0) {
					result[index] = newHead;
					newHead = curr.next;
					curr.next = null;
				}
				curr = curr.next;
			}

			index++;

		}

		return result;

	}

}
