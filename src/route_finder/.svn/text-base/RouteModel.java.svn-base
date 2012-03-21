package route_finder;
import java.util.*;

import graph.*;
/**
 * RouteModel provides access to Route and Building data, and generates the least
 * cost path between any two Buildings.
 * For example, it can return a list of buildings present on campus, and also
 * find the shortest route between any two buildings.
 * @author Janelle Van Hofwegen
 */
/*
 * Abstraction Function:
 * campusGraph -> graph containing all known endPoints across campus, and the
 * paths between them. 
 * 			 nodes of graph -> EndPoints on campus
 * 			 edges of graph -> Paths between EndPoints on campus
 * 
 * buildings -> list of all buildings present on campus
 * 
 * Representation Invariant:
 * campusGraph != null
 * buildings != null
 * buildings is a list of all buildings present as EndPoints in campusGraph
 * 
 */
public class RouteModel {
	private final DirectedGraph<EndPoint> campusGraph;
	private final Set<Building> buildings;
	/**
	 * Constructs a new RouteManager that provides access to Route & Building data,
	 * and which generates least cost paths (aka Routes) between buildings across
	 * the University of Washington campus.
	 */
	public RouteModel(){
		// use parser to fill list of EndPoints and Edges		
		List<EndPoint> endPoints = new ArrayList<EndPoint>();
		List<Edge> paths = new ArrayList<Edge>();
		CampusDataParser.parsePathsData(paths, endPoints);
		
		// call parser to get buildings list, add to endPoints
		buildings = CampusDataParser.parseBuildingData();
		assert buildings != null;
		endPoints.addAll(buildings);
		
		// initialize graph
		campusGraph = new DirectedGraph<EndPoint>(endPoints, paths);
		
		checkRep();
	}
	
	/**
	 * Constructs a new RouteManager that can generate least cost paths (aka Routes) between 
	 * the Buildings in buildings, based on the given endPoint, paths, and buildings data.
	 * @param endPoints: list of EndPoint to be used in generating Routes
	 * @param paths: list of Edges to be used in generating Routes
	 * @param buildings: list of Buildings to be used in generating Routes
	 */
	// Constructor isn't useful for Route Finder Application, but was necessary
	// for testing purposes
	public RouteModel(List<EndPoint> endPoints, List<Edge> paths, Set<Building> buildings){
		endPoints.addAll(buildings);
		this.buildings = buildings;
		campusGraph = new DirectedGraph<EndPoint>(endPoints, paths);
	}
	
	/**
	 * Finds and returns the least cost path between any two buildings
	 * @param startBuilding: short name of the starting building
	 * @param destBuilding: short name of the destination building
	 * @requires: both buildings are present on campus (in other words, there 
	 * is a building on campus with that short name)
	 * @throws IllegalArgumentException if either building is not found on Campus. The
	 * exception's String is the name of the (first) building that wasn't found
	 * @returns null if a route is not found
	 * @returns a Route with no edges if the startBuilding and destBuliding are the same.
	 * In this case, the returned Route's start and end EndPoints are that Building.
	 */
	public Route findLeastCostPath(String startBuilding, String destBuilding){
		Building start = getBuilding(startBuilding);
		Building dest =  getBuilding(destBuilding);
		if(start == null || dest == null){
			throw new IllegalArgumentException();
		}

		PriorityQueue<Route> active = new PriorityQueue<Route>();
		// initializes active to have one Route representing a single node (no actual edges in route)
		active.add(new Route(start));	
		Set<EndPoint> finished = new HashSet<EndPoint>();
		// MincostPaths holds minimum route known thus far from start (S) to any given node
		// MincostPaths is updated as we find new paths, or as we find better routes
		Map<EndPoint, Route> minCostPaths = new HashMap<EndPoint, Route>(); // provides fast access
		minCostPaths.put(start, new Route(start));
		while(!active.isEmpty()){
			Route queueMinPath = active.poll();
			EndPoint queueMin = queueMinPath.getEnd();
			assert queueMinPath.getStart().equals(start);
			if ((queueMin).equals(dest)) { // if QueuMin equals dest, we're done!
				return queueMinPath;
			}
			for(Edge p: campusGraph.edgesOf(queueMin)){
				EndPoint nbr = (EndPoint) p.getTarget();
				if (!finished.contains(nbr)){
					// check if we don't have a path to this node yet (no key), or if this path is
					// actually less costly than the existing path we know
					if(!minCostPaths.containsKey(nbr) || minCostPaths.get(nbr).getCost() > 
					    minCostPaths.get(queueMin).getCost() + ((CampusEdge) p).getDistance()){	
						LinkedList<CampusEdge> alt = 
							new LinkedList<CampusEdge>(minCostPaths.get(queueMin).getPaths());
						Route oldRoute = minCostPaths.get(nbr);
						alt.add((CampusEdge) p);
						Route newRoute = new Route(alt);
						minCostPaths.put(nbr, newRoute); // update database of minCostPaths
						// if active contains old route to nbr, remove and insert new route
						// because we have found a cheaper route to nbr
						if(active.contains(oldRoute)){
							boolean removedOldRoute = active.remove(oldRoute);
							assert removedOldRoute;
						}
						active.add(newRoute); // add cheaper route to PQ
					}		
				}
			}
			finished.add(queueMin);
		}
		return null;
	}

	/**
	 * Returns the Building object associated with this short name.
	 * Returns null if the building is not found.
	 */
	public Building getBuilding(String name){
		for(Building b : buildings){
			if(b.shortName.equals(name) || b.longName.equals(name)){
				return b;
			}
		}
		return null;
	}
	
	/**
	 * Returns whether there is a building with the given short name on campus.
	 * @return true if there is a building on campus with the given shortName
	 * @param shortName: the short name of the building being searched for
	 */
	public boolean containsBuilding(String shortName){
		for(Building b : buildings){
			if(b.shortName.equals(shortName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a set of the Buildings present on campus
	 * @return set of buildings present on campus
	 */
	public Set<Building> getBuildings(){
		return buildings;
	}
	
	/**
	 *  Throws an exception if the representation invariant is violated.
	 */
	private void checkRep() {
		assert campusGraph != null;
		assert buildings != null;
		// check that all Buildings in buildings are present in campusGraph
		// compares based on coordinates (sufficient because duplicate endPoints
		// are not allowed in graph, and endPoints & buildings are compared for
		// equality based on coordinates)
		Set<EndPoint> endPoints = campusGraph.nodeSet();
		for(Building b: buildings){
			if(!endPoints.contains(b)){
				throw new IllegalStateException();
			}
		}
	}

	public Route findLeastCostPathLongName(String startBuilding,
			String endBuilding) {
		return findLeastCostPath(longtoShort(startBuilding), longtoShort(endBuilding));
	}
	
	private String longtoShort(String longName){
		for(Building b : buildings){
			if(b.longName.equals(longName)){
				return b.shortName();
			}
		}
		return null;
	}
}
