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

import com.tkd.data.structure.graph.WeightedAdjListGraph.Edge;

public class TopologicalSort {

	public static void main(String[] args) {
		graph1();
		System.out.println("============================");
		graph2();
		System.out.println("============================");
		graph3();
		System.out.println("============================");
		graph4();
		System.out.println("============================");
		graph5();
	}

	private static void graph1() {
		/*-
		 *   4--->5--->6--->7
		 */
		AdjListGraph<Integer> graph = new AdjListGraph<>();
		graph.addDirectedEdge(4, 5);
		graph.addDirectedEdge(5, 6);
		graph.addDirectedEdge(6, 7);
		topoSortWithoutWeight(graph.adjList);
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
		topoSortWithoutWeight(graph.adjList);
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

		topoSortWithoutWeight(graph.adjList);
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

		topoSortWithoutWeight(graph.adjList);
	}

	private static void graph5() {
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

		topoSortWithoutWeight(graph.adjList);
	}

	public static <T> List<T> topoSortWithWeight(Map<T, List<Edge<T>>> adjList) {
		Map<T, Integer> indegreMap = new HashMap<>();
		for (Map.Entry<T, List<Edge<T>>> entry : adjList.entrySet()) {
			T key = entry.getKey();

			indegreMap.computeIfAbsent(key, t -> 0);
			List<Edge<T>> nodes = entry.getValue();
			for (Edge<T> node : nodes) {
				indegreMap.compute(node.destination, (k, v) -> v == null ? 1 : v + 1);
			}
		}
		System.out.println("InDegree Of Graph=>" + indegreMap);
		LocalDateTime time = LocalDateTime.now();
		List<T> topSortList = topoSortWithWeight(indegreMap, adjList);
		System.out.println(
				"Time Taken for Topological Sort in Millis=" + ChronoUnit.MILLIS.between(time, LocalDateTime.now()));
		return topSortList;
	}

	private static <T> List<T> topoSortWithWeight(Map<T, Integer> indegreMap, Map<T, List<Edge<T>>> adjList) {
		List<T> topSortList = new ArrayList<>();
		Queue<T> q = new LinkedList<>();
		Set<T> visited = new HashSet<>();
		q.addAll(getNodesWithZeroInDegree(indegreMap, visited));
		if (q.isEmpty()) {
			System.out.println("There is no Node without any InDegree or There is Cycle on Graph");
			return topSortList;
		}
		while (!q.isEmpty()) {
			T node = q.remove();
			topSortList.add(node);
			decreaseInDegree(node, indegreMap, adjList);
			q.addAll(getNodesWithZeroInDegree(indegreMap, visited));
		}
		System.out.println("Topological Sorted Nodes=>" + topSortList);
		return topSortList;
	}

	public static <T> List<T> topoSortWithoutWeight(Map<T, List<T>> adjList) {
		Map<T, Integer> indegreMap = new HashMap<>();
		for (Map.Entry<T, List<T>> entry : adjList.entrySet()) {
			T key = entry.getKey();

			indegreMap.computeIfAbsent(key, t -> 0);
			List<T> nodes = entry.getValue();
			for (T node : nodes) {
				indegreMap.compute(node, (k, v) -> v == null ? 1 : v + 1);
			}
		}
		System.out.println("InDegree Of Graph=>" + indegreMap);
		LocalDateTime time = LocalDateTime.now();
		List<T> topSortList = topoSortWithoutWeight(indegreMap, adjList);
		System.out.println(
				"Time Taken for Topological Sort in Millis=" + ChronoUnit.MILLIS.between(time, LocalDateTime.now()));
		return topSortList;
	}

	private static <T> List<T> topoSortWithoutWeight(Map<T, Integer> indegreMap, Map<T, List<T>> adjList) {
		List<T> topSortList = new ArrayList<>();
		Queue<T> q = new LinkedList<>();
		Set<T> visited = new HashSet<>();
		q.addAll(getNodesWithZeroInDegree(indegreMap, visited));
		if (q.isEmpty()) {
			System.out.println("There is no Node without any OutDegree or There is Cycle on Graph");
			return topSortList;
		}
		while (!q.isEmpty()) {
			T node = q.remove();
			topSortList.add(node);
			decreaseInDegreeOfNode(node, indegreMap, adjList);
			q.addAll(getNodesWithZeroInDegree(indegreMap, visited));
		}
		System.out.println("Topological Sorted Nodes=>" + topSortList);
		return topSortList;
	}

	private static <T> List<T> getNodesWithZeroInDegree(Map<T, Integer> indegreMap, Set<T> visited) {
		List<T> nodesWithZeroInDegree = indegreMap.entrySet().stream().filter(entry -> entry.getValue() == 0)
				.map(Entry::getKey).filter(t -> !visited.contains(t)).collect(Collectors.toList());
		visited.addAll(nodesWithZeroInDegree);
		return nodesWithZeroInDegree;
	}

	private static <T> void decreaseInDegreeOfNode(T node, Map<T, Integer> indegreMap, Map<T, List<T>> adjList) {
		List<T> linkedNodes = adjList.get(node);
		if (CollectionUtils.isEmpty(linkedNodes)) {
			return;
		}

		for (T linkedNode : linkedNodes) {
			indegreMap.computeIfPresent(linkedNode, (k, v) -> v - 1);
		}
	}

	private static <T> void decreaseInDegree(T node, Map<T, Integer> indegreMap, Map<T, List<Edge<T>>> adjList) {
		List<Edge<T>> linkedNodes = adjList.get(node);
		if (CollectionUtils.isEmpty(linkedNodes)) {
			return;
		}

		for (Edge<T> linkedNode : linkedNodes) {
			indegreMap.computeIfPresent(linkedNode.destination, (k, v) -> v - 1);
		}

	}

}
