package map_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;




public class Graph {
	
	ArrayList<GraphNode> nodes;
	
	public Graph(ArrayList<GraphNode> list) {
		// TODO Auto-generated constructor stub
		this.nodes = list;
		
		
	}
	
	public void add_edge(GraphNode node1, GraphNode node2, int distance) {
		
		if (nodes.contains(node1) && nodes.contains(node2))
		{
			node1.addChild(node2, distance);
			node2.addChild(node1, distance);
		}
		
	}
	
	public void removeChild(GraphNode node1, GraphNode node2) {
		
		if(nodes.contains(node2) && nodes.contains(node1))
		{
			node1.removeChild(node2);
			node2.removeChild(node1);
		}
		
	}
	
	public static void djikstra(Graph graph, GraphNode start, GraphNode end )
	{
		HashMap<GraphNode,Integer> distance_dict = new HashMap<GraphNode,Integer>();
		HashMap<GraphNode,Integer> shortest_dict = new HashMap<GraphNode,Integer>();
		
		for(GraphNode node: graph.nodes)
			distance_dict.put(node, 1000);
			
		
		distance_dict.put(start, 0);
		
		for(GraphNode node: distance_dict.keySet())
			System.out.println("Node: "+node.value+" Distance:"+Integer.toString(distance_dict.get(node)));
		
		while(distance_dict.size()>0)
		{
			ArrayList<Integer> distances = new ArrayList<>(distance_dict.values());
			Collections.sort(distances);
			
			for(int distance: distances)
				System.out.println(distance);
			
			GraphNode current_node=null;
			
			for(GraphNode node: distance_dict.keySet())
			{
				if(distance_dict.get(node)==distances.get(0))
				{
					current_node = node;
					shortest_dict.put(node, distance_dict.get(node));
					
					break;
				}
					
			}
			
			for(GraphEdge edge: current_node.edges)
			{
				if(distance_dict.containsKey(edge.node))
				{
					int distance_to_neighbour = distance_dict.get(current_node)+edge.distance;
					
					if(distance_dict.get(edge.node)>distance_to_neighbour)
					{
						distance_dict.put(edge.node, distance_to_neighbour);
					}
				}
			}
			distance_dict.remove(current_node);
			
			
			//return shortest_dict.get(end);
		}
		
		for(GraphNode node: shortest_dict.keySet())
			System.out.println("Node: "+ node.value+ " Distance: "+Integer.toString(shortest_dict.get(node)));
		
	}
	
	public static HashMap<GraphNode, GraphEdge> djikstra_path(Graph graph, GraphNode start, GraphNode end )
	{
		HashMap<GraphNode,GraphEdge> distance_dict = new HashMap<GraphNode,GraphEdge>();
		HashMap<GraphNode,GraphEdge> shortest_dict = new HashMap<GraphNode,GraphEdge>();
		
		for(GraphNode node: graph.nodes)
			distance_dict.put(node, new GraphEdge(null,Integer.MAX_VALUE));
			
		
		distance_dict.put(start, new GraphEdge(start, 0));
		
//		for(GraphNode node: distance_dict.keySet())
//			System.out.println("Node: "+node.value+" Distance:"+Integer.toString(distance_dict.get(node).distance));
		
		while(distance_dict.size()>0)
		{
			ArrayList<GraphEdge> GraphEdges = new ArrayList<>(distance_dict.values());
			
			ArrayList<Integer> distances = new ArrayList<>();
			
			for(GraphEdge node: GraphEdges)
				distances.add(node.distance);
			
			Collections.sort(distances);
			
//			for(int distance: distances)
//				System.out.println(distance);
			
			GraphNode current_node=null;
			
			for(GraphNode node: distance_dict.keySet())
			{
				if(distance_dict.get(node).distance==distances.get(0))
				{
					current_node = node;
					shortest_dict.put(node, distance_dict.get(node));
					
					break;
				}
					
			}
			
			for(GraphEdge edge: current_node.edges)
			{
				if(distance_dict.containsKey(edge.node))
				{
					int distance_to_neighbour = distance_dict.get(current_node).distance+edge.distance;
					
					if(distance_dict.get(edge.node).distance>distance_to_neighbour)
					{
						distance_dict.put(edge.node, new GraphEdge(current_node, distance_to_neighbour));
					}
				}
			}
			distance_dict.remove(current_node);
			
			
			
		}
		
//		for(GraphNode node: shortest_dict.keySet())
//			System.out.println("Node: "+ node.value+ " Distance: "+Integer.toString(shortest_dict.get(node).distance)+" Previous Node:"+shortest_dict.get(node).node.value);
		
		return shortest_dict;
		
	}
	
	public static ArrayList<String> getPath(Graph graph, GraphNode start, GraphNode end)
	{
		
		HashMap<GraphNode, GraphEdge> shortest_distance = djikstra_path(graph, start, end);
		
		ArrayList<String> path = new ArrayList<>();
		path.add(end.value);
		GraphNode node = end;
		
		while(node!=start)
		{
			path.add(shortest_distance.get(node).node.value);
			node = shortest_distance.get(node).node;
		}
		
		
		
		return path;
	}
	
	public static int getDistance(Graph graph, GraphNode start, GraphNode end)
	{
		int total_distance = djikstra_path(graph, start, end).get(end).distance;
		
		return total_distance;
	}

	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GraphNode nodeA = new GraphNode("A");
		GraphNode nodeB = new GraphNode("B");
		GraphNode nodeC = new GraphNode("C");
		GraphNode nodeD = new GraphNode("D");
		GraphNode nodeE = new GraphNode("E");
		
		
		Graph graph1 = new Graph(new ArrayList<>(Arrays.asList(nodeA,nodeB,nodeC,nodeD,nodeE)));
		graph1.add_edge(nodeA,nodeB,3);
		graph1.add_edge(nodeA,nodeD,2);
		graph1.add_edge(nodeB, nodeD, 4);
		graph1.add_edge(nodeB,nodeE,6);
		graph1.add_edge(nodeB,nodeC,1);
		graph1.add_edge(nodeC,nodeE,2);
		graph1.add_edge(nodeE,nodeD,1);
		
		for(GraphNode node: graph1.nodes)
		{
			System.out.println(node.value);
			
			for(GraphEdge edge: node.edges)
				System.out.println("Node: "+edge.node.value+" Distance: "+Integer.toString(edge.distance));
		}
		
		
		
		
		ArrayList<String> path = getPath(graph1, nodeA, nodeC);
		
		for(String c: path)
			System.out.print(c);
		
		
		System.out.println("\nDistance: "+Integer.toString(getDistance(graph1, nodeA, nodeC)));
		
		
		


	}

}
