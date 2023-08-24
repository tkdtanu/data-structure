package com.tkd.data.structure.tree;

import java.util.Stack;

import com.tkd.data.structure.common.TreeNode;

public class PreOrder {
	public static void preOder(TreeNode node) {
		System.out.print("PreOrder Recursive Way-->");
		printPreOder(node);
		System.out.println();
	}

	private static void printPreOder(TreeNode node) {
		if (node == null) {
			return;
		}
		System.out.print(node.val + " ");
		if (node.left != null) {
			printPreOder(node.left);
		}

		if (node.right != null) {
			printPreOder(node.right);
		}
	}

	public static void preOrderIterative(TreeNode node) {
		System.out.print("PreOrder Iterative Way-->");
		printPreOrderIterative(node);
		System.out.println();
	}

	private static void printPreOrderIterative(TreeNode node) {
		if (node == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			TreeNode curr = stack.pop();
			System.out.print(curr.val + " ");
			if (curr.right != null) {
				stack.push(curr.right);
			}
			if (curr.left != null) {
				stack.push(curr.left);
			}
		}
		System.out.println();
	}
}
