package com.tkd.data.structure.stack;

import java.util.Deque;
import java.util.LinkedList;

public class StackUsingQueue {

}

class MyStack {

	Deque<Integer> dq = new LinkedList<>();
	int size =0;

	public MyStack() {

	}

	public void push(int x) {
		dq.addFirst(x);
	}

	public int pop() {
		return dq.remove();
	}

	public int top() {
		return dq.peekFirst();
	}

	public boolean empty() {
		return dq.size()==0;
	}
}