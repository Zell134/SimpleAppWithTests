package com.mycompany.simpleapp.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

	private Map<Vertex, Set<Edge>> vertexes;

	public Graph() {
		this.vertexes = new HashMap<>();
	}

	public Map<Vertex, Set<Edge>> getVertexes() {
		return vertexes;
	}

	public Set<Edge> getVertexByName(String label) {
		return vertexes.get(new Vertex(label));
	}

	public void putVertex(String label) {
		vertexes.putIfAbsent(new Vertex(label), new HashSet<>());
	}

	public void addEdge(String from, String to, int weight) {

		Vertex startVertex = new Vertex(from);
		Edge edge = new Edge(new Vertex(to), weight);

		if (vertexes.containsKey(startVertex))
			vertexes.get(startVertex).add(edge);
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		vertexes.forEach((k, e) -> {
			string.append("{" + k + " : ");
			string.append(e + "}");
		});
		return string.toString();
	}

}
