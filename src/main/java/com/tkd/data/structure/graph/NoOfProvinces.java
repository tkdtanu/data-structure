package com.tkd.data.structure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * There are n cities. Some of them are connected, while some are not. If city a
 * is connected directly with city b, and city b is connected directly with city
 * c, then city a is connected indirectly with city c.
 * 
 * A province is a group of directly or indirectly connected cities and no other
 * cities outside of the group.
 * 
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the
 * ith city and the jth city are directly connected, and isConnected[i][j] = 0
 * otherwise.
 * 
 * Return the total number of provinces.
 *
 */
public class NoOfProvinces {

	public static void main(String[] args) {
		NoOfProvinces obj = new NoOfProvinces();
		example1(obj);
		example2(obj);
	}

	private static void example1(NoOfProvinces obj) {
		int[][] cityConnection = new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
		run(obj, cityConnection);
	}

	private static void example2(NoOfProvinces obj) {
		int[][] cityConnection = new int[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
		run(obj, cityConnection);
	}

	private static void run(NoOfProvinces obj, int[][] cityConnection) {
		System.out.println("Total No of Provinces=" + obj.findCircleNum(cityConnection));
	}

	public int findCircleNum(int[][] graph) {
		int n = graph.length;
		boolean[] visited = new boolean[n];

		int total = 0;

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfs(graph, visited, i, n);
				total++;
			}
		}

		return total;
	}

	private void dfs(int[][] graph, boolean[] visited, int source, int n) {
		visited[source] = true;
		List<Integer> adjList = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (!visited[i] && graph[source][i] == 1) {
				adjList.add(i);
			}
		}

		for (int dest : adjList) {
			if (!visited[dest]) {
				dfs(graph, visited, dest, n);
			}
		}
	}
}
