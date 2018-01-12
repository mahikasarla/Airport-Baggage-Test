package com.mahi.interview.baggagerouting.model;

public class RouteEdge {
	private final String id;
	private final Vertex source;
	private final Vertex destination;
	private final int baggageweight;

	public RouteEdge(String id, Vertex source, Vertex destination, int weight) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.baggageweight = weight;
	}

	public String getId() {
		return id;
	}

	public Vertex getDestination() {
		return destination;
	}

	public Vertex getSource() {
		return source;
	}

	public int getbaggageweight() {
		return baggageweight;
	}

	@Override
	public String toString() {
		return source + " " + destination;
	}

}
