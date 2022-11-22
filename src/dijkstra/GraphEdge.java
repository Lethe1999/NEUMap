package dijkstra;

public class GraphEdge {
	
	GraphNode node;
	int distance;
	
	public GraphEdge(GraphNode destination, int distance) {
		this.node = destination;
		this.distance = distance;
		
	}

}
