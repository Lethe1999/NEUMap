package map_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NEUMap extends Graph {

	
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

		this.nodes = new ArrayList<>(Arrays.asList(sec, snell, curry, ell, richards, hayden, cabot, forsyth,
				churchill, marino, speare, columbus, robinson, hurtig, mugar, cullinane, gainsborough, eastvillage,
				hastings, dodge, robinsonquad));
		
		super.addEdge(marino, cabot, 252);
		super.addEdge(marino, forsyth, 168);
		super.addEdge(cabot, forsyth, 260);
		super.addEdge(richards, hayden, 117);
		super.addEdge(richards, cabot, 56);
		super.addEdge(richards, ell, 46);
		super.addEdge(ell, hayden, 84);
		super.addEdge(ell, curry, 112);
		super.addEdge(curry, snell, 70);
		super.addEdge(snell, hayden, 80);
		super.addEdge(snell, sec, 30);
		super.addEdge(sec, forsyth, 196);
		super.addEdge(churchill, forsyth, 112);
		super.addEdge(churchill, snell, 70);
		super.addEdge(churchill, hayden, 50);
		super.addEdge(marino, speare, 163);
		super.addEdge(speare, dodge, 170);
		super.addEdge(dodge, mugar, 90);
		super.addEdge(dodge, hastings, 115);
		super.addEdge(speare, hastings, 173);
		super.addEdge(mugar, eastvillage, 84);
		super.addEdge(eastvillage, gainsborough, 34);
		super.addEdge(mugar, hastings, 90);
		super.addEdge(eastvillage, cullinane, 25);
		super.addEdge(cullinane, hurtig, 117);
		super.addEdge(hurtig, robinson, 60);
		super.addEdge(robinsonquad, robinson, 34);
		super.addEdge(mugar, robinson, 140);
		super.addEdge(dodge, robinsonquad, 150);
		super.addEdge(mugar, robinsonquad, 124);
		super.addEdge(curry, robinsonquad, 80);
		super.addEdge(robinsonquad, columbus, 146);
		super.addEdge(snell, columbus, 210);
		super.addEdge(dodge, ell, 45);
		super.addEdge(ell, mugar, 42);
		super.addEdge(columbus, hurtig, 154);
		super.addEdge(cullinane, robinsonquad, 120);
		super.addEdge(speare, cabot, 172);
		super.addEdge(sec, columbus, 282);

	}
	
	@Override
	public ArrayList<String> getPath(String start, String end) {

		HashMap<GraphNode, GraphEdge> shortest_distance = super.djikstra_path(this, buildingMap.get(start),
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
	
	@Override
	public int getDistance(String start, String end) {
		return super.djikstra_path(this, buildingMap.get(start), buildingMap.get(end))
				.get(buildingMap.get(end)).distance;
	}

}
