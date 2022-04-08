package com.mycompany.simpleapp.models;

public class Edge {

	private int weight;
	private Vertex targetVertex;

	public Edge(Vertex targetVertex, int weight) {
		this.weight = weight;
		this.targetVertex = targetVertex;
	}

	public Edge() {

	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex getTargetVertex() {
		return targetVertex;
	}

	public void setTargetVertex(Vertex targetVertex) {
		this.targetVertex = targetVertex;
	}

	@Override
	public boolean equals(Object obj) {
		return this.targetVertex.equals((Vertex) obj);
	}

	@Override
	public String toString() {
		return this.targetVertex + " : " + this.weight;
	}
	
}
