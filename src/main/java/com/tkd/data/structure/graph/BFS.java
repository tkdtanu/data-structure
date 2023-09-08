package com.tkd.data.structure.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS {

	public static void main(String[] args) {
		AdjListGraph<Integer> graph = new AdjListGraph<>();

		graph.addDirectedEdge(0, 1);
		graph.addDirectedEdge(0, 2);
		graph.addDirectedEdge(1, 2);
		graph.addDirectedEdge(1, 0);
		graph.addDirectedEdge(1, 3);
		graph.addDirectedEdge(2, 1);
		graph.addDirectedEdge(2, 0);
		graph.addDirectedEdge(2, 3);
		graph.addDirectedEdge(3, 1);
		graph.addDirectedEdge(3, 2);

		Map<Integer, List<Integer>> adjList = graph.getAdjList();
		printBfs(0, adjList);
		printBfs(1, adjList);
		printBfs(2, adjList);
		printBfs(3, adjList);
	}

	public static <T> void printBfs(T source, Map<T, List<T>> adjList) {
		if (source == null) {
			System.err.println("Source can't be null");
			return;
		}
		if (adjList.isEmpty()) {
			System.err.println("Graph is empty");
			return;
		}
		System.out.println("Printing BFS From Node:" + source);
		HashSet<T> visitedDp = new HashSet<>();// To Clear existing things
		bfs(source, adjList, visitedDp);
		System.out.println("===========================================");
	}

	private static <T> void bfs(T source, Map<T, List<T>> adjList, HashSet<T> visitedDp) {
		Queue<T> queue = new LinkedList<>();
		queue.add(source);
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			System.out.print("Printing Level:" + level + " Node-->");
			while (size != 0) {
				T node = queue.remove();
				if (!visitedDp.contains(node)) {
					System.out.print(" " + node);
					visitedDp.add(node);
					List<T> adjs = adjList.getOrDefault(node, List.of());
					for (T t : adjs) {
						if (!visitedDp.contains(t)) {
							queue.add(t);
						}
					}
				}
				size--;
			}
			System.out.println();
			level++;
		}
	}
}
