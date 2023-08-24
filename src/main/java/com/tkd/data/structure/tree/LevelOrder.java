package com.tkd.data.structure.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import com.tkd.data.structure.common.TreeNode;

public class LevelOrder {

	public static void levelOrder(TreeNode node) {
		System.out.println("LevelOrder Traversal-->");
		if (node == null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(node);
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			System.out.print("Level-" + level + "-->");
			while (size != 0) {
				TreeNode temp = queue.remove();
				System.out.print(temp.val + " ");
				if (temp.left != null) {
					queue.add(temp.left);
				}
				if (temp.right != null) {
					queue.add(temp.right);
				}
				size--;
			}
			System.out.println();
			level++;
		}
		System.out.println();
	}

	public static void zigzagOrder(TreeNode node) {
		System.out.println("ZigZag Traversal-->");
		if (node == null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(node);
		int level = 0;
		boolean order = true;
		while (!queue.isEmpty()) {
			int size = queue.size();
			System.out.print("Level-" + level + "-->");
			Queue<TreeNode> tempQueue = new LinkedList<>();
			while (size != 0) {
				TreeNode temp = queue.remove();
				System.out.print(temp.val + " ");
				if (order) {
					if (temp.left != null) {
						tempQueue.add(temp.left);
					}
					if (temp.right != null) {
						tempQueue.add(temp.right);
					}
				} else {
					if (temp.right != null) {
						tempQueue.add(temp.right);
					}
					if (temp.left != null) {
						tempQueue.add(temp.left);
					}
				}
				size--;
			}
			List<TreeNode> list = tempQueue.stream().collect(Collectors.toList());
			Collections.reverse(list);
			queue.addAll(list);
			System.out.println();
			level++;
			order = !order;
		}
		System.out.println();
	}

}
