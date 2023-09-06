package com.tkd.data.structure.graph;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public class TopologicalSort {

	public static void main(String[] args) {
		graph1();
		System.out.println("============================");
		graph2();
		System.out.println("============================");
		graph3();
		System.out.println("============================");
		graph4();
	}

	private static void graph1() {
		/*-
		 *   4--->5--->6--->7
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addDirectedEdge(4, 5);
		graph.addDirectedEdge(5, 6);
		graph.addDirectedEdge(6, 7);
		topSort(graph.adjList);
	}

	private static void graph2() {
		/*- 
		 * 		  2
		 *        |
		 *   	  |
		 *        V
		 *   1--->0<---3
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addDirectedEdge(1, 0);
		graph.addDirectedEdge(2, 0);
		graph.addDirectedEdge(3, 0);
		topSort(graph.adjList);
	}

	private static void graph3() {
		/*-
		 *   1---->4------->5
		 *   	   	       ^^
		 *   	   	      / |
		 *   	   	     /	|
		 *   	        7 	6
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addDirectedEdge(1, 4);
		graph.addDirectedEdge(4, 5);
		graph.addDirectedEdge(7, 5);
		graph.addDirectedEdge(6, 5);

		topSort(graph.adjList);
	}

	private static void graph4() {
		/*-
		 *          0        1
		 *   	   / \      / \
		 *   	  /	  \    /   \
		 *   	 / 	   \  /     \
		 *      <       ><       >
		 *   	2------->3       4 	
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addDirectedEdge(0, 2);
		graph.addDirectedEdge(0, 3);
		graph.addDirectedEdge(1, 3);
		graph.addDirectedEdge(1, 4);
		graph.addDirectedEdge(2, 3);

		topSort(graph.adjList);
	}

	private static void topSort(Map<Integer, List<Integer>> adjList) {
		Map<Integer, Integer> indegreMap = new HashMap<>();
		for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
			Integer key = entry.getKey();

			indegreMap.computeIfAbsent(key, t -> 0);
			List<Integer> nodes = entry.getValue();
			for (Integer node : nodes) {
				indegreMap.compute(node, (k, v) -> v == null ? 1 : v + 1);
			}
		}
		System.out.println("InDegree Of Graph=>" + indegreMap);
		LocalDateTime time = LocalDateTime.now();
		topSort(indegreMap, adjList);
		System.out.println(
				"Time Taken for Topological Sort in Millis=" + ChronoUnit.MILLIS.between(time, LocalDateTime.now()));
	}

	private static void topSort(Map<Integer, Integer> indegreMap, Map<Integer, List<Integer>> adjList) {
		List<Integer> topSortList = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		q.addAll(getNodesWithZeroInDegree(indegreMap, visited));
		if (q.isEmpty()) {
			System.out.println("There is no Node without any InDegree or There is Cycle on Graph");
			return;
		}
		while (!q.isEmpty()) {
			Integer node = q.remove();
			topSortList.add(node);
			decreaseInDegreeOfNode(node, indegreMap, adjList);
			q.addAll(getNodesWithZeroInDegree(indegreMap, visited));
		}
		System.out.println("Topological Sorted Nodes=>" + topSortList);
	}

	private static List<Integer> getNodesWithZeroInDegree(Map<Integer, Integer> indegreMap, Set<Integer> visited) {
		List<Integer> nodesWithZeroInDegree = indegreMap.entrySet().stream().filter(entry -> entry.getValue() == 0)
				.map(Entry::getKey).filter(t -> !visited.contains(t)).collect(Collectors.toList());
		visited.addAll(nodesWithZeroInDegree);
		return nodesWithZeroInDegree;
	}

	private static void decreaseInDegreeOfNode(Integer node, Map<Integer, Integer> indegreMap,
			Map<Integer, List<Integer>> adjList) {
		List<Integer> linkedNodes = adjList.get(node);
		if (CollectionUtils.isEmpty(linkedNodes)) {
			return;
		}

		for (Integer linkedNode : linkedNodes) {
			indegreMap.computeIfPresent(linkedNode, (k, v) -> v - 1);
		}
	}

}
