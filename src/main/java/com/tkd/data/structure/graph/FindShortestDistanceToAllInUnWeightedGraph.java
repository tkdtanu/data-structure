package com.tkd.data.structure.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FindShortestDistanceToAllInUnWeightedGraph {

	public static void main(String[] args) {
		graph1(0);
		graph1(2);
		graph1(3);
		graph1(6);
		graph1(7);
	}

	private static void graph1(int source) {
		AdjListGraph<Integer> graph = buildGraph();

		findShortedPathUsingBFS(graph, source);
	}

	private static AdjListGraph<Integer> buildGraph() {
		/*-  
		 * 
		 *    1-----0     7-----6
		 *    |     |    /|    /|
		 *    |     |   / |   / |
		 *    |     |  /  |  /  |
		 *    |     | /   | /   |
		 *    2     3-----4-----5    9
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addUnDirectedEdge(0, 1);
		graph.addUnDirectedEdge(0, 3);
		graph.addUnDirectedEdge(1, 2);
		graph.addUnDirectedEdge(3, 4);
		graph.addUnDirectedEdge(3, 7);
		graph.addUnDirectedEdge(7, 6);
		graph.addUnDirectedEdge(7, 4);
		graph.addUnDirectedEdge(4, 5);
		graph.addUnDirectedEdge(4, 6);
		graph.addUnDirectedEdge(6, 5);
		graph.addUnDirectedEdge(9, null);
		return graph;
	}

	private static void findShortedPathUsingBFS(AdjListGraph<Integer> graph, int source) {
		boolean[] visited = new boolean[graph.getTotalVertexCount()];

		Map<Integer, Integer> distances = new HashMap<>();
		graph.getAllVertex().forEach(t -> {
			distances.put(t, -1);
		});
		Map<Integer, List<Integer>> adjList = graph.adjList;
		distances.put(source, 0);
		Queue<Integer> q = new LinkedList<>();
		q.add(source);

		int distance = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			while (size > 0) {

				int node = q.remove();
				distances.put(node, distance);
				visited[node] = true;

				List<Integer> adjs = adjList.get(node);
				for (int adj : adjs) {
					if (!visited[adj]) {
						q.add(adj);
						visited[adj] = true;
					}
				}

				size--;
			}
			distance++;
		}

		System.out.println("Shortest Distance from Node:" + source + " To All Nodes are:");
		System.out.println(distances);
		System.out.println("========================================");
	}

}
