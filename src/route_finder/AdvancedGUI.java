package route_finder;

import java.awt.*;
import java.awt.event.*;
import java.util.Set;
import javax.swing.*;

/**
 * AdvancedGUI generates and displays a graphical user interface for the Route
 * Finder application.
 * 
 * @author Janelle Van Hofwegen
 * 
 */
public class AdvancedGUI {
	// The original map image is 4330 x 2964 pixels. SCALE_FACTOR scales down
	// the map by the given factor.
	private static final int SCALE_FACTOR = 5;
	private static final String MAP_LOCATION = "campus_map.jpg";
	private static final int MAP_WIDTH = 4330;
	private static final int MAP_HEIGHT = 2964;
	private static final int CIRCLE_SIZE = (int) (MAP_WIDTH * .04 / SCALE_FACTOR);
	private static final String START_PROMPT = "Choose a Starting Location...";
	private static final String END_PROMPT = "Choose an Ending Location...";

	private RouteModel model; // manages data
	private JPanel canvas; // panel where graphics are drawn
	private String startBuilding; // building currently selected in the
									// startDropDown JComboBox
	private String endBuilding; // building currently selected in the
								// endDropDown JComboBox
	private JComboBox<String> startDropDown;
	private JComboBox<String> endDropDown;
	private boolean find; // true if the user has clicked the "find" button and
							// we haven't repainted the canvas yet
	
	/**
	 * Constructs the GUI and displays it on the screen.
	 * 
	 * @param model
	 *            : RouteModel which handles the data for this GUI
	 */
	public AdvancedGUI(RouteModel model) {
		// Model is used to manage data & compute paths
		this.model = model;

		// Create and set up the window
		JFrame frame = new JFrame("Route Finder"); // frame that holds all
													// containers
		frame.setSize(MAP_WIDTH / SCALE_FACTOR, MAP_HEIGHT / SCALE_FACTOR);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create "Find Route" and "Reset" Buttons
		JButton findRouteButton = findRouteButton();
		JButton resetButton = resetButton();

		// Set the main pane's LayoutManager to BoxLayout
		// Pane is the content pane of the main JFrame
		JComponent pane = (JComponent) frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		// Top pane of Box Layout (contains two ComboBoxes)
		JPanel top = new JPanel();
		startDropDown = initializeComboBox(START_PROMPT);
		top.add(startDropDown);
		endDropDown = initializeComboBox(END_PROMPT);
		top.add(endDropDown);
		pane.add(top);

		// Middle pane of Box Layout (contains two buttons)
		JPanel middle = new JPanel();
		middle.add(findRouteButton);
		middle.add(resetButton);
		pane.add(middle);

		// Bottom pane of Box Layout (contains map)
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(MAP_WIDTH / SCALE_FACTOR,
				MAP_HEIGHT / SCALE_FACTOR));
		pane.add(canvas);

