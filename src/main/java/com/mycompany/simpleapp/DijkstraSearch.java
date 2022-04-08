package com.mycompany.simpleapp;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.mycompany.simpleapp.models.Edge;
import com.mycompany.simpleapp.models.Graph;
import com.mycompany.simpleapp.models.Vertex;

public class DijkstraSearch {
	
	public static List<String> search(Graph graph, String startVertex, String endVerter) {

		int INFINITY = Integer.MAX_VALUE;
		
		Map<String, Integer> costs = new HashMap<>();		

		Map<Vertex, Vertex> parents = new HashMap<>();
		
		Queue<Vertex> queue = new LinkedList<>();
		
		List<Vertex> processed = new ArrayList<>();
		
		Vertex vertex = new Vertex(startVertex);

		graph.getVertexes().forEach((k, e) -> costs.put(k.getLabel(), INFINITY));
		costs.put(vertex.getLabel(), 0);		
		queue.add(vertex);

		while (!queue.isEmpty()) {
			vertex = queue.poll();
			for (Edge edge : graph.getVertexByName(vertex.getLabel())) {
				int newCost = costs.get(vertex.getLabel()) + edge.getWeight();
				Vertex targetVertex = edge.getTargetVertex();
				if (!processed.contains(targetVertex))
					queue.add(targetVertex);
				if (newCost < costs.get(targetVertex.getLabel())) {
					costs.put(targetVertex.getLabel(), newCost);
					parents.put(targetVertex, vertex);
				}
				processed.add(vertex);
			}
		}

		String vertexName = endVerter;
		Deque<String> chain = new LinkedList<>();
		chain.add(vertexName);
		while (vertexName != startVertex) {
			vertexName = parents.get(new Vertex(vertexName)).getLabel();
			chain.add(vertexName);
		}

		StringBuilder resultString = new StringBuilder();
		while (!chain.isEmpty()) {
			vertexName = chain.pollLast();
			resultString.append(vertexName + (chain.isEmpty() ? "" : " -> "));
		}

		System.out.println(costs);
		List<String> result = new ArrayList<>();
		result.add(resultString.toString());
		result.add(costs.get(endVerter).toString());
		return result;
	}
}
