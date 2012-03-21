package graph.tests;

import graph.*;
import org.junit.Test;
import static org.junit.Assert.*; 
import marvel.MarvelEdge;

public class EdgeTest {
	
	String name = "name";
	
	String n1 = "Fred";
	String n2 = "Lucy";
	String label = "testLabel";
	Edge e = new MarvelEdge(n1, n2, label);
	Edge e2 = new MarvelEdge(n1, n1, label);
	
	@Test
	public void testConstructor() { 
		assertTrue("edge should not be equal to null", e != null);
	}
	
	// source == null
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorException2() {
		new MarvelEdge(null, n1, "label");
	}
	
	// target == null
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorException3() {
		new MarvelEdge(n1, null, "label");
	}
	
	@Test
	public void testgetLabel() { 
		assertTrue("wrong label returned", ((MarvelEdge) e).getBook().equals(label));
		assertTrue("wrong label returned", ((MarvelEdge) e2).getBook().equals(label));
	}
	
	@Test
	public void testgetSource() { 
		assertTrue("source refers to wrong node", e.getSource() == n1);
		assertTrue("source refers to wrong node", e2.getSource() == n1);
	}
	
	@Test
	public void testgetTarget() { 
		assertTrue("target refers to wrong node", e.getTarget() == n2);
		assertTrue("target refers to wrong node", e2.getTarget() == n1);
	}
}
