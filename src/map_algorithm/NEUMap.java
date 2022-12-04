package map_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NEUMap {

	Graph graph;
	HashMap<String, GraphNode> buildingMap;

	public NEUMap() {

		GraphNode hayden = new GraphNode("hayden");
		GraphNode richards = new GraphNode("richards");
		GraphNode cabot = new GraphNode("cabot");
		GraphNode sec = new GraphNode("sec");
		GraphNode snell = new GraphNode("snell");
		GraphNode curry = new GraphNode("curry");
		GraphNode ell = new GraphNode("ell");
		GraphNode forsyth = new GraphNode("forsyth");
		GraphNode churchill = new GraphNode("churchill");
		GraphNode marino = new GraphNode("marino");
		GraphNode speare = new GraphNode("speare");
		GraphNode columbus = new GraphNode("columbus");
		GraphNode robinson = new GraphNode("robinson");
		GraphNode hurtig = new GraphNode("hurtig");
		GraphNode mugar = new GraphNode("mugar");
		GraphNode cullinane = new GraphNode("cullinane");
		GraphNode gainsborough = new GraphNode("gainsborough");
		GraphNode eastvillage = new GraphNode("eastvillage");
		GraphNode hastings = new GraphNode("hastings");
		GraphNode dodge = new GraphNode("dodge");
		GraphNode robinsonquad = new GraphNode("robinsonquad");

		buildingMap = new HashMap<>();
		buildingMap.put("hayden", hayden);
		buildingMap.put("richards", richards);
		buildingMap.put("cabot", cabot);
		buildingMap.put("sec", sec);
		buildingMap.put("snell", snell);
		buildingMap.put("curry", curry);
		buildingMap.put("ell", ell);
		buildingMap.put("forsyth", forsyth);
		buildingMap.put("churchill", churchill);
		buildingMap.put("marino", marino);
		buildingMap.put("speare", speare);
		buildingMap.put("columbus", columbus);
		buildingMap.put("robinson", robinson);
		buildingMap.put("hurtig", hurtig);
		buildingMap.put("mugar", mugar);
		buildingMap.put("cullinane", cullinane);
		buildingMap.put("gainsborough", gainsborough);
		buildingMap.put("eastvillage", eastvillage);
		buildingMap.put("hastings", hastings);
		buildingMap.put("dodge", dodge);
		buildingMap.put("robinsonquad", robinsonquad);

		this.graph = new Graph(new ArrayList<>(Arrays.asList(sec, snell, curry, ell, richards, hayden, cabot, forsyth,
				churchill, marino, speare, columbus, robinson, hurtig, mugar, cullinane, gainsborough, eastvillage,
				hastings, dodge, robinsonquad)));
		this.graph.add_edge(marino, cabot, 252);
		this.graph.add_edge(marino, forsyth, 168);
		this.graph.add_edge(cabot, forsyth, 260);
		this.graph.add_edge(richards, hayden, 117);
		this.graph.add_edge(richards, cabot, 56);
		this.graph.add_edge(richards, ell, 46);
		this.graph.add_edge(ell, hayden, 84);
		this.graph.add_edge(ell, curry, 112);
		this.graph.add_edge(curry, snell, 70);
		this.graph.add_edge(snell, hayden, 80);
		this.graph.add_edge(snell, sec, 30);
		this.graph.add_edge(sec, forsyth, 196);
		this.graph.add_edge(churchill, forsyth, 112);
		this.graph.add_edge(churchill, snell, 70);
		this.graph.add_edge(churchill, hayden, 50);

		this.graph.add_edge(marino, speare, 163);
		this.graph.add_edge(speare, dodge, 170);
		this.graph.add_edge(dodge, mugar, 90);
		this.graph.add_edge(dodge, hastings, 115);
		this.graph.add_edge(speare, hastings, 173);
		this.graph.add_edge(mugar, eastvillage, 84);
		this.graph.add_edge(eastvillage, gainsborough, 34);
		this.graph.add_edge(mugar, hastings, 90);
		this.graph.add_edge(eastvillage, cullinane, 25);
		this.graph.add_edge(cullinane, hurtig, 117);
		this.graph.add_edge(hurtig, robinson, 60);
		this.graph.add_edge(robinsonquad, robinson, 34);
		this.graph.add_edge(mugar, robinson, 140);
		this.graph.add_edge(dodge, robinsonquad, 150);
		this.graph.add_edge(mugar, robinsonquad, 124);
		this.graph.add_edge(curry, robinsonquad, 80);
		this.graph.add_edge(robinsonquad, columbus, 146);
		this.graph.add_edge(snell, columbus, 210);
		this.graph.add_edge(dodge, ell, 45);
		this.graph.add_edge(ell, mugar, 42);
		this.graph.add_edge(columbus, hurtig, 154);
		this.graph.add_edge(cullinane, robinsonquad, 120);
		this.graph.add_edge(speare, cabot, 172);
		this.graph.add_edge(sec, columbus, 282);

	}

	public ArrayList<String> getPath(String start, String end) {

		HashMap<GraphNode, GraphEdge> shortest_distance = Graph.djikstra_path(this.graph, buildingMap.get(start),
				buildingMap.get(end));

		ArrayList<String> path = new ArrayList<>();
		path.add(buildingMap.get(end).value);
		GraphNode node = buildingMap.get(end);

		while (node != buildingMap.get(start)) {
			path.add(shortest_distance.get(node).node.value);
			node = shortest_distance.get(node).node;
		}

		return path;
	}

	public int getDistance(String start, String end) {
		return Graph.djikstra_path(this.graph, buildingMap.get(start), buildingMap.get(end))
				.get(buildingMap.get(end)).distance;
	}

}
