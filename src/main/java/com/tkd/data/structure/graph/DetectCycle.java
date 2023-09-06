package com.tkd.data.structure.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DetectCycle {

	public static void main(String[] args) {
		graph1(); // No Of Cycle=1
		graph2(); // No Of Cycle=0
		graph3(); // No Of Cycle=1
		graph4(); // No Of Cycle=2

	}

	private static void graph4() {
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
		System.out.println("No of Cycle present on Graph:" + noOfCyclePresent(graph.adjList));
	}

	private static void graph3() {
		/*-
		 *   	4------->5
		 *   	^	     |
		 *   	|	     |
		 *   	|	     v
		 *   	7<-------6
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addDirectedEdge(4, 5);
		graph.addDirectedEdge(5, 6);
		graph.addDirectedEdge(6, 7);
		graph.addDirectedEdge(7, 4);

		System.out.println("No of Cycle present on Graph:" + noOfCyclePresent(graph.adjList));
	}

	private static void graph2() {
		/*-
		 *   4--->5--->6--->7
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addDirectedEdge(4, 5);
		graph.addDirectedEdge(5, 6);
		graph.addDirectedEdge(6, 7);
		System.out.println("No of Cycle present on Graph:" + noOfCyclePresent(graph.adjList));
	}

	private static void graph1() {
		/*-
		 * 
		 *   0------1
		 *   	   / \
		 *   	  /	  \
		 *       3-----2----4----5
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addUnDirectedEdge(0, 1);
		graph.addUnDirectedEdge(1, 2);
		graph.addUnDirectedEdge(1, 3);
		graph.addUnDirectedEdge(2, 3);
		graph.addUnDirectedEdge(2, 4);
		graph.addUnDirectedEdge(4, 5);

		System.out.println("No of Cycle present on Graph:" + noOfCyclePresent(graph.adjList));
	}

	private static int noOfCyclePresent(Map<Integer, List<Integer>> adjList) {
		int noOfCyclePresentInGraph = 0;
		Set<Integer> visited = new HashSet<>();
		for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
			Integer key = entry.getKey();
			if (isCyclePresent(null, key, adjList, visited)) {
				noOfCyclePresentInGraph++;
			}

		}
		return noOfCyclePresentInGraph;
	}

	private static boolean isCyclePresent(Integer parent, Integer node, Map<Integer, List<Integer>> adjList,
			Set<Integer> visited) {
		if (!adjList.containsKey(node)) {
			return false;
		}
		List<Integer> edges = adjList.get(node);
		visited.add(node);
		for (Integer edge : edges) {
			if (!visited.contains(edge)) {
				boolean result = isCyclePresent(node, edge, adjList, visited);
				if (result) {
					return true;
				}
			} else if (parent != null && !edge.equals(parent)) {
				return true;
			}

		}
		return false;
	}

}
