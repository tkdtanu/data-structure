package com.tkd.data.structure.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DetectCycle {

	public static void main(String[] args) {
		AdjListGraph<Integer> graph = new AdjListGraph<>();

		graph.addEdgeUnDirected(0, 1);

		graph.addEdgeUnDirected(1, 2);
		graph.addEdgeUnDirected(1, 3);

		graph.addEdgeUnDirected(2, 3);
		graph.addEdgeUnDirected(2, 4);

		graph.addEdgeUnDirected(4, 5);

		// graph.printBfs(0);
		System.out.println("Is Cycle present on Graph:" + isCyclePresent(0, graph.adjList, new HashSet<>()));

		graph = new AdjListGraph<>();
		graph.addEdge(4, 5);
		graph.addEdge(5, 6);
		graph.addEdge(6, 7);
		// graph.printBfs(4);
		System.out.println("Is Cycle present on Graph:" + isCyclePresent(4, graph.adjList, new HashSet<>()));

		graph.addEdge(7, 4);

		System.out.println("Is Cycle present on Graph:" + isCyclePresent(4, graph.adjList, new HashSet<>()));

	}

	private static boolean isCyclePresent(Integer parent, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
		if (!adjList.containsKey(parent)) {
			return false;
		}
		List<Integer> edges = adjList.get(parent);
		visited.add(parent);
		for (Integer edge : edges) {
			if (!visited.contains(edge)) {
				boolean result = isCyclePresent(edge, adjList, visited);
				if (result) {
					return true;
				}
			} else if (!edge.equals(parent)) {
				return true;
			}

		}
		return false;
	}

}
