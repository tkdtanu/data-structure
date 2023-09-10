package com.tkd.data.structure.graph;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.tkd.data.structure.graph.WeightedAdjListGraph.Edge;

public class MSTUsingPrimsAlgorithm {

	public static void main(String[] args) {
		graph1();
		graph2();
		graph3();
		graph4();
	}

	private static void graph1() {
		WeightedAdjListGraph<Integer> graph = buildGraph1();
		runPrimsAlgorithm(graph, 0);
		runPrimsAlgorithm(graph, 5);
	}

	private static void graph2() {
		WeightedAdjListGraph<Integer> graph = buildGraph2();
		runPrimsAlgorithm(graph, 0);
		runPrimsAlgorithm(graph, 3);
	}

	private static void graph3() {
		WeightedAdjListGraph<Integer> graph = buildGraph3();
		runPrimsAlgorithm(graph, 1);
		runPrimsAlgorithm(graph, 4);
	}

	private static void graph4() {
		WeightedAdjListGraph<Integer> graph = buildGraph4();
		runPrimsAlgorithm(graph, 0);
		runPrimsAlgorithm(graph, 6);
		runPrimsAlgorithm(graph, 4);
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

	public static <T> void runPrimsAlgorithm(WeightedAdjListGraph<T> graph, T source) {
		Map<T, Integer> distances = new HashMap<>();
		Set<T> mst = new HashSet<>();
		Map<T, T> parents = new HashMap<>();
		LocalDateTime start = LocalDateTime.now();
		runPrimsAlgorithm(graph, source, distances, mst, parents);
		LocalDateTime end = LocalDateTime.now();
		if (mst.isEmpty()) {
			return;
		}
		System.out.println("Time Taken in MS:" + ChronoUnit.MILLIS.between(start, end));
		System.out.println("*******MST After Running Prim's Algorithm From Source:" + source + "***************");

		parents.entrySet().stream().forEach(entry -> {
			T vertex = entry.getKey();
			T parent = entry.getValue();
			Integer distance = distances.get(vertex);
			System.out.println("Source:" + parent + ", Destination:" + vertex + ", Distance:" + distance);
		});
		System.out.println("*************************************");
	}

	public static <T> void runPrimsAlgorithm(WeightedAdjListGraph<T> graph, T source, Map<T, Integer> distances,
			Set<T> mst, Map<T, T> parents) {
		List<T> allVertex = graph.getAllVertex();
		Map<T, List<Edge<T>>> adjList = graph.getAdjList();
		if (!allVertex.contains(source)) {
			System.err.println("Source:" + source + " doesn't exist on the Graph");
			return;
		}

		// Initial for source
		parents.put(source, null);
		distances.put(source, 0);
		boolean completed = false;
		while (!completed) {
			Entry<T, Integer> minimumNodeEntry = distances.entrySet().stream().filter(t -> !mst.contains(t.getKey()))
					.sorted((t1, t2) -> t1.getValue().compareTo(t2.getValue())).findFirst().orElse(null);
			if (minimumNodeEntry == null) {
				// All Vertex are completed
				completed = true;
				continue;
			}
			T minimumDistanceNodeWhichNotYetAddedToMST = minimumNodeEntry.getKey();
			mst.add(minimumDistanceNodeWhichNotYetAddedToMST);

			List<Edge<T>> adjacents = Optional.ofNullable(adjList.get(minimumDistanceNodeWhichNotYetAddedToMST))
					.orElse(new ArrayList<>()).stream().filter(t -> !mst.contains(t.getDestination()))
					.collect(Collectors.toList());

			for (Edge<T> edge : adjacents) {
				T vertex = edge.destination;
				Integer weight = edge.getWeight();
				distances.compute(vertex, (k, v) -> {
					if (v == null || weight < v) {
						parents.put(vertex, minimumDistanceNodeWhichNotYetAddedToMST);
						return weight;
					} else {
						return v;
					}
				});
			}
		}

	}

}
