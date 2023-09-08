package com.tkd.data.structure.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;

import lombok.Data;

@Data
public class AdjListGraph<T extends Comparable<T>> {

	Map<T, List<T>> adjList = new HashedMap<>();
	Set<T> vertices = new HashSet<>();

	public int getTotalVertexCount() {
		return vertices.size();
	}

	public List<T> getAllVertex() {
		return new ArrayList<>(vertices);
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
			vertices.add(source);
		}
		if (destination != null) {
			vertices.add(destination);
		}
	}

	public void addUnDirectedEdge(T source, T destination) {
		if (source != null) {
			vertices.add(source);
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
			vertices.add(destination);
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

}
