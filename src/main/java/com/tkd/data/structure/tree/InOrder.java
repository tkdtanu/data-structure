package com.tkd.data.structure.tree;

import java.util.Stack;

import com.tkd.data.structure.common.TreeNode;

public class InOrder {
	public static void inOrder(TreeNode node) {
		System.out.print("InOrder Recursive Way-->");
		printInOrder(node);
		System.out.println();
	}

	private static void printInOrder(TreeNode node) {
		if (node == null) {
			return;
		}
		if (node.left != null) {
			printInOrder(node.left);
		}
		System.out.print(node.val + " ");

		if (node.right != null) {
			printInOrder(node.right);
		}
	}

	public static void inOrderIterative(TreeNode node) {
		System.out.print("InOrder Iterative Way-->");
		printInOrderIterative(node);
		System.out.println();
	}

	private static void printInOrderIterative(TreeNode node) {
		if (node == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curr = node;
		while (curr != null || !stack.isEmpty()) {
			while (curr != null) {
				stack.push(curr);
				curr = curr.left;
			}
			curr = stack.pop();
			System.out.print(curr.val + " ");
			curr = curr.right;
		}
		System.out.println();
	}
}
