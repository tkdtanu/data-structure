package com.tkd.data.structure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import com.tkd.data.structure.graph.WeightedAdjListGraph.Edge;

public class FindShortestPathInDAGUsingTopologicalSort {

	public static void main(String[] args) {
		graph1(0);
		System.out.println("=========================================");
		graph2(0);
	}

	private static void graph1(int source) {
		WeightedAdjListGraph<Integer> graph = buildGraph1();
		List<Integer> topoSort = TopologicalSort.topoSortWithWeight(graph.getAdjList());
		findShortedDistanceUsingTopoSort(source, graph, topoSort);
	}

	private static void graph2(int source) {
		WeightedAdjListGraph<Integer> graph = buildGraph2();
		List<Integer> topoSort = TopologicalSort.topoSortWithWeight(graph.getAdjList());
		findShortedDistanceUsingTopoSort(source, graph, topoSort);
	}

	private static WeightedAdjListGraph<Integer> buildGraph1() {
		/*-  
		 *                       4
		 *      4----------------------------------->5
		 *      ^ \                                  |
		 *      |   \                                |
		 *    1 |     \ 2                            | 1
		 *      |       \                            |
		 *      |         \                          |
		 *      |  2    3  \           6             v
		 *      0---->1---->2----------------------->3
		 * 
		 */
		WeightedAdjListGraph<Integer> graph = new WeightedAdjListGraph<>();
		graph.addDirectedEdge(0, 4, 1);
		graph.addDirectedEdge(0, 1, 2);
		graph.addDirectedEdge(1, 2, 3);
		graph.addDirectedEdge(4, 2, 2);
		graph.addDirectedEdge(4, 5, 4);
		graph.addDirectedEdge(5, 3, 1);
		graph.addDirectedEdge(2, 3, 6);
		return graph;
	}

	private static WeightedAdjListGraph<Integer> buildGraph2() {
		/*-  
		 * 				
		 *            2       3       2
		 *         0------>4----->6------->1
		 *          \      ^\              |
		 *          3\   1 / \ 1           |1
		 *            \   /   \            |
		 *             v |     v      3    v
		 *              5       2--------->3
		 *            
		 *            
		 *            
		 *      
		 * 
		 */
		WeightedAdjListGraph<Integer> graph = new WeightedAdjListGraph<>();
		graph.addDirectedEdge(0, 4, 2);
		graph.addDirectedEdge(0, 5, 3);
		graph.addDirectedEdge(4, 6, 3);
		graph.addDirectedEdge(4, 2, 1);
		graph.addDirectedEdge(5, 4, 1);
		graph.addDirectedEdge(6, 1, 2);
		graph.addDirectedEdge(2, 3, 3);
		graph.addDirectedEdge(1, 3, 1);
		return graph;
	}

	private static void findShortedDistanceUsingTopoSort(int source, WeightedAdjListGraph<Integer> graph,
			List<Integer> topoSort) {
		if (CollectionUtils.isEmpty(topoSort)) {
			System.err.println("Topo Sort is Empty, Can't proceed further");
			return;
		}
		Map<Integer, List<Edge<Integer>>> adjList = graph.getAdjList();
		Map<Integer, Integer> distances = new HashMap<>();

		distances.put(source, 0); // As Source to Source Distance will be 0

		for (Integer vertex : topoSort) {
			List<Edge<Integer>> adjacents = Optional.ofNullable(adjList.get(vertex)).orElse(new ArrayList<>());
			for (Edge<Integer> edge : adjacents) {
				distances.compute(edge.destination, (k, v) -> {
					int wt = distances.get(vertex) + edge.weight;
					if (v == null) {
						return wt;
					}
					return Math.min(v, wt);
				});
			}
		}

		System.out.println("Shortest Path From:" + source + " to All Nodes=" + distances);

	}

}
