package com.tkd.data.structure.tree;

import com.tkd.data.structure.common.TreeNode;

public class BST {

	public static TreeNode addValue(TreeNode root, int val) {
		if (root == null) {
			root = new TreeNode(val);
			return root;
		}

		TreeNode curr = root;
		TreeNode parent = null;
		while (curr != null) {
			parent = curr;
			if (val < curr.val) {
				curr = curr.left;
			} else if (val > curr.val) {
				curr = curr.right;
			}
		}
		if (parent.val > val) {
			parent.left = new TreeNode(val);
		} else {
			parent.right = new TreeNode(val);
		}
		return root;
	}

}
