package route_finder.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import route_finder.*;
import graph.*;

public class CampusEdgeTest {
	Edge c1 = new CampusEdge(new EndPoint(1,1), new EndPoint(2,2), 1.0);
	Edge c2 = new CampusEdge(new EndPoint(1,1), new EndPoint(2,2), 2.0); // same as e1 but different distance
	Edge c3 = new CampusEdge(new EndPoint(2,2), new EndPoint(1,1), 2.0); // similar to e2, but different direction
	Edge c4 = new CampusEdge(new EndPoint(3,3), new EndPoint(3,3), 2.0); // source and target the same
	Edge c5 = new CampusEdge(new EndPoint(23.2, 3.4), new EndPoint(.993, 3.2), 43.2);
	Edge c6 = new CampusEdge(new EndPoint(1,1), new EndPoint(2,2), 1.0);
	List<Edge> edges;
	
	// Array of Source EndPoints to test against (these correspond to sources of above edges)
	EndPoint[] sources = new EndPoint[]{
			new EndPoint(1,1),
			new EndPoint(1,1),
			new EndPoint(2,2),
			new EndPoint(3,3),
			new EndPoint(23.2, 3.4)
	};
	
	// Array of Target EndPoints to test against (these correspond to targets of above edges)
	EndPoint[] targets = new EndPoint[]{
			new EndPoint(2,2),
			new EndPoint(2,2),
			new EndPoint(1,1),
			new EndPoint(3,3),
			new EndPoint(.993, 3.2)
	};
	
	// Array of distances to test against (these correspond to distances of above edges)
	double[] distances = new double[]{1.0, 2.0, 2.0, 2.0, 43.2};
	
	public CampusEdgeTest(){
		edges = new ArrayList<Edge>();
		edges.add(c1);
		edges.add(c2);
		edges.add(c3);
		edges.add(c4);
		edges.add(c5);
	}
	
	// source == null
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorExceptionSourceIsNull() {
		new CampusEdge(null, new EndPoint(2,2), 1.0);
	}
	
	// target == null
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorExceptionTargetIsNull() {
		new CampusEdge(new EndPoint(2,2), null, 1.0);
	}
	
	// distance < 0
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorExceptionDistanceIsNegative() {
		new CampusEdge(new EndPoint(2,2), new EndPoint(1,1), -3.0);
	}
	
	@Test
	public void testGetSource(){
		for(int i = 0 ; i < edges.size(); i++){
			assertEquals(edges.get(i).getSource(), sources[i]);
		}
	}
	
	@Test
	public void testGetTarget(){
		for(int i = 0 ; i < edges.size(); i++){
			assertEquals(edges.get(i).getTarget(), targets[i]);
		}
	}
	
	@Test
	public void testGetDistance(){
		for(int i = 0 ; i < edges.size(); i++){
			assertEquals(((CampusEdge) edges.get(i)).getDistance(), distances[i], .0000000001);
		}
	}
	
	@Test
	public void testEquals(){
		// Edges that should be equal:
		assertEquals(c1, c1); // Edges should be equal to themselves
		assertEquals(c5, c5); // Edges should be equal to themselves
		assertEquals(c1, c6); // these have the same source, target distance
		
		//Edges that shouldn't be equal:
		assertFalse(c1.equals(c5)); // different source, target, distance
		assertFalse(c5.equals(c1));
		assertFalse(c1.equals(c2)); // same source, target, but different distance
		assertFalse(c2.equals(c1));
		assertFalse(c2.equals(c3)); // different source, target, but same distance
		assertFalse(c3.equals(c2));
		
	}
}
