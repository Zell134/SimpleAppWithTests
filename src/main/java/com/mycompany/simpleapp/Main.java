package com.mycompany.simpleapp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mycompany.simpleapp.models.Graph;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph();
		setGraph(graph);

		List<String> result = DijkstraSearch.search(graph, "start", "end");
		System.out.println("Dijkstra search result: " + result.get(0) + "; cost - " + result.get(1));

		System.out.println();
		System.out.println(runGreedySearch());

	}

	public static Set<String> runGreedySearch() {
		Set<String> states = new HashSet<>();
		states.addAll(Set.of("mt", "wa", "or", "id", "nv", "ut", "са", "az"));

		Map<String, Set<String>> stations = new HashMap<>();
		stations.put("one", Set.of("id", "nv", "ut"));
		stations.put("two", Set.of("wa", "id", "mt"));
		stations.put("three", Set.of("or", "nv", "са"));
		stations.put("four", Set.of("nv", "ut"));
		stations.put("five", Set.of("ca", "az"));
		return GreedySearch.search(states, stations);
	}

	public static Graph setGraph(Graph graph) {

		graph.putVertex("start");
		graph.putVertex("a");
		graph.putVertex("b");
		graph.putVertex("c");
		graph.putVertex("end");

		graph.addEdge("start", "a", 2);
		graph.addEdge("start", "b", 2);

		graph.addEdge("a", "b", 2);

		graph.addEdge("b", "c", 2);
		graph.addEdge("b", "end", 2);

		graph.addEdge("c", "end", 2);
		graph.addEdge("c", "a", -1);
		return graph;
	}

}
