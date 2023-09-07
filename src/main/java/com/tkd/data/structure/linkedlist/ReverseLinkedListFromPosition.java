package com.tkd.data.structure.linkedlist;

import com.tkd.data.structure.common.ListNode;

public class ReverseLinkedListFromPosition {
	public static void main(String[] args) {
		reverseLL(buildLinkedList(), 2, 4);
		reverseLL(buildLinkedList(), 1, 4);
		reverseLL(buildLinkedList(), 1, 5);
		reverseLL(buildLinkedList(), 4, 5);
		reverseLL(buildLinkedList(), 2, 2);

		reverseLLUsingDummyPointer(buildLinkedList(), 2, 4);
		reverseLLUsingDummyPointer(buildLinkedList(), 1, 4);
		reverseLLUsingDummyPointer(buildLinkedList(), 1, 5);
		reverseLLUsingDummyPointer(buildLinkedList(), 4, 5);
		reverseLLUsingDummyPointer(buildLinkedList(), 2, 2);
	}

	private static void reverseLL(ListNode ll, int left, int right) {
		System.out.println("Original LL:");
		ll.print();
		System.out.println("After Reversing LL from Position " + left + " to " + right);
		reverseBetween(ll, left, right).print();
		System.out.println("==================================");
	}

	private static void reverseLLUsingDummyPointer(ListNode ll, int left, int right) {
		System.out.println("Original LL:");
		ll.print();
		System.out.println("After Reversing LL Using Dummy Pointer from Position " + left + " to " + right);
		reverseBetween(ll, left, right).print();
		System.out.println("==================================");
	}

	private static ListNode buildLinkedList() {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		return l1;
	}

	public static ListNode reverseBetweenUsingDummyNode(ListNode head, int left, int right) {
		if (left == right) {
			return head;
		}
		ListNode dummy = new ListNode();
		dummy.next = head;

		ListNode curr = head;
		ListNode prev = dummy;
		int counter = 1;
		while (curr != null && counter < left) {
			prev = curr;
			curr = curr.next;
			counter++;
		}

		ListNode leftPrevious = prev;
		prev = null;
		while (curr != null && counter <= right) {
			ListNode temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
			counter++;
		}

		leftPrevious.next.next = curr;
		leftPrevious.next = prev;

		return dummy.next;
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
