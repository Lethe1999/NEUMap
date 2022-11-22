package dijkstra;

import java.util.ArrayList;

public class Stack {
	
	ArrayList<GraphNode> arr;
	
	public Stack() {
		// TODO Auto-generated constructor stub
		arr = new ArrayList<GraphNode>();
		
	}
	
	public void push(GraphNode node) {
		
		arr.add(node);
		
	}
	
	public GraphNode pop() {
		
		return arr.remove(arr.size()-1);		
		
	}
	
	public boolean isEmpty()
	{
		return arr.isEmpty();
	}
	
	public boolean contains(GraphNode node)
	{
		return arr.contains(node);
	}
	
	public void printStack() {
		
		for(int i=0; i<arr.size();i++)
			System.out.print(arr.get(i).value+ " ");
		
	}

}