		// Add listener to start dropdown menu:
		startDropDown.addActionListener(new ActionListener() {
			// effects: sets startBuilding to the selected item, repaints canvas
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				startBuilding = (String) cb.getSelectedItem();
				canvas.repaint();
			}
		});

		// Add listener to end dropdown menu:
		endDropDown.addActionListener(new ActionListener() {
			// effects: sets endBuilding to the selected item, repaints canvas
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				endBuilding = (String) cb.getSelectedItem();
				canvas.repaint(); // draw circle
			}
		});

		// initial buildings / prompts
		startBuilding = START_PROMPT;
		endBuilding = END_PROMPT;

		// Do this after the frame is all set up
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Creates and returns the "reset" button
	 */
	private JButton resetButton() {
		JButton resetButton = new JButton("Reset");
		// Add listener for "reset" button
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.repaint();
				startDropDown.setSelectedItem(START_PROMPT);
				endDropDown.setSelectedItem(END_PROMPT);
			}
		});
		return resetButton;
	}

	/**
	 * Creates and returns the "find route" button
	 */
	private JButton findRouteButton() {
		JButton findRouteButton = new JButton("Find Route");
		// Add listener for "find route" button
		findRouteButton.addActionListener(new ActionListener() {
			// effects : find = true, repaints canvas
			public void actionPerformed(ActionEvent e) {
				find = true;
				canvas.repaint();
			}
		});
		return findRouteButton;
	}

	/**
	 * Creates and a returns a ComboBox containing a list of all campus
	 * buildings. The first item in the comboBox is the given String prompt.
	 * 
	 * @param prompt
	 *            String that is displayed in the ComboBox initially; e.g.
	 *            "Select a Building..."
	 * @return a JComboBox<String> containing a list of all the campus buildings
	 *         as options. First option is the given String prompt.
	 */
	private JComboBox<String> initializeComboBox(String prompt) {
		Set<Building> buildings = model.getBuildings();
		String[] options = new String[buildings.size() + 1];
		options[0] = prompt;
		int i = 1;
		for (Building b : buildings) {
			options[i] = b.longName();
			i++;
		}
		JComboBox<String> cb = new JComboBox<String>(options);
		return cb;
	}

	/**
	 * Canvas is the panel holding the map. Circles and Routes are drawn on top
	 * of this panel.
	 */
	private class Canvas extends JPanel {
		/**
		 * Draws the map image, circles, and route. Always draws the map. If
		 * currently selected startBuilding is not START_PROMPT, draws a circle
		 * around the startBuilding's location on the map. If currently selected
		 * endBuilding is not END_PROMPT, draws a circle around the endBuilding
		 * location on the map. If the canvas hasn't been repainted since the
		 * last time the user clicked the "find route" button (i.e., find =
		 * true), and the selected items of both dropdown menus are buildings
		 * (instead of prompts), then a route between those two buildings will
		 * be drawn on the map.
		 * 
		 * @effects: sets find = false
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			Image img = Toolkit.getDefaultToolkit().getImage(MAP_LOCATION);
			g2d.setStroke(new BasicStroke(2));
			drawMap(g2d, img);
			if (!endBuilding.equals(END_PROMPT))
				drawCircle(g2d, endBuilding);
			if (!startBuilding.equals(START_PROMPT))
				drawCircle(g2d, startBuilding);
			// Draw a route if we haven't repainted the canvas since the last
			// time the user clicked the "find route" button (find = true).
			if (find)
				drawRoute(g2d);
			find = false; // reset find because we have repainted canvas
		}

		/**
		 * Draws the map image in its original proportions, scaled down by a
		 * factor of SCALE_FACTOR
		 * 
		 * @param g2d
		 *            : Graphics object being used to draw
		 * @param img
		 *            : Map image to draw
		 */
		private void drawMap(Graphics g2d, Image img) {
			// load map image
			g2d.drawImage(img, 0, 0, MAP_WIDTH / SCALE_FACTOR, MAP_HEIGHT
					/ SCALE_FACTOR, 0, 0, MAP_WIDTH, MAP_HEIGHT, this);
		}

		/**
		 * Draws a circle around the specified building
		 * 
		 * @param g2d
		 *            : Graphics object being used to draw
		 * @param buildling
		 *            : building to draw a circle around
		 * @requires building exists in model
		 */
		private void drawCircle(Graphics g2d, String building) {
			assert (model.containsBuilding(building));
			g2d.setColor(Color.CYAN);
			Building b = model.getBuilding(building);
			g2d.fillOval(
					(int) ((b.getX() / SCALE_FACTOR) - (CIRCLE_SIZE / 20)),
					(int) ((b.getY() / SCALE_FACTOR) - (CIRCLE_SIZE / 20)),
					(CIRCLE_SIZE / 10), CIRCLE_SIZE / 10);
			g2d.drawOval((int) ((b.getX() / SCALE_FACTOR) - (CIRCLE_SIZE / 2)),
					(int) ((b.getY() / SCALE_FACTOR) - (CIRCLE_SIZE / 2)),
					CIRCLE_SIZE, CIRCLE_SIZE);
		}
	}

	/**
	 * Draws the least cost path across the map
	 * 
	 * @param g2d
	 *            : Graphics object being used to draw
	 * @requires startBuilding & endBuilding both exist in the model
	 */
	private void drawRoute(Graphics g2d) {
		g2d.setColor(Color.RED);
		if (!startBuilding.equals(START_PROMPT)
				&& !endBuilding.equals(END_PROMPT)) {
			assert (model.containsBuilding(startBuilding));
			assert (model.containsBuilding(endBuilding));
			Route r = model.findLeastCostPathLongName(startBuilding,
					endBuilding);
			for (CampusEdge e : r.getPaths()) {
				g2d.drawLine((int) (e.getSource().getX() / SCALE_FACTOR),
						(int) (e.getSource().getY() / SCALE_FACTOR), (int) (e
								.getTarget().getX() / SCALE_FACTOR), (int) (e
								.getTarget().getY() / SCALE_FACTOR));
			}
		}
	}
}