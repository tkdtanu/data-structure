package com.tkd.data.structure.common;

import lombok.ToString;

@ToString
public class ListNode {
	public int val;
	public ListNode next;

	public ListNode() {
	}

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	public void print() {
		ListNode curr = this;
		while (curr != null) {
			System.out.print(curr.val);
			curr = curr.next;
			if (curr != null) {
				System.out.print("-->");
			}
		}
		System.out.println();
	}
}
