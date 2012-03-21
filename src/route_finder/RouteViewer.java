package route_finder;

import java.util.Collection;

/**
 * RouteViewer formats the display of a user interface.
 * @author Janelle Van Hofwegen
 *
 */
public class RouteViewer {

	/**
	 * Prints the given Route, one CampusEdge per line.
	 * Format is : Walk <feet> feet <direction> to (<First Coordinate>, <Second Coordinate>)
	 * @param r the Route to be printed
	 */
	public void displayRoute(Route r) {
		for(CampusEdge e : r.getPaths()){
			System.out.printf("    Walk %.1f feet " + getDirection(e) + " to (%.2f,%.2f)", 
					e.getDistance(), e.getTarget().getX(), e.getTarget().getY());
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Prints the given collectoin of Buildings, one building per line.
	 * Format is : <Building short name> : <Building long name>
	 * @param buildings : buildings to be printed
	 */
	public void displayBuildings(Collection<Building> buildings) {
		System.out.println("Buildings:");
		for(Building b : buildings){
			System.out.print("    ");
			System.out.print(b.shortName() + ": " + b.longName());
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Returns the cardinal direction that the given CampusEdge points.
	 * @param e : edge for which a direction is being computed
	 * @return String representing the cardinal direction this edge is pointing
	 * (N, S, E, W, NE, NW, SE, SW).
	 */
	private String getDirection(CampusEdge e){
		double x1 = e.getSource().getX();
		double y1 = e.getSource().getY();
		double x2 = e.getTarget().getX();
		double y2 = e.getTarget().getY();
		
		String direction = "";
		if(y2 < y1){
			direction+="N";
		} else{
			direction+="S";
		}
		
		if(x2 < x1){
			direction+="W";
		} else if(x2 > x1){
			direction+="E";
		}
		return direction;
	}
	/**
	 * Prints out an "Unknown Building" error message
	 * @param building : name of the unknown building
	 */
	public void displayNotFoundMessage(String building) {
		System.out.println("Unknown building: " + building);
	}

	/**
	 * Prints out the menu.
	 */
	public void displayMenu() {
		System.out.println("Menu:");
		System.out.println("    r to find a route");
		System.out.println("    b to see a list of all buildings");
		System.out.println("    q to quit");
		System.out.println();
	}
}