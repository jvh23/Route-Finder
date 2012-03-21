package route_finder;

import java.util.LinkedList;
import java.util.List;
/**
 * Route represents a series of linked walking paths.
 * @author Janelle Van Hofwegen
 *
 */
/*
 * Abstraction Function:
 * List<CampusEdge> route represents a series of linked paths/edges (CampusEdges) that represent a
 * route from one EndPoint to another EndPoint.
 * The beginning of the route is the first edge in the list, and the end of the route is the last
 * edge in the list.
 * 
 * Route also contains a reference to the start and end EndPoints of the path:
 * start = the first EndPoint of the path
 * end = the last EndPoint of the path
 * 
 * Each edge in the route has a source and target EndPoint.  Because edges in the route are linked,
 * for any edge e in the middle of the list route, the element to e's left (l) will have a target EndPoint
 * that is the same as e's source EndPoint. Similarly, the element to e's right (r) will have a source
 * EndPoint that is the same as e's target EndPoint. For example:
 * 
 *   l = list.get(0)          e = list.get(1)          r = list.get(2)
 *   +----+                   +----+                   +----+
 *   | l  |      ---->        | e  |      ---->        | r  |
 *   +----+                   +----+                   +----+
 *         l.target == e.source     e.target ==  r.source
 *         
 *         
 * Representation Invariant:
 * route != null
 * start != null
 * end != null
 * 
 * list.get(0).getSource().equals(start)
 * (In other words, the first edge's source EndPoint is equal to start)
 * 
 * list.get(list.size()-1).getTarget.equals(end)
 * (In other words, the last edge's target EndPoint is equal to end)
 * 
 * For each edge e in route, the edge to to the left (l) of e has a target EndPoint that
 * is equal to e's source EndPoint, and the edge to the right of e has a source EndPoint
 * that is equal to e's target EndPoint.
 */
public class Route implements Comparable<Route> {
	private final List<CampusEdge> route; // list of paths that represent one route
	private final EndPoint start; // EndPoint that is the start of route
	private final EndPoint end; // EndPoint that is the end of route
	
	/**
	 * Creates a route based on given list of CampusEdges. First edge in given list
	 * becomes the first edge in the route, second edge in the given list becomes the second
	 * edge in the route, etc.
	 * @param route: list of paths from which a route will be constructed
	 * @requires: route != null
	 * @requires: the CampusEdges in route are linked (that is, for each edge e in route, the edge
	 * to to the left (l) of e in the list has a target EndPoint that is equal to e's source EndPoint,
	 * and the edge to the right of e in list has a source EndPoint that is equal to e's target EndPoint.
	 * @throws IllegalArgumentException if route == null
	 */
	public Route(List<CampusEdge> route){
		this.route = route;
		if(route == null){
			throw new IllegalArgumentException("argument should not be null");
		}
		start = route.get(0).getSource();
		end = route.get(route.size() - 1).getTarget();
		checkRep();
	}
	
	/**
	 * Constructs a route with no edges, and just one EndPoint. 
	 * Start and end are the same given EndPoint.
	 * @param path: EndPoint that will represent this route
	 * @requires path != null
	 * @throws IllegalArgumentException if path == null
	 */
	public Route(EndPoint path){
		route = new LinkedList<CampusEdge>();
		if(path == null){
			throw new IllegalArgumentException("argument should not be null");
		}
		start = path;
		end = path;
		checkRep();
	}
	
	/**
	 * Returns the list of this route's paths (the sequence of paths that form this route)
	 * If this route contains no edges, returns an empty list.
	 * @return the list of this route's paths (the sequence of paths that form this route)
	 */
	public List<CampusEdge> getPaths(){
		return route;
	}
	
	/**
	 * Compares this Route to the given route
	 * @return 0 if the cost of this route is equal to the cost of the other Route, a value
	 * less than 0 if this route's cost is less than the cost of the other route, and a value
	 * greater than 0 if this route's cost is greater than the cost of the other route
	 */
	public int compareTo(Route other){
		if(getCost() > other.getCost()){
			return 1;
		} else if(getCost() < other.getCost()){
			return -1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Returns the cost of this Route, which is the sum of the individual costs of all
	 * edges of this Route.  Returns zero if this route contains no edges.
	 * @return cost of this Route
	 */
	public double getCost(){
		double cost = 0.0;
		for(CampusEdge p : route){
			cost += p.getDistance();
		}
		return cost;
	}
	
	/**
	 * Returns the starting EndPoint of this Route
	 * @return starting EndPoint of this Route
	 */
	public EndPoint getStart(){
		return start;
	}
	
	/**
	 * Returns the ending EndPoint of this Route
	 * @return ending EndPoint of this Route
	 */
	public EndPoint getEnd(){
		return end;
	}
	
	/**
	 * Returns a String representation of this Route
	 */
	@Override
	public String toString() {
		return "Route [start=" + start + ", end=" + end
				+ ", # of edges=" + route.size() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((route == null) ? 0 : route.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	/**
	 * Returns whether this Route is equal to the given Object
	 * @return true if given Object is a Route whose series of linked
	 * edges is equal to this Route's series of linked edges
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Route))
			return false;
		Route other = (Route) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	
	/**
	 *  Throws an exception if the representation invariant is violated.
	 */
	private void checkRep(){
		assert route != null;
		assert start != null;
		assert end != null;
		
		// start and end should match route
		if(route.size() > 0){
			assert start == route.get(0).getSource();
			assert end == route.get(route.size() - 1).getTarget();
		}
		
		// check that edges are linked (only a concern if at least 2 edges are in route)
		for(int i = 0 ; i < route.size() - 2 ; i++){
			assert route.get(i).getTarget().equals(route.get(i + 1).getSource());
		} 
	}
}