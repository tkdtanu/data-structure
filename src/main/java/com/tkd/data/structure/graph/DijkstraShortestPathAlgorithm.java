package com.tkd.data.structure.graph;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.tkd.data.structure.graph.WeightedAdjListGraph.Edge;

import lombok.AllArgsConstructor;
import lombok.Data;

public class DijkstraShortestPathAlgorithm {

	public static void main(String[] args) {
		graph1();
		graph2();
		graph3();
		graph4();
	}

	private static void graph1() {
		WeightedAdjListGraph<Integer> graph = buildGraph1();
		int graphNumber = 1;
		runDijkstrAlgorithm(graph, 0, graphNumber);
		runDijkstrAlgorithm(graph, 5, graphNumber);
	}

	private static void graph2() {
		WeightedAdjListGraph<Integer> graph = buildGraph2();
		int graphNumber = 2;
		runDijkstrAlgorithm(graph, 0, graphNumber);
		runDijkstrAlgorithm(graph, 3, graphNumber);
		runDijkstrAlgorithm(graph, 5, graphNumber);
	}

	private static void graph3() {
		WeightedAdjListGraph<Integer> graph = buildGraph3();
		int graphNumber = 3;
		runDijkstrAlgorithm(graph, 1, graphNumber);
		runDijkstrAlgorithm(graph, 4, graphNumber);
	}

	private static void graph4() {
		WeightedAdjListGraph<Integer> graph = buildGraph4();
		int graphNumber = 4;
		runDijkstrAlgorithm(graph, 0, graphNumber);
		runDijkstrAlgorithm(graph, 6, graphNumber);
		runDijkstrAlgorithm(graph, 4, graphNumber);
	}

	private static WeightedAdjListGraph<Integer> buildGraph1() {
		/*-  
		 *                       4
		 *      4------------------------------------5
		 *      | \                                  |
		 *      |   \                                |
		 *    1 |     \ 2                            | 1
		 *      |       \                            |
		 *      |         \                          |
		 *      |  2    3  \           6             |
		 *      0-----1-----2------------------------3
		 * 
		 */
		WeightedAdjListGraph<Integer> graph = new WeightedAdjListGraph<>();
		graph.addUnDirectedEdge(0, 4, 1);
		graph.addUnDirectedEdge(0, 1, 2);
		graph.addUnDirectedEdge(1, 2, 3);
		graph.addUnDirectedEdge(4, 2, 2);
		graph.addUnDirectedEdge(4, 5, 4);
		graph.addUnDirectedEdge(5, 3, 1);
		graph.addUnDirectedEdge(2, 3, 6);
		return graph;
	}

	private static WeightedAdjListGraph<Integer> buildGraph2() {
		/*-  
		 * 				
		 *            2          3       2
		 *         0---------4------6-------1
		 *          \       /\              |
		 *          3\   1 /  \ 1           |1
		 *            \   /    \            |
		 *             \ /      \      3    |
		 *              5        2----------3
		 *            
		 *            
		 *            
		 *      
		 * 
		 */
		WeightedAdjListGraph<Integer> graph = new WeightedAdjListGraph<>();
		graph.addUnDirectedEdge(0, 4, 2);
		graph.addUnDirectedEdge(0, 5, 3);
		graph.addUnDirectedEdge(4, 6, 3);
		graph.addUnDirectedEdge(4, 2, 1);
		graph.addUnDirectedEdge(5, 4, 1);
		graph.addUnDirectedEdge(6, 1, 2);
		graph.addUnDirectedEdge(2, 3, 3);
		graph.addUnDirectedEdge(1, 3, 1);
		return graph;
	}

	private static WeightedAdjListGraph<Integer> buildGraph3() {
		/*-  
		 * 				
		 *                     5
		 *                4--------3
		 *               /| \      | \
		 *             9/ |  \     |  \ 8
		 *             /  |   \ 3  |3  \
		 *            5  1|    \   |    6
		 *             \  |     \  |   /
		 *             4\ |      \ |  / 7
		 *               \|       \| /
		 *                1--------2
		 *                      2
		 * 
		 */
		WeightedAdjListGraph<Integer> graph = new WeightedAdjListGraph<>();
		graph.addUnDirectedEdge(1, 2, 2);
		graph.addUnDirectedEdge(1, 4, 1);
		graph.addUnDirectedEdge(1, 5, 4);
		graph.addUnDirectedEdge(2, 4, 3);
		graph.addUnDirectedEdge(2, 3, 3);
		graph.addUnDirectedEdge(2, 6, 7);
		graph.addUnDirectedEdge(3, 6, 8);
		graph.addUnDirectedEdge(3, 4, 5);
		graph.addUnDirectedEdge(5, 4, 9);
		return graph;
	}

