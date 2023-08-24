package com.tkd.data.structure.tree;

import com.tkd.data.structure.common.TreeNode;

public class Runner {
	public static void main(String[] args) {
		 bstOperation();
	}

	private static void bstOperation() {
		TreeNode root = BST.addValue(null, 5);
		root = BST.addValue(root, 3);
		root = BST.addValue(root, 2);
		root = BST.addValue(root, 4);
		root = BST.addValue(root, 10);
		root = BST.addValue(root, 7);
		root = BST.addValue(root, 6);
		root = BST.addValue(root, 8);
		root = BST.addValue(root, 15);
		root = BST.addValue(root, 13);
		root = BST.addValue(root, 17);

		InOrder.inOrder(root);
		InOrder.inOrderIterative(root);

		PreOrder.preOder(root);
		PreOrder.preOrderIterative(root);

		PostOrder.postOrder(root);

		LevelOrder.levelOrder(root);
		LevelOrder.zigzagOrder(root);
	}
}
