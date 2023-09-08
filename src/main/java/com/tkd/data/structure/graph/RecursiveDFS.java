package com.tkd.data.structure.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class RecursiveDFS {

	public static void main(String[] args) {
		graph1();
		graph2();
	}

	private static void graph1() {
		AdjListGraph<Integer> graph = buildGraph1();
		System.out.println("********GRAPH-1 Related DFS*********");
		System.out.println("************************************");
		Map<Integer, List<Integer>> adjList = graph.getAdjList();
		printDFS(0, adjList);
		printDFS(1, adjList);
		printDFS(2, adjList);
		printDFS(3, adjList);
		System.out.println("************************************");
	}

	private static void graph2() {
		AdjListGraph<Integer> graph = buildGraph2();
		System.out.println("********GRAPH-2 Related DFS*********");
		System.out.println("************************************");
		Map<Integer, List<Integer>> adjList = graph.getAdjList();
		printDFS(0, adjList);
		printDFS(4, adjList);
		printDFS(8, adjList);
		printDFS(2, adjList);
		System.out.println("************************************");
	}

	private static AdjListGraph<Integer> buildGraph2() {
		/*-
		 * 
		 *   0------1				7
		 *   	   / \			   / \
		 *   	  /	  \           /   \
		 *       3-----2----4----5-----8
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addUnDirectedEdge(0, 1);
		graph.addUnDirectedEdge(1, 2);
		graph.addUnDirectedEdge(1, 3);
		graph.addUnDirectedEdge(2, 3);
		graph.addUnDirectedEdge(2, 4);
		graph.addUnDirectedEdge(4, 5);
		graph.addUnDirectedEdge(5, 7);
		graph.addUnDirectedEdge(5, 8);
		graph.addUnDirectedEdge(7, 8);
		return graph;
	}

	private static AdjListGraph<Integer> buildGraph1() {
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		/*-
		 *       1
		 *      /|
		 *     / | 
		 *    0  |  
		 *     \ | 
		 *      \|
		 *       2-----3
		 * 
		 */
		graph.addUnDirectedEdge(0, 1);
		graph.addUnDirectedEdge(0, 2);
		graph.addUnDirectedEdge(1, 2);
		graph.addUnDirectedEdge(2, 3);
		return graph;
	}

	public static <T> void printDFS(T source, Map<T, List<T>> adjList) {
		if (source == null) {
			System.err.println("Source can't be null");
			return;
		}
		if (adjList.isEmpty()) {
			System.err.println("Graph is empty");
			return;
		}
		System.out.println("Printing DFS From Node:" + source);
		HashSet<T> visitedDp = new HashSet<>();// To Clear existing things
		recursiveDFS(null, source, adjList, visitedDp);
		System.out.println("===========================================");
	}

	private static <T> void recursiveDFS(T parent, T current, Map<T, List<T>> adjList, HashSet<T> visitedDp) {
		if (visitedDp.contains(current)) {
			return;
		}
		System.out.println("Parent:" + parent + ", Current:" + current);
		visitedDp.add(current);
		List<T> adjcents = adjList.get(current);
		if (adjcents == null) {
			return;
		}

		for (T adj : adjcents) {
			recursiveDFS(current, adj, adjList, visitedDp);
		}
	}

}
