package com.tkd.data.structure.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class WeightedAdjListGraph<T> {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	static class Edge<T> {
		T source;
		T destination;
		int weight;
	}

	Map<T, List<Edge<T>>> adjList = new HashedMap<>();
	Set<T> vertices = new HashSet<>();

	public boolean isVertexPresent(T vertex) {
		return vertices.contains(vertex);
	}

	public int getTotalVertexCount() {
		return vertices.size();
	}

	public List<T> getAllVertex() {
		return new ArrayList<>(vertices);
	}

	public void addDirectedEdge(T source, T destination, int weight) {
		adjList.compute(source, (k, v) -> {
			if (v == null) {
				List<Edge<T>> list = new ArrayList<>();
				list.add(new Edge<>(source, destination, weight));
				return list;
			} else {
				v.add(new Edge<>(source, destination, weight));
				return v;
			}
		});
		if (source != null) {
			vertices.add(source);
		}
		if (destination != null) {
			vertices.add(destination);
		}
	}

	public void addUnDirectedEdge(T source, T destination, int weight) {
		if (source != null) {
			vertices.add(source);
			adjList.compute(source, (k, v) -> {
				if (v == null) {
					List<Edge<T>> list = new ArrayList<>();
					list.add(new Edge<>(source, destination, weight));
					return list;
				} else {
					v.add(new Edge<>(source, destination, weight));
					return v;
				}
			});
		}
		if (destination != null) {
			vertices.add(destination);
			adjList.compute(destination, (k, v) -> {
				if (v == null) {
					List<Edge<T>> list = new ArrayList<>();
					list.add(new Edge<>(destination, source, weight));
					return list;
				} else {
					v.add(new Edge<>(destination, source, weight));
					return v;
				}
			});
		}

	}
}
