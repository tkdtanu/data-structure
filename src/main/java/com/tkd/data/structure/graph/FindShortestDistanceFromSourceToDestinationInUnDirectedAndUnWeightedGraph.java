package com.tkd.data.structure.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FindShortestDistanceFromSourceToDestinationInUnDirectedAndUnWeightedGraph {

	public static void main(String[] args) {
		graph1('A', 'B');
		graph1('A', 'E');
		graph1('A', 'F');

		graph1('C', 'F');
		graph1('B', 'D');
		graph1('B', 'Z');
	}

	private static void graph1(char source, char destination) {
		AdjListGraph<Character> graph = buildGraph();

		findShortedPathUsingBFS(graph, source, destination);
	}

	private static void findShortedPathUsingBFS(AdjListGraph<Character> graph, char source, char destination) {
		int resultDistance = -1;
		Map<Character, List<Character>> adjList = graph.adjList;

		boolean[] visited = new boolean[26];

		Queue<Character> q = new LinkedList<>();
		q.add(source);
		visited[source - 'A'] = true;
		int distance = 0;

		boolean found = false;

		while (!q.isEmpty()) {
			int size = q.size();

			while (size > 0) {
				Character temp = q.remove();
				if (temp.equals(destination)) {
					resultDistance = distance;
					found = true;
					break;
				} else {
					List<Character> adjs = adjList.get(temp);
					for (Character adj : adjs) {
						if (!visited[adj - 'A']) {
							q.add(adj);
							visited[adj - 'A'] = true;
						}
					}
				}
				size--;
			}
			if (found) {
				break;
			}
			distance++;
		}

		System.out
				.println("Distance from Source:" + source + " To Destination:" + destination + " Is:" + resultDistance);
	}

	private static AdjListGraph<Character> buildGraph() {
		/*-  
		 *      B------D
		 *     /| 	   |\
		 *    / |      | \
		 *   A  |      |  \
		 *    \ |      |   F
		 *     \|      |
		 *     	C------E
		 * 
		 * 
		 */
		AdjListGraph<Character> graph = new AdjListGraph<>();
		graph.addUnDirectedEdge('A', 'B');
		graph.addUnDirectedEdge('A', 'C');
		graph.addUnDirectedEdge('B', 'D');
		graph.addUnDirectedEdge('B', 'C');
		graph.addUnDirectedEdge('C', 'E');
		graph.addUnDirectedEdge('E', 'D');
		graph.addUnDirectedEdge('D', 'F');
		return graph;
	}

}
