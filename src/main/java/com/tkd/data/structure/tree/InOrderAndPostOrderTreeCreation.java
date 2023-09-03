package com.tkd.data.structure.tree;

import java.util.HashMap;
import java.util.Map;

import com.tkd.data.structure.common.TreeNode;

public class InOrderAndPostOrderTreeCreation {

	Integer postIndex = 0;

	public TreeNode buildTree(int[] in, int[] post) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		postIndex = post.length-1;
		return build(in, post, 0, in.length - 1, map);
	}

	public TreeNode build(int[] in, int[] post, int inL, int inR, Map<Integer, Integer> inMap) {
		if (postIndex < 0 || inL > inR) {
			return null;
		}
		TreeNode node = new TreeNode(post[postIndex--]);
		int index = inMap.get(node.val);
		node.right = build(in, post, index + 1, inR, inMap);
		node.left = build(in, post, inL, index - 1, inMap);
		return node;
	}
}
