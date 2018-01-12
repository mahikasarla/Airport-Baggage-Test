package com.mahi.interview.baggagerouting;

import com.mahi.interview.baggagerouting.model.GraphMap;
import com.mahi.interview.baggagerouting.model.RouteEdge;
import com.mahi.interview.baggagerouting.model.Vertex;
import junit.framework.TestCase;

public class TestDijkstraAlgorithm extends TestCase{

	private List<Vertex> nodes;
	private List<RouteEdge> routeEdges;
	private GraphMap graphMap;
	private DijkstraAlgorithm dijkstra;
	private Map<String,Vertex[]> bags;
	private Map<String,Vertex> departures;


	public void testExcute() {
		nodes = new ArrayList<Vertex>();
		routeEdges = new ArrayList<RouteEdge>();
		
		//Add Vertexes
		for (int i = 0; i <= 11; i++) {
			if (i==0) {
				Vertex location = new Vertex("Concourse_A_Ticketing", "Concourse_A_Ticketing");
				nodes.add(location);
			}else{
			if (i==11) {
				Vertex location = new Vertex("BaggageClaim", "BaggageClaim");
				nodes.add(location);
			}
			else{
			Vertex location = new Vertex("A" + i, "A" + i);
			nodes.add(location);
			}
			}
		}
		
		
		//Add RouteEdges
		addLane("RouteEdge_0", 0, 5, 5);
		addLane("RouteEdge_1", 5, 11, 5);
		addLane("RouteEdge_2", 5, 10, 4);
		addLane("RouteEdge_3", 5, 1, 6);
		addLane("RouteEdge_4", 1, 2, 1);
		addLane("RouteEdge_5", 2, 3, 1);
		addLane("RouteEdge_6", 3, 4, 1);
		addLane("RouteEdge_7", 10, 9, 1);
		addLane("RouteEdge_8", 9, 8, 1);
		addLane("RouteEdge_9", 8, 7, 1);
		addLane("RouteEdge_10", 7, 6, 1);
		

		// Lets check from location Loc_1 to Loc_10
		graphMap = new GraphMap(nodes, routeEdges);
		dijkstra = new DijkstraAlgorithm(graphMap);
		
		departures = new HashMap<>();
		departures.put("UA11", nodes.get(1));
		departures.put("UA12", nodes.get(1));
		departures.put("UA13", nodes.get(2));
		departures.put("UA14", nodes.get(2));
		departures.put("UA10", nodes.get(1));
		departures.put("UA15", nodes.get(2));
		departures.put("UA16", nodes.get(3));
		departures.put("UA17", nodes.get(4));
		departures.put("UA18", nodes.get(5));
		departures.put("ARRIVAL", nodes.get(11));
		
		bags = new LinkedHashMap<>();
		bags.put("0001", new Vertex[]{nodes.get(0), departures.get("UA12")} );
		bags.put("0002", new Vertex[]{nodes.get(5), departures.get("UA17")} );
		bags.put("0003", new Vertex[]{nodes.get(2), departures.get("UA10")} );
		bags.put("0004", new Vertex[]{nodes.get(8), departures.get("UA18")} );
		bags.put("0005", new Vertex[]{nodes.get(7), departures.get("ARRIVAL")} );
		
		for (Map.Entry<String,Vertex[]> bag : bags.entrySet()) {
			getPathDistance(bag);
		}

	}


	private void getPathDistance(Map.Entry<String,Vertex[]> bag) {
		String bagName=bag.getKey();
		Vertex[] vertexes=bag.getValue();
		dijkstra.execute(vertexes[0]);
		Map<Integer,LinkedList<Vertex>> path = dijkstra.getPath(vertexes[1]);

		assertNotNull(path);
		assertTrue(path.size() > 0);

		for (Map.Entry<Integer,LinkedList<Vertex>> pathDistance : path.entrySet()) {
			System.out.println(bagName+"\t"+pathDistance.getValue().toString()+"\t"+pathDistance.getKey());
		}
	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo,
			int duration) {
		
		//Twoway RouteEdges
		RouteEdge lane = new RouteEdge(laneId, nodes.get(sourceLocNo),
				nodes.get(destLocNo), duration);
		routeEdges.add(lane);
		
		lane = new RouteEdge(laneId, nodes.get(destLocNo),
				nodes.get(sourceLocNo), duration);
		routeEdges.add(lane);
	}
}