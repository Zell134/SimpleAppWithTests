package com.mycompany.simpleapp.models;

public class Vertex {
	private String label;

	public Vertex(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public int hashCode() {
		return this.label.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Vertex object = (Vertex) obj;
		return this.label.equals(object.getLabel());
	}

	@Override
	public String toString() {
		return this.label;
	}

}
