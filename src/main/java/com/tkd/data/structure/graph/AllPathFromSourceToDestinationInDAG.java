package com.tkd.data.structure.graph;

import java.util.ArrayList;
import java.util.List;

public class AllPathFromSourceToDestinationInDAG {

	public static void main(String[] args) {
		graph1();
		graph2();
		graph3();
	}

	private static void graph1() {
		AdjListGraph<Integer> graph1 = buildGraph1();
		printAllPathFromSourceToDestination(graph1, 4, 7);
		printAllPathFromSourceToDestination(graph1, 6, 5);
	}

	private static void graph2() {
		AdjListGraph<Integer> graph2 = buildGraph2();
		printAllPathFromSourceToDestination(graph2, 0, 5);
		printAllPathFromSourceToDestination(graph2, 4, 3);
	}

	private static void graph3() {
		AdjListGraph<Integer> graph3 = buildGraph3();
		printAllPathFromSourceToDestination(graph3, 0, 1);
		printAllPathFromSourceToDestination(graph3, 0, 3);
	}

	private static AdjListGraph<Integer> buildGraph1() {
		/*-
		 *   4--->5--->6--->7
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addDirectedEdge(4, 5);
		graph.addDirectedEdge(5, 6);
		graph.addDirectedEdge(6, 7);

		return graph;
	}

	private static AdjListGraph<Integer> buildGraph2() {
		/*-  
		 *                       
		 *      4----------------------------------->5
		 *      ^ \                                  |
		 *      |   \                                |
		 *      |     \                              | 
		 *      |       \                            |
		 *      |         \                          |
		 *      |          \                         v
		 *      0---->1---->2----------------------->3
		 * 
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addDirectedEdge(0, 4);
		graph.addDirectedEdge(0, 1);
		graph.addDirectedEdge(1, 2);
		graph.addDirectedEdge(4, 2);
		graph.addDirectedEdge(4, 5);
		graph.addDirectedEdge(5, 3);
		graph.addDirectedEdge(2, 3);
		return graph;
	}

	private static AdjListGraph<Integer> buildGraph3() {
		/*-  
		 * 				
		 *                             
		 *         0------>4----->6------->1
		 *          \      ^\              |
		 *           \     / \             |
		 *            \   /   \            |
		 *             v |     v           v
		 *              5       2--------->3
		 *            
		 *            
		 *            
		 *      
		 * 
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addDirectedEdge(0, 4);
		graph.addDirectedEdge(0, 5);
		graph.addDirectedEdge(4, 6);
		graph.addDirectedEdge(4, 2);
		graph.addDirectedEdge(5, 4);
		graph.addDirectedEdge(6, 1);
		graph.addDirectedEdge(2, 3);
		graph.addDirectedEdge(1, 3);
		return graph;
	}

	private static void printAllPathFromSourceToDestination(AdjListGraph<Integer> graph, int source, int destination) {
		List<List<Integer>> result = new ArrayList<>();
		dfs(graph, source, destination, new ArrayList<>(), result);
		if (result.isEmpty()) {
			System.out.println("There is no path From:" + source + " To:" + destination);
		} else {
			System.out.println("****All Path path From:" + source + " To:" + destination + "****");

			for (List<Integer> path : result) {

				for (int i = 0; i < path.size(); i++) {
					System.out.print(path.get(i));
					if (i != path.size() - 1) {
						System.out.print("--->");
					}
				}
				System.out.println();
			}

		}
		System.out.println("******************************");
	}

	private static void dfs(AdjListGraph<Integer> graph, int current, int destination, List<Integer> path,
			List<List<Integer>> result) {

		path.add(current);
		if (current == destination) {
			result.add(path);
		} else {
			List<Integer> adjacents = graph.adjList.getOrDefault(current, new ArrayList<>());
			for (int node : adjacents) {
				dfs(graph, node, destination, new ArrayList<>(path), result);
			}
		}

	}

}
