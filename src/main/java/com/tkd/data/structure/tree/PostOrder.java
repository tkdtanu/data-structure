package com.tkd.data.structure.tree;

import com.tkd.data.structure.common.TreeNode;

public class PostOrder {
	public static void postOrder(TreeNode node) {
		System.out.print("PostOrder Recursive Way-->");
		printPostOrder(node);
		System.out.println();
	}

	private static void printPostOrder(TreeNode node) {
		if (node == null) {
			return;
		}
		if (node.left != null) {
			printPostOrder(node.left);
		}

		if (node.right != null) {
			printPostOrder(node.right);
		}
		System.out.print(node.val + " ");
	}
}
