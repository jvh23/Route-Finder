package route_finder.tests;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import route_finder.*;

public class RouteTest {
	
	// Edges to use in forming Routes
	CampusEdge c1 = new CampusEdge(new EndPoint(1,1), new EndPoint(2.4,2), 1.0);
	CampusEdge c2 = new CampusEdge(new EndPoint(2.4, 2), new EndPoint(3,4), 2.0); 
	CampusEdge c3 = new CampusEdge(new EndPoint(3,4), new EndPoint(5,5), 2.0); 
	CampusEdge c4 = new CampusEdge(new EndPoint(5,5), new EndPoint(7.7,3), 2.0);
	CampusEdge c5 = new CampusEdge(new EndPoint(7.7, 3), new EndPoint(.993, 3.2), 43.2);
	CampusEdge c6 = new CampusEdge(new EndPoint(.993, 3.2), new EndPoint(8,2), 1.0);
	
	// Test Routes
	Route r0 = new Route(new EndPoint(1,1)); // Route with just one path, no edges
	Route r1; // Route with one edge
	Route r2; // Route with two edges
	Route r3; // Route with three edges
	Route r6; // Route with six edges
	
	// Lists of Edges (correspond to above Test Routes)
	List<CampusEdge> l1;
	List<CampusEdge> l2;
	List<CampusEdge> l3;
	List<CampusEdge> l6;
	
	public RouteTest(){
		l1 = new ArrayList<CampusEdge>();
		l2 = new ArrayList<CampusEdge>();
		l3 = new ArrayList<CampusEdge>();
		l6 = new ArrayList<CampusEdge>();
		
		l1.add(c1);
		l2.add(c1);
		l2.add(c2);
		l3.add(c1);
		l3.add(c2);
		l3.add(c3);
		l6.add(c1);
		l6.add(c2);
		l6.add(c3);
		l6.add(c4);
		l6.add(c5);
		l6.add(c6);
		
		r1 = new Route(l1);
		r2 = new Route(l2);
		r3 = new Route(l3);
		r6 = new Route(l6);
	}
	
	// constructor argument == null
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorExceptionListIsNull() {
		List<CampusEdge> route = null;
		new Route(route);
	}
	
	// constructor argument == null
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorExceptionEndPointIsNull() {
		EndPoint p = null;
		new Route(p);
	}
	
	// pass constructor a non-legal list of edges (edges aren't linked)
	@Test
	public void testConstructorExceptionNonLinkedEdges(){
		List<CampusEdge> l = new ArrayList<CampusEdge>();
		l.add(c1);
		l.add(c3);
		new Route(l);
	}
	
	@Test
	public void testGetPaths(){
		// paths that should be equal
		assertEquals(r0.getPaths(), new ArrayList<CampusEdge>()); // Route with no edges
		assertEquals(r1.getPaths(), l1);
		assertEquals(r2.getPaths(), l2);
		assertEquals(r3.getPaths(), l3);
		assertEquals(r6.getPaths(), l6);
	}
	
	@Test
	public void testCompareTo(){
		assertTrue(r0.compareTo(r1) < 0);
		assertTrue(r1.compareTo(r1) == 0);
		assertTrue(r6.compareTo(r6) == 0);
		assertTrue(r1.compareTo(r2) < 0);
		assertTrue(r2.compareTo(r1) > 0);
		assertTrue(r1.compareTo(r3) < 0);
		assertTrue(r6.compareTo(r2) > 0);
	}
	
	@Test
	public void testGetCost(){
		assertEquals(r0.getCost(), 0, .000001);
		assertEquals(r1.getCost(), 1.0, .000001);
		assertEquals(r2.getCost(), 3.0, .000001);
		assertEquals(r3.getCost(), 5.0, .000001);
		assertEquals(r6.getCost(), 51.2, .000001);
	}
	
	@Test
	public void testGetStart(){
		assertEquals(r0.getStart(), new EndPoint(1,1));
		assertEquals(r1.getStart(), new EndPoint(1,1));
		assertEquals(r2.getStart(), new EndPoint(1,1));
		assertEquals(r3.getStart(), new EndPoint(1,1));
		assertEquals(r6.getStart(), new EndPoint(1,1));
	}
	
	@Test
	public void testGetEnd(){
		assertEquals(r0.getEnd(), new EndPoint(1,1));
		assertEquals(r1.getEnd(), new EndPoint(2.4,2));
		assertEquals(r2.getEnd(), new EndPoint(3,4));
		assertEquals(r3.getEnd(), new EndPoint(5,5));
		assertEquals(r6.getEnd(), new EndPoint(8,2));
	}
	
	@Test
	public void testEquals(){
		assertEquals(r0, r0);
		assertEquals(r6, r6);
		assertEquals(r0, new Route(new EndPoint(1,1)));
		assertEquals(r6, new Route(l6));
	}
}
