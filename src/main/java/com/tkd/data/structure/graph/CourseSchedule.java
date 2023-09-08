package com.tkd.data.structure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CourseSchedule {

	public static void main(String[] args) {
		System.out.println(canFinish(2, new int[][] { { 1, 0 } }));
		System.out.println(canFinishV2(2, new int[][] { { 1, 0 } }));
		System.out.println(canFinish(2, new int[][] { { 1, 0 }, { 0, 1 } }));
		System.out.println(canFinishV2(2, new int[][] { { 1, 0 }, { 0, 1 } }));
		System.out.println(canFinish(5, new int[][] { { 1, 4 }, { 2, 4 }, { 3, 1 }, { 3, 2 } }));
		System.out.println(canFinishV2(5, new int[][] { { 1, 4 }, { 2, 4 }, { 3, 1 }, { 3, 2 } }));
		System.out.println(canFinish(5, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } }));
		System.out.println(canFinishV2(5, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } }));

	}

	public static boolean canFinishV2(int numCourses, int[][] pr) {

		ArrayList<Integer>[] adjList = new ArrayList[numCourses];
		boolean[] visited = new boolean[numCourses];
		for (int[] course : pr) {
			int from = course[1];
			int to = course[0];
			ArrayList<Integer> nodes = Optional.ofNullable(adjList[from]).orElse(new ArrayList<>());
			nodes.add(to);
			adjList[from] = nodes;
		}

		System.out.println(Arrays.toString(adjList));

		for (int i = 0; i < numCourses; i++) {
			ArrayList<Integer> list = adjList[i];
			if (list != null && isCyclePresent(i, adjList, new boolean[numCourses], visited)) {
				return false;
			}
		}

		return true;
	}

	private static boolean isCyclePresent(int s, ArrayList<Integer>[] adjList, boolean[] dfsVisited,
			boolean[] visited) {
		if (dfsVisited[s]) {
			return true;
		}
		if (visited[s]) {
			return false;
		}

		visited[s] = true;
		dfsVisited[s] = true;
		List<Integer> nodes = adjList[s];
		if (nodes == null) {
			return false;
		}

		for (Integer node : nodes) {
			if (isCyclePresent(node, adjList, dfsVisited, visited)) {
				return true;
			}
			dfsVisited[node] = false;
		}
		return false;
	}

	public static boolean canFinish(int numCourses, int[][] pr) {

		Map<Integer, List<Integer>> adjList = new HashMap<>();
		Set<Integer> visited = new HashSet<>();
		for (int[] course : pr) {
			adjList.compute(course[1], (k, v) -> {
				if (v == null) {
					v = new ArrayList<>();
				}
				v.add(course[0]);
				return v;
			});
		}

		System.out.println(adjList);

		for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
			if (isCyclePresent(entry.getKey(), adjList, new HashSet<>(), visited)) {
				return false;
			}
		}

		return true;
	}

	private static boolean isCyclePresent(int s, Map<Integer, List<Integer>> adjList, Set<Integer> dfsVisited,
			Set<Integer> visited) {
		if (dfsVisited.contains(s)) {
			return true;
		}
		if (visited.contains(s)) {
			return false;
		}

		visited.add(s);
		dfsVisited.add(s);
		List<Integer> nodes = adjList.get(s);
		if (nodes == null) {
			return false;
		}

		for (Integer node : nodes) {
			if (isCyclePresent(node, adjList, dfsVisited, visited)) {
				return true;
			}
			dfsVisited.remove(node);
		}
		return false;
	}

}
