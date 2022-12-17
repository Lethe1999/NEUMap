package map_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;




public abstract class Graph {
	
	//This arrayList contains all the nodes.
	ArrayList<GraphNode> nodes;
	
	
	//With this method, we add GraphEdge to both nodes with the distance in between the nodes.
	public void addEdge(GraphNode node1, GraphNode node2, int distance) {
		
		if (nodes.contains(node1) && nodes.contains(node2))
		{
			node1.addEdge(node2, distance);
			node2.addEdge(node1, distance);
		}
		
	}
	
	public void removeEdge(GraphNode node1, GraphNode node2) {
		
		if(nodes.contains(node2) && nodes.contains(node1))
		{
			node1.removeEdge(node2);
			node2.removeEdge(node1);
		}
		
	}
	
	
	//In this value we also store the last node, to trace the path of the node from the start node. 
	//We accomplish by storing a path node instead of just the distance as done in the previous version of
	//dijkstra.
	public static HashMap<GraphNode, GraphEdge> djikstra_path(Graph graph, GraphNode start, GraphNode end )
	{
		HashMap<GraphNode,GraphEdge> distance_map = new HashMap<GraphNode,GraphEdge>();
		HashMap<GraphNode,GraphEdge> shortest_map = new HashMap<GraphNode,GraphEdge>();
		
		//In this function, we intialize the distance dict, with the nodes of the graph. We instantiate the 
		//path nodes with null and MAX_VALUE for distance.
		for(GraphNode node: graph.nodes)
			distance_map.put(node, new GraphEdge(null,Integer.MAX_VALUE));
			
		//We set the distance and prev_node for the star node, as the start node itself and 0.
		distance_map.put(start, new GraphEdge(start, 0));
		
		
		//We start a while loop to run till the distance dict is not empty.
		while(distance_map.size()>0)
		{
			//Now we extract the distance from the GraphEdges to sort the distances to find the current node.
			ArrayList<GraphEdge> GraphEdges = new ArrayList<>(distance_map.values());
			
			ArrayList<Integer> distances = new ArrayList<>();
			
			for(GraphEdge node: GraphEdges)
				distances.add(node.distance);
			
			Collections.sort(distances);
			
			GraphNode current_node=null;
			
			//We set the current node and add to the shortest dict.
			for(GraphNode node: distance_map.keySet())
			{
				if(distance_map.get(node).distance==distances.get(0))
				{
					current_node = node;
					shortest_map.put(node, distance_map.get(node));
					
					break;
				}
					
			}
			
			//We visit the edge nodes of the current node that are still in the distance dict.
			for(GraphEdge edge: current_node.edges)
			{
				if(distance_map.containsKey(edge.node))
				{
					//We check the distance of each neighbor node from the current node, and if it is smaller
					//than the distance is distance dict, we set the new GraphEdge, with updated distance and
					//previous node.
					int distance_to_neighbour = distance_map.get(current_node).distance+edge.distance;
					
					if(distance_map.get(edge.node).distance>distance_to_neighbour)
					{
						distance_map.put(edge.node, new GraphEdge(current_node, distance_to_neighbour));
					}
				}
			}
			//We remove the current node from the distance dict.
			distance_map.remove(current_node);
			
			
			
		}
		
		//We return the shortest_map with the final values.
		return shortest_map;
		
	}
	
	public abstract ArrayList<String> getPath(String start, String end);
	
	public abstract int getDistance(String start, String end);


}
