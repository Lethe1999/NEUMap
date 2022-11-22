package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import dijkstra.Graph;
import dijkstra.GraphNode;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MapConroller {

	/* Path colors */
	private static final Color PATH_ORIGIN = Color.web("#1047d352");
	private static final Color PATH_FOUND = Color.web("#fcb1ef");

	/* From and to positions */
	@FXML
	Label from, to;
	private String fromPos, toPos;
	private int selectedBuildings = 0; // To help check from and to positions

	/* Results */
	@FXML
	Label distance, time;

	/* Paths */
	@FXML
	Rectangle snell_curry, curry_marino, snell_marino, path4;

	/* Buildings */
	@FXML
	Circle snell, curry, marino;

	/* Button - Start */
	public void calculateDistance() {
		/* Calculate distance */
//		Graph graph = createGraph();
		GraphNode snell = new GraphNode("snell");
		GraphNode hayden = new GraphNode("hayden");
		GraphNode marino = new GraphNode("marino");
		GraphNode curry = new GraphNode("curry");
		GraphNode shillman = new GraphNode("shillman");

		Graph graph1 = new Graph(new ArrayList<>(Arrays.asList(snell, hayden, marino, curry, shillman)));
		graph1.add_edge(snell, hayden, 100);
		graph1.add_edge(snell, curry, 200);
		graph1.add_edge(hayden, curry, 400);
		graph1.add_edge(hayden, shillman, 600);
		graph1.add_edge(hayden, marino, 100);
		graph1.add_edge(marino, shillman, 200);
		graph1.add_edge(shillman, curry, 100);
		
		HashMap<String, GraphNode> buildingCode = new HashMap<>();
		buildingCode.put("snell",snell);
		buildingCode.put("marino", marino);
		buildingCode.put("curry",curry);
		buildingCode.put("shillman", shillman);
		buildingCode.put("hayden", hayden);
		

		int dist = Graph.getDistance(graph1, buildingCode.get(fromPos), buildingCode.get(toPos));

		/* Set distance and time labels */
		distance.setText(dist + " mi");
		time.setText(6 + " min");

		/* Change path color */
		String[] paths = new String[] { "curry", "marino", "snell" };
		for (int i = 0; i < paths.length - 1; i++) {
			String current_path = paths[i] + "_" + paths[i + 1];
			colorPath(current_path);
			current_path = paths[i + 1] + "_" + paths[i];
			colorPath(current_path);
		}

	}

	/* Create map */
//	public Graph createGraph() {
//		
//		
//		return graph1;
//	}

	/* Color path */
	private void colorPath(String path) {
		if (snell_curry.getId().equals(path))
			snell_curry.setFill(PATH_FOUND);
		if (snell_marino.getId().equals(path))
			snell_marino.setFill(PATH_FOUND);
		if (curry_marino.getId().equals(path))
			curry_marino.setFill(PATH_FOUND);
		if (path4.getId().equals(path))
			path4.setFill(PATH_FOUND);
	}

	/* Button - Reset */
	public void reset() {
		/* Clear contents */
		fromPos = null;
		from.setText(fromPos);
		toPos = null;
		to.setText(toPos);
		distance.setText(null);
		time.setText(null);
		selectedBuildings = 0;

		/* Reset path color */
		snell_curry.setFill(PATH_ORIGIN);
		snell_marino.setFill(PATH_ORIGIN);
		curry_marino.setFill(PATH_ORIGIN);
		path4.setFill(PATH_ORIGIN);
	}

	/* Select buildings */
	public void selectSnell() {
		selectBuilding(snell.getId());
	}

	public void selectCurry() {
		selectBuilding(curry.getId());
	}

	public void selectMarino() {
		selectBuilding(marino.getId());
	}

	private void selectBuilding(String building) {
		if (selectedBuildings == 0) {
			/* Set from position */
			fromPos = building;
			from.setText(fromPos);

			selectedBuildings++;
		} else { // selectedBuildings == 1
			/* Set to position */
			toPos = building;
			to.setText(toPos);

			selectedBuildings--;
		}
	}

}
