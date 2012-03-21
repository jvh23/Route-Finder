package route_finder.tests;
import static org.junit.Assert.*;

import graph.Edge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import route_finder.*;

import org.junit.Test;

public class RouteModelTest {
	
	public RouteModel model0;
	public RouteModel model1;
	public RouteModel model2;
	public RouteModel model4;
	public RouteModel model5;
	
	// test models that hold different graphs (these correspond to above fields):
	// model0: graph with zero buildings, zero edges
	// model1: graph with one building, zero edges
	// model2: graph with two buildings, multiple edges
	// model4: graph with four buildings, multiple edges
	
	EndPoint b1 = new Building(1, 1, "CSE", "Computer Science Building");
	EndPoint b2 = new Building(2, 1, "MGH", "Mary Gates Hall");
	EndPoint b3 = new Building(1, 2, "SUZ", "Suzallo");
	EndPoint b4 = new Building(2 ,2, "DEN", "Denny Hall");
	EndPoint b5 = new Building(1, 3, "PAC", "Paccar Hall");
	
	CampusEdge c1 = new CampusEdge(b1, b2, 1);
	CampusEdge c2 = new CampusEdge(b1, b3, 2);
	CampusEdge c3 = new CampusEdge(b1, b4, 7);
	CampusEdge c4 = new CampusEdge(b2, b4, 2);
	CampusEdge c5 = new CampusEdge(b4, b5, 1);
	CampusEdge c6 = new CampusEdge(b3, b5, 12);
	
	public RouteModelTest(){
		List<EndPoint> endPoints = new ArrayList<EndPoint>(); // accumulates endPoints
		Set<Building> buildings = new HashSet<Building>(); // accumulates buildings
		List<Edge> edges = new ArrayList<Edge>(); // accumulates CampusEdges
		model0 = new RouteModel(new ArrayList<EndPoint>(), new ArrayList<Edge>(),
				new HashSet<Building>());
		endPoints.add(b1);
		buildings.add((Building) b1);
		model1 = new RouteModel(endPoints, new ArrayList<Edge>(), buildings);
		endPoints.add(b2);
		buildings.add((Building) b2);
		edges.add(c1);
		model2 = new RouteModel(endPoints, edges, buildings);
		endPoints.add(b3);
		buildings.add((Building) b3);
		edges.add(c2);
		endPoints.add(b4);
		buildings.add((Building) b4);
		edges.add(c3);
		edges.add(c4);
		model4 = new RouteModel(endPoints, edges, buildings);
		endPoints.add(b5);
		buildings.add((Building) b5);
		edges.add(c5);
		edges.add(c6);
		model5 = new RouteModel(endPoints, edges, buildings);
	} 
	
	// **************************************************************
	// Test findLeastCostPath()
	//***************************************************************
	
	// least cost path is one edge away (least cost path is also shortest)
	@Test
	public void testFindLeastCostPathOneEdge(){		
		List<CampusEdge> m2 = new ArrayList<CampusEdge>();
		m2.add(c1);
		Route r2 = new Route(m2);
		assertEquals(model2.findLeastCostPath("CSE", "MGH"), r2);
	}
	
	// least cost path is 2 edges, a shorter (1 edge) path exist in graph (but is more costly)
	@Test
	public void testFindLeastCostPathTwoEdges(){
		List<CampusEdge> m3 = new ArrayList<CampusEdge>();
		m3.add(c1);
		m3.add(c4);
		Route r3 = new Route(m3);
		assertEquals(model4.findLeastCostPath("CSE", "DEN"), r3);
	}
	
	// least cost path is 3 edges, a shorter (2 edge) path exist in graph (but is more costly)
	@Test
	public void testFindLeastCostPathThreeEdges(){
		List<CampusEdge> m = new ArrayList<CampusEdge>();
		m.add(c1);
		m.add(c4);
		m.add(c5);
		Route r = new Route(m);
		assertEquals(model5.findLeastCostPath("CSE", "PAC"), r);
	}
	
	// path does not exist between the two buildings (should return null)
	@Test
	public void testFindLeastCostPathWhenPathDNE(){
		assertNull(model4.findLeastCostPath("MGH", "SUZ"));
	}
	
	// path to self (should return a Route with no edges.  The start and end EndPoints 
	// of the Route should be the same).
	@Test
	public void testFindLeastCostPathtoSelf(){
		Route r4 = new Route(b2);
		assertEquals(model4.findLeastCostPath("MGH", "MGH"), r4);
	}
		
	// an exception should be thrown when the client passes a (short) building name
	// which doesn't exist on campus
	@Test(expected=IllegalArgumentException.class)
	public void testExceptionWhenFirstBuildingDoesntExist(){
		model4.findLeastCostPath("MGH", "PAA");
	}
	
	// an exception should be thrown when the client passes a (short) building name
	// which doesn't exist on campus
	@Test(expected=IllegalArgumentException.class)
	public void testExceptionWhenSecondBuildingDoesntExist(){
		model4.findLeastCostPath("MGHAFD", "PAA");
	}
	
	// **************************************************************
	// Test containsBuilding()
	//***************************************************************
	
	@Test
	public void testContainsBuilding(){
		assertTrue(model1.containsBuilding("CSE"));
		assertTrue(model4.containsBuilding("DEN"));
		assertTrue(model4.containsBuilding("SUZ"));
	}
}
