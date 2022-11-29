package map_algorithm;

import java.util.ArrayList;

public class GraphNode {
	
	String value;
	ArrayList<GraphEdge> edges;
	
	public GraphNode(String val) {
		// TODO Auto-generated constructor stub
		this.value = val;
		edges = new ArrayList<>();
		
	}
	
	

	public void addChild(GraphNode desination, int distance) {
		
		edges.add(new GraphEdge(desination, distance));		
	}
	
	public void removeChild(GraphNode child) {
		
		for(GraphEdge edge:edges)
		{
			if(edge.node==child)
			{
				edges.remove(edge);
				break;
				
			}
		}
		
				
	}

}
