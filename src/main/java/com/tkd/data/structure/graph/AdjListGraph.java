package com.tkd.data.structure.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;

public class AdjListGraph<T> {

	Map<T, List<T>> adjList = new HashedMap<>();
	Set<T> visitedDp = new HashSet<>();
	Set<T> edges = new HashSet<>();

	public int getTotalEdgeCount() {
		return edges.size();
	}

	public List<T> getAllEdges() {
		return new ArrayList<T>(adjList.keySet());
	}

	public void addDirectedEdge(T source, T destination) {
		adjList.compute(source, (k, v) -> {
			if (v == null) {
				List<T> list = new ArrayList<>();
				list.add(destination);
				return list;
			} else {
				v.add(destination);
				return v;
			}
		});
		if (source != null) {
			edges.add(source);
		}
		if (destination != null) {
			edges.add(destination);
		}
	}

	public void addUnDirectedEdge(T source, T destination) {
		if (source != null) {
			edges.add(source);
			adjList.compute(source, (k, v) -> {
				if (v == null) {
					List<T> list = new ArrayList<>();
					list.add(destination);
					return list;
				} else {
					v.add(destination);
					return v;
				}
			});
		}
		if (destination != null) {
			edges.add(destination);
			adjList.compute(destination, (k, v) -> {
				if (v == null) {
					List<T> list = new ArrayList<>();
					list.add(source);
					return list;
				} else {
					v.add(source);
					return v;
				}
			});
		}

	}

	public void printBfs(T source) {
		if (source == null) {
			System.err.println("Source can't be null");
			return;
		}
		if (adjList.isEmpty()) {
			System.err.println("Graph is empty");
			return;
		}
		System.out.println("Printing BFS From Node:" + source);
		visitedDp = new HashSet<>();// To Clear existing things
		bfs(source);
	}

	private void bfs(T source) {
		Queue<T> queue = new LinkedList<>();
		queue.add(source);
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			System.out.print("Printing Level:" + level + " Node-->");
			while (size != 0) {
				T node = queue.remove();
				if (!visitedDp.contains(node)) {
					System.out.print(" " + node);
					visitedDp.add(node);
					List<T> adjs = adjList.getOrDefault(node, List.of());
					for (T t : adjs) {
						if (!visitedDp.contains(t)) {
							queue.add(t);
						}
					}
				}
				size--;
			}
			System.out.println();
			level++;
		}
	}

}
