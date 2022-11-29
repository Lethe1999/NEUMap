package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import map_algorithm.NEUMap;

public class MapController {

	/* Path changes */
	private static final Color PATH_ORIGIN = Color.web("#7079f5");
	private static final Color PATH_FOUND = Color.web("#ff00aa");
	private static final double PATH_ORIGIN_WIDTH = 3.0;
	private static final double PATH_COLOR_WIDTH = 5.0;
	private static final double PATH_ORIGIN_OPACITY = 0.6;
	private static final double PATH_COLOR_OPACITY = 1.0;

	/* From and to positions */
	@FXML
	TextField from_text, to_text;
	private String from, to;
	private int selectedBuildings = 0; // To help check from and to positions

	/* Results */
	@FXML
	Text distance, time;

	/* Paths */
	@FXML
	Polyline sec_snell, sec_forsyth, hayden_snell, richards_hayden, marino_cabot, cabot_richards, cabot_forsyth,
			forsyth_churchill, snell_churchill, churchill_hayden, snell_curry, ell_curry, richards_ell, hayden_ell,
			marino_forsyth, marino_speare, speare_dodge, dodge_mugar, dodge_hastings, speare_hastings,
			mugar_eastvillage, eastvillage_gainsborough, mugar_hastings, eastvillage_cullinane, cullinane_hurtig,
			hurtig_robinson, robinsonquad_robinson, mugar_robinson, dodge_robinsonquad, mugar_robinsonquad,
			curry_robinsonquad, robinsonquad_columbus, snell_columbus, dodge_ell, ell_mugar, columbus_hurtig,
			cullinane_robinsonquad, speare_cabot, sec_columbus;
	private Polyline[] pathList;

	/* Buildings */
	@FXML
	Rectangle sec, snell, curry, ell, richards, hayden, cabot, forsyth, churchill, marino, speare, columbus, robinson,
			hurtig, mugar, cullinane, gainsborough, eastvillage, hastings, dodge;
	// private Rectangle[] buildings;

	/* Weather and vehicle */
	@FXML
	ComboBox<String> weather, vehicle;
	private ObservableList<String> weatherOptions = FXCollections.observableArrayList("Sunny", "Snowy", "Rainy");
	private ObservableList<String> vehiclesOptions = FXCollections.observableArrayList("Walk", "Bicycle");

	@FXML
	public void initialize() {
		weather.setItems(weatherOptions);
		vehicle.setItems(vehiclesOptions);
	}

	/* Create a NEU Map */
	private NEUMap neuMap = new NEUMap();

	/* Button - Start */
	public void calculateDistance() {
		/* Calculate distance */
		int distance_text = neuMap.getDistance(from, to);
		String time_text = String.format("%.2f", getTime(distance_text, weather.getValue(), vehicle.getValue()));

		/* Set distance and time labels */
		distance.setText(distance_text + " m");
		time.setText(time_text + " min");

		/* Create paths */
		pathList = new Polyline[] { sec_snell, sec_forsyth, hayden_snell, richards_hayden, marino_cabot, cabot_richards,
				cabot_forsyth, forsyth_churchill, snell_churchill, churchill_hayden, snell_curry, ell_curry,
				richards_ell, hayden_ell, marino_forsyth, marino_speare, speare_dodge, dodge_mugar, dodge_hastings,
				speare_hastings, mugar_eastvillage, eastvillage_gainsborough, mugar_hastings, eastvillage_cullinane,
				cullinane_hurtig, hurtig_robinson, robinsonquad_robinson, mugar_robinson, dodge_robinsonquad,
				mugar_robinsonquad, curry_robinsonquad, robinsonquad_columbus, snell_columbus, dodge_ell, ell_mugar,
				columbus_hurtig, cullinane_robinsonquad, speare_cabot, sec_columbus };

		/* Change path color */
		ArrayList<String> shortestPath = neuMap.getPath(from, to);
		for (int i = 0; i < shortestPath.size() - 1; i++) {
			String current_path = shortestPath.get(i) + "_" + shortestPath.get(i + 1);
			colorPath(current_path);
			current_path = shortestPath.get(i + 1) + "_" + shortestPath.get(i);
			colorPath(current_path);
		}
	}

	/* Get time */
	private double getTime(int distance, String weather_mode, String vehicle_mode) {
		double time = 0;

		/* Vehicle */
		if (vehicle_mode == null || vehicle_mode.equals("Walk")) {
			time = (double) distance / 60 / 1.4;
		} else {
			time = (double) distance / 60 / 5.0;
		}
		System.out.println(time);
		/* Weather */
		if (weather_mode == null || weather_mode.equals("Sunny")) {
			time *= 1;
		} else if (weather_mode.equals("Rainy")) {
			time *= 1.5;
			System.out.println(time);
		} else {
			time *= 3;
			System.out.println(time);
		}

		return time;
	}

