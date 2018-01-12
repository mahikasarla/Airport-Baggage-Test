package com.mahi.interview.baggagerouting.model;

import java.util.List;

public class GraphMap {
	private final List<Vertex> vertexes;
	private final List<RouteEdge> edges;

	public GraphMap(List<Vertex> vertexes, List<RouteEdge> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
	}

	public List<Vertex> getVertexes() {
		return vertexes;
	}

	public List<RouteEdge> getEdges() {
		return edges;
	}

}