	private static WeightedAdjListGraph<Integer> buildGraph4() {
		/*-  
		 * 				
		 *                     8       7
		 *                1-------2--------3
		 *               /|       | \      | \
		 *             4/ |      2|  \     |  \ 9
		 *             /  |       |   \ 4  |14 \
		 *            0   |11    /8    \   |    4
		 *             \  |    /  |     \  |   /
		 *             8\ | 7/   6|      \ |  / 10
		 *               \|/      |       \| /
		 *                7-------6--------5
		 *                     1       2    
		 * 
		 */
		WeightedAdjListGraph<Integer> graph = new WeightedAdjListGraph<>();
		graph.addUnDirectedEdge(0, 1, 4);
		graph.addUnDirectedEdge(0, 7, 8);
		graph.addUnDirectedEdge(1, 2, 8);
		graph.addUnDirectedEdge(1, 7, 11);
		graph.addUnDirectedEdge(2, 3, 7);
		graph.addUnDirectedEdge(2, 8, 2);
		graph.addUnDirectedEdge(2, 5, 4);
		graph.addUnDirectedEdge(3, 5, 14);
		graph.addUnDirectedEdge(3, 4, 9);
		graph.addUnDirectedEdge(4, 5, 10);
		graph.addUnDirectedEdge(5, 6, 2);
		graph.addUnDirectedEdge(6, 8, 6);
		graph.addUnDirectedEdge(6, 7, 1);
		graph.addUnDirectedEdge(7, 8, 7);
		return graph;
	}

	private static <T> void runDijkstrAlgorithm(WeightedAdjListGraph<T> graph, T source, int graphNumber) {
		List<T> allVertex = graph.getAllVertex();
		Map<T, List<Edge<T>>> adjList = graph.getAdjList();
		if (!allVertex.contains(source)) {
			System.err.println("Provided Source node:" + source + " is not present on current Graph:" + graphNumber);
		}
		LocalDateTime start = LocalDateTime.now();
		Map<T, Integer> shortestPathToAllNode = dijkstraAlgo(adjList, source);
		LocalDateTime end = LocalDateTime.now();
		System.out.println("Time Take to run Dijkstra's Algorithm in Graph:" + graphNumber + " From Source:" + source
				+ ", is:" + ChronoUnit.MILLIS.between(start, end));
		System.out.println("***Shortest Path in Graph:" + graphNumber + " From Source:" + source + " To All Node***");
		shortestPathToAllNode.entrySet().forEach(entry -> {
			System.out.println(source + "-->" + entry.getKey() + "=" + entry.getValue());
		});
		System.out.println("**********************************************************");
	}

	private static <T> Map<T, Integer> dijkstraAlgo(Map<T, List<Edge<T>>> adjList, T source) {
		Map<T, Integer> distanceMap = new HashMap<>();
		Set<Node<T>> set = new TreeSet<>();
		distanceMap.put(source, 0);
		set.add(new Node<>(source, 0));
		while (!set.isEmpty()) {
			Node<T> shortestDistanceNode = set.stream().findFirst().orElse(null);
			if (shortestDistanceNode == null) {
				break;
			}
			List<Edge<T>> adjacents = adjList.get(shortestDistanceNode.node);
			for (Edge<T> adj : adjacents) {
				int distance = shortestDistanceNode.getDistance() + adj.getWeight();
				distanceMap.compute(adj.getDestination(), (k, v) -> {
					if (v == null) {
						set.add(new Node<>(adj.getDestination(), distance));
						return distance;
					} else {
						if (distance < v) {
							set.add(new Node<>(adj.getDestination(), distance));
						}
						return Math.min(distance, v);
					}
				});
			}
			set.remove(shortestDistanceNode);
		}
		return distanceMap;
	}

	@Data
	@AllArgsConstructor
	private static class Node<T> implements Comparable<Node<T>> {
		T node;
		Integer distance;

		@Override
		public int compareTo(Node<T> other) {
			return this.distance.compareTo(other.distance);
		}
	}
}
