package com.tkd.data.structure.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FindShortestDistanceFromSourceToDestinationInDirectedAndUnWeightedGraph {

	public static void main(String[] args) {
		graph1('A', 'B');
		graph1('A', 'E');
		graph1('A', 'F');

		graph1('C', 'F');
		graph1('B', 'D');
		graph1('B', 'C');
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
					if (adjs != null) {
						for (Character adj : adjs) {
							if (!visited[adj - 'A']) {
								q.add(adj);
								visited[adj - 'A'] = true;
							}
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
		 *      B----->D
		 *     ^î 	   |\
		 *    / |      | \
		 *   A  |      |  >
		 *    ^ |      |   F
		 *     \|      v
		 *     	C----->E
		 * 
		 * 
		 */
		AdjListGraph<Character> graph = new AdjListGraph<>();
		graph.addDirectedEdge('A', 'B');
		graph.addDirectedEdge('C', 'A');
		graph.addDirectedEdge('C', 'B');
		graph.addDirectedEdge('C', 'E');
		graph.addDirectedEdge('B', 'D');
		graph.addDirectedEdge('D', 'E');
		graph.addDirectedEdge('D', 'F');
		return graph;
	}

}