	/* Color path */
	private void colorPath(String path_text) {
		for (Polyline path : pathList) {
			if (path.getId().equals(path_text)) {
				path.setStroke(PATH_FOUND);
				path.setStrokeWidth(PATH_COLOR_WIDTH);
				path.setOpacity(PATH_COLOR_OPACITY);
			}
		}
	}

	/* Button - Reset */
	public void reset() {
		/* Clear contents */
		from = null;
		from_text.setText(from);
		to = null;
		to_text.setText(to);
		distance.setText(null);
		time.setText(null);
		selectedBuildings = 0;

		/* Reset path color */
		for (Polyline path : pathList) {
			path.setStroke(PATH_ORIGIN);
			path.setStrokeWidth(PATH_ORIGIN_WIDTH);
			path.setOpacity(PATH_ORIGIN_OPACITY);
		}
	}

	/* Select buildings */
	public void select_sec() {
		selectBuilding(sec.getId());
	}

	public void select_snell() {
		selectBuilding(snell.getId());
	}

	public void select_curry() {
		selectBuilding(curry.getId());
	}

	public void select_richards() {
		selectBuilding(richards.getId());
	}

	public void select_ell() {
		selectBuilding(ell.getId());
	}

	public void select_hayden() {
		selectBuilding(hayden.getId());
	}

	public void select_cabot() {
		selectBuilding(cabot.getId());
	}

	public void select_forsyth() {
		selectBuilding(forsyth.getId());
	}

	public void select_churchill() {
		selectBuilding(churchill.getId());
	}

	public void select_marino() {
		selectBuilding(marino.getId());
	}

	public void select_columbus() {
		selectBuilding(columbus.getId());
	}

	public void select_speare() {
		selectBuilding(speare.getId());
	}

	public void select_robinson() {
		selectBuilding(robinson.getId());
	}

	public void select_hurtig() {
		selectBuilding(hurtig.getId());
	}

	public void select_mugar() {
		selectBuilding(mugar.getId());
	}

	public void select_cullinane() {
		selectBuilding(cullinane.getId());
	}

	public void select_gainsborough() {
		selectBuilding(gainsborough.getId());
	}

	public void select_eastvillage() {
		selectBuilding(eastvillage.getId());
	}

	public void select_hastings() {
		selectBuilding(hastings.getId());
	}

	public void select_dodge() {
		selectBuilding(dodge.getId());
	}

	private void selectBuilding(String building) {
		if (selectedBuildings == 0) {
			/* Set from position */
			from = building;
			from_text.setText(from);

			selectedBuildings++;
		} else { // selectedBuildings == 1
			/* Set to position */
			to = building;
			to_text.setText(to);

			selectedBuildings--;
		}
	}

	/**
	 * 
	 * Main Controller
	 * 
	 */
	@FXML
	Circle btnClose;
	@FXML
	Button aboutUs, tunnelBar, buildingBar;
	@FXML
	Button aboutUs1, tunnelBar1, buildingBar1;
	@FXML
	Button aboutUs2, tunnelBar2, buildingBar2;
	@FXML
	Button HOME, HOME1, HOME2;
	@FXML
	GridPane buildingMap, tunnelMap;
	@FXML
	Pane mainPage;
	@FXML
	HBox hbox;

	@FXML
	private void handleMouseEvent(MouseEvent event) {
		if (event.getSource() == btnClose) {
			System.exit(0);
		}
	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		if (event.getSource().equals(buildingBar)) {
			buildingMap.toFront();
		}
		if (event.getSource().equals(tunnelBar)) {
			System.out.println("click");
			tunnelMap.toFront();
		}
		if (event.getSource().equals(buildingBar1)) {
			buildingMap.toFront();
		}
		if (event.getSource().equals(buildingBar2)) {
			buildingMap.toFront();
		}
		if (event.getSource().equals(tunnelBar1)) {
			tunnelMap.toFront();
		}
		if (event.getSource().equals(HOME)) {
			mainPage.toFront();
		}
		if (event.getSource().equals(HOME1)) {
			mainPage.toFront();
		}
		if (event.getSource().equals(tunnelBar2)) {
			tunnelMap.toFront();
		}
		if (event.getSource().equals(HOME2)) {
			mainPage.toFront();
		}
		if (event.getSource() != null) {
			hbox.toFront();
		}

	}

}
