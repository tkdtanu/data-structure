package com.tkd.data.structure.graph;

public class Runner {

	public static void main(String[] args) {
		AdjListGraph<Integer> graph = new AdjListGraph<>();

		graph.addDirectedEdge(0, 1);
		graph.addDirectedEdge(0, 2);
		graph.addDirectedEdge(1, 2);
		graph.addDirectedEdge(1, 0);
		graph.addDirectedEdge(1, 3);
		graph.addDirectedEdge(2, 1);
		graph.addDirectedEdge(2, 0);
		graph.addDirectedEdge(2, 3);
		graph.addDirectedEdge(3, 1);
		graph.addDirectedEdge(3, 2);

		graph.printBfs(0);
		graph.printBfs(1);
		graph.printBfs(2);
		graph.printBfs(3);
	}

}
