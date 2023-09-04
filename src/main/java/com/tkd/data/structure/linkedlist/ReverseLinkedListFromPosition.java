package com.tkd.data.structure.linkedlist;

import com.tkd.data.structure.common.ListNode;

public class ReverseLinkedListFromPosition {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		reverseBetween(l1, 2, 4).print();
	}

	public static ListNode reverseBetween(ListNode head, int left, int right) {
		if (left == right) {
			return head;
		}
		int counter = 1;
		ListNode curr = head;
		ListNode leftprev = null;
		while (counter < left && curr != null) {
			leftprev = curr;
			curr = curr.next;
			counter++;
		}
		ListNode prev = null;
		counter = left;
		ListNode leftStart = null;
		ListNode rightNext = null;
		while (counter <= right && curr != null) {
			if (prev == null) {
				leftStart = curr;
			}
			ListNode temp = curr.next;
			if (counter == right) {
				rightNext = temp;
			}
			curr.next = prev;
			prev = curr;
			curr = temp;
			counter++;
		}
		if (leftprev != null) {
			leftprev.next = prev;
		}
		if (leftStart != null) {
			leftStart.next = rightNext;
		}
		return left == 1 ? prev : head;
	}
}
