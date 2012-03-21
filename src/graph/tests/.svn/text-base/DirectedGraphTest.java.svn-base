package graph.tests;


import java.util.*;
import graph.*;
import marvel.MarvelEdge;
import org.junit.Test;
import static org.junit.Assert.*; 
/**
 * Tests the various methods of DirectedGraph to make sure that they are operating properly
 * (doing what their specification says)
 * @author Janelle Van Hofwegen
 *
 */
public class DirectedGraphTest {
	// Individual nodes to be used commonly in test operations
	private final String n1 = "Fred";
	private final String n2 = "Lucy";
	private final String n3 = "Sally";	
	private final String n4 = "Jenny";
	private final String n5 = "Darold";
	
	private final Edge e1 = new MarvelEdge(n1, n2, "label 1");
	private final Edge e2 = new MarvelEdge(n2, n1, "label 2");
	private final Edge e3 = new MarvelEdge(n2, n3, "label 3");	
	private final Edge e4 = new MarvelEdge(n3, n2, "label 4");
	private final Edge e5 = new MarvelEdge(n1, n3, "label 5");
	private final Edge e6 = new MarvelEdge(n5, n4, "label 6");
	
	// Lists of nodes to be used commonly in test operations
	private final List <String> multiNodesList = new LinkedList<String>();
	private final List<Edge> multiEdgesList = new LinkedList<Edge>();
	
	// Initializes multiNodesList & multiEdgesList
	public DirectedGraphTest(){
		// add nodes
		multiNodesList.add(n1);
		multiNodesList.add(n2);
		multiNodesList.add(n3);
		// add edges
		multiEdgesList.add(e1);
		multiEdgesList.add(e2);
		multiEdgesList.add(e3);
	}
	
	// **************************************************************
	// Test nodeSet
	//***************************************************************
	@Test
	public void testNodeSet() { 
		// test empty graph
		DirectedGraph<String> gEmpty = new DirectedGraph<String>();
		assertTrue("set should not contain any elements", gEmpty.nodeSet().size() == 0);
		
		// test one String graph
		List<String> oneList = new LinkedList<String>();
		oneList.add(n1);
		DirectedGraph<String> gOne = new DirectedGraph<String>(oneList);
		assertTrue("set contains wrong # of elements", gOne.nodeSet().size() == 1);
		assertTrue("set does not contain expected element", gOne.nodeSet().contains(n1));
		
		// test multi-String graph
		DirectedGraph<String> multiNode = new DirectedGraph<String>(multiNodesList);
		assertTrue("set contains wrong # elements", multiNode.nodeSet().size() == 3);
		assertTrue("set does not contain expected element", 
				multiNode.nodeSet().containsAll(multiNodesList));
	}
	
	// **************************************************************
	// Test various constructor combinations.  Assumes  nodeSet works properly
	// because it was tested earlier.
	//***************************************************************
	@Test
	public void testConstructors(){
		List<String> emptyNodeList = new LinkedList<String>();
		List<String> oneNodeList = new LinkedList<String>();
		List<String> twoNodeList = new LinkedList<String>();
		List<String> fiveNodeList = new LinkedList<String>();
		oneNodeList.add(n1);
		twoNodeList.add(n1);
		twoNodeList.add(n2);
		fiveNodeList.add(n1);
		fiveNodeList.add(n2);
		fiveNodeList.add(n3);
		fiveNodeList.add(n4);
		fiveNodeList.add(n5);
		
		// Test Constructor that accepts a list of nodes.  Lists sent in
		// are of various lengths.
		DirectedGraph<String> emptyNodeGraph = new DirectedGraph<String>(emptyNodeList); 
		DirectedGraph<String> oneNodeGraph = new DirectedGraph<String>(oneNodeList);
		DirectedGraph<String> twoNodeGraph = new DirectedGraph<String>(twoNodeList);
		DirectedGraph<String> fiveNodeGraph = new DirectedGraph<String>(fiveNodeList);
		
		assertTrue(emptyNodeGraph.nodeSet().size() == 0);
		assertTrue(oneNodeGraph.nodeSet().size() == 1);
		assertTrue(twoNodeGraph.nodeSet().size() == 2);
		assertTrue(fiveNodeGraph.nodeSet().size() == 5);
	}
	
	// **************************************************************
	// Test addNode: assumes nodeSet works properly because it was tested
	// earlier.
	//***************************************************************
	@Test
	public void testaddNode() { 
		// add onto empty graph
		DirectedGraph<String> gEmpty = new DirectedGraph<String>();
		gEmpty.addNode(n1);
		assertTrue("set does not contain expected element", gEmpty.nodeSet().contains(n1));
		gEmpty.addNode(n2);
		assertTrue("set does not contain expected element", gEmpty.nodeSet().contains(n1));
		assertTrue("set does not contain expected element", gEmpty.nodeSet().contains(n2));
		gEmpty.addNode(n3);
		assertTrue("set does not contain expected element", gEmpty.nodeSet().containsAll(multiNodesList));
		
		// add onto graph already containing some elements
		DirectedGraph<String> gMult = new DirectedGraph<String>(multiNodesList);
		gMult.addNode(n4);
		gMult.addNode(n5);
		assertTrue("set does not contain expected element", gMult.nodeSet().contains(n4));
		assertTrue("set does not contain expected element", gMult.nodeSet().contains(n5));
		assertTrue("set does not contain expected element", gMult.nodeSet().containsAll(multiNodesList));
		
		// try to insert same String multiple times (should have no effect)
		gMult.addNode(n5);
		gMult.addNode(n5);
		gMult.addNode(n4);
		assertTrue("set does not contain expected element", gMult.nodeSet().contains(n4));
		assertTrue("set does not contain expected element", gMult.nodeSet().contains(n5));
		
	}	
	
	
	// **************************************************************
	// Test containsNode
	//***************************************************************
	@Test
	public void testContainsNode(){
		// test empty graph
		DirectedGraph<String> gEmpty = new DirectedGraph<String>();
		assertFalse(gEmpty.containsNode(n1));
		
		// test multi-String graph
		DirectedGraph<String> gMult = new DirectedGraph<String>(multiNodesList);
		assertFalse(gMult.containsNode(n4));
		assertTrue(gMult.containsNode(n1));

	}
	
	// **************************************************************
	// Test removeNode: assumes addNode(), containsNode(), and  NodeSet()
	// work properly because they were tested earlier.
	//***************************************************************
	
	@Test
	public void testRemoveNode(){
		// add 1, then remove 1
		DirectedGraph<String> g1 = new DirectedGraph<String>();
		g1.addNode(n1);
		g1.removeNode(n1);
		assertFalse(g1.containsNode(n1));
		
		// add 3, then remove 1
		DirectedGraph<String> g2 = new DirectedGraph<String>();
		g2.addNode(n1);
		g2.addNode(n2);
		g2.addNode(n3);
		g2.removeNode(n2);
		assertFalse(g2.containsNode(n2));
		
		// add 3, then remove 3
		DirectedGraph<String> g3 = new DirectedGraph<String>();
		g3.addNode(n1);
		g3.addNode(n2);
		g3.addNode(n3);
		g3.removeNode(n1);
		g3.removeNode(n2);
		g3.removeNode(n3);
		assertFalse(g3.containsNode(n1));
		assertFalse(g3.containsNode(n2));
		assertFalse(g3.containsNode(n3));
		
		// try to remove elements not contained in list	
		DirectedGraph<String> g4 = new DirectedGraph<String>(multiNodesList);
		g4.removeNode(n4);
		g4.removeNode(n5);
		assertTrue(g4.nodeSet().containsAll(multiNodesList));
		assertTrue(g4.nodeSet().size() == 3);
	}
	
	// **************************************************************
	// Test containsEdge
	//***************************************************************
	@Test
	public void testContainsEdge(){
		// test empty graph
		DirectedGraph<String> gEmpty = new DirectedGraph<String>();
		assertFalse(gEmpty.containsEdge(e1));
		
		// test multi-String graph with no edges
		DirectedGraph<String> gMult1 = new DirectedGraph<String>(multiNodesList);
		assertFalse(gMult1.containsEdge(e1));
		
		// test multi-String graph with multiple edges
		DirectedGraph<String> gMult2 = new DirectedGraph<String>(multiNodesList, multiEdgesList);
		assertTrue(gMult2.containsEdge(e1));
		assertTrue(gMult2.containsEdge(e2));
		assertTrue(gMult2.containsEdge(e3));
		assertFalse(gMult2.containsEdge(e4));
	}
	
	// **************************************************************
	// Test edgesOf
	//***************************************************************
	@Test(expected=IllegalArgumentException.class)
	public void testEdgesOfExceptionForBadEdgePass(){
		// try to get a set from a graph that doesn't contain the given String
		DirectedGraph<String> gMult = new DirectedGraph<String>(multiNodesList);
		gMult.edgesOf(n5);
	}
	
	@Test
	public void testEdgesOf(){
		DirectedGraph<String> gMult = new DirectedGraph<String>(multiNodesList, multiEdgesList);

		// test String that exists but has no edges
		assertTrue(gMult.edgesOf(n3).size() == 0);
		
		// test String that exists with one edges
		assertTrue(gMult.edgesOf(n1).size() == 1);
		assertTrue(gMult.edgesOf(n1).contains(e1));


		// test String that exists with multiple edges
		assertTrue(gMult.edgesOf(n2).size() == 2);
		assertTrue(gMult.edgesOf(n2).contains(e2));
		assertTrue(gMult.edgesOf(n2).contains(e3));
	}
	
	// **************************************************************
	// Test addEdge : assumes that containsEdge works properly, since
	// it was tested earlier
	//***************************************************************
	@Test(expected=IllegalArgumentException.class)
	public void testAddEdgeExceptionEmptyGraph(){
		// try to add edge into empty list
		DirectedGraph<String> gEmpty = new DirectedGraph<String>();
		gEmpty.addEdge(e1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddEdgeExceptionMultiGraph(){
		// try to add illegal edge into graph containing some elements
		DirectedGraph<String> gMult = new DirectedGraph<String>(multiNodesList);
		gMult.addEdge(e6);
	}
	
	@Test
	public void testAddEdge(){	
		DirectedGraph<String> gMult = new DirectedGraph<String>(multiNodesList);

		// add legal edges onto graph already containing some elements
		gMult.addEdge(e1);
		assertTrue(gMult.containsEdge(e1));
		assertTrue(gMult.edgesOf(n1).size() ==1);
		gMult.addEdge(e2);
		assertTrue(gMult.containsEdge(e1));
		assertTrue(gMult.containsEdge(e2));
		assertTrue(gMult.edgesOf(n1).size() ==1);
		assertTrue(gMult.edgesOf(n2).size() ==1);

		gMult.addEdge(e3);
		assertTrue(gMult.containsEdge(e1));
		assertTrue(gMult.containsEdge(e2));
		assertTrue(gMult.containsEdge(e3));
		assertTrue(gMult.edgesOf(n1).size() ==1);
		assertTrue(gMult.edgesOf(n2).size() ==2);

		// try to insert same edge multiple times (legal)
		// Should have no effect.
     	gMult.addEdge(new MarvelEdge(n2, n3, "label 3")); 
		gMult.addEdge(new MarvelEdge(n2, n3, "label 3"));
		gMult.addEdge(e3);
		assertTrue(gMult.containsEdge(e1));
		assertTrue(gMult.containsEdge(e2));
		assertTrue(gMult.containsEdge(e3));
		assertFalse(gMult.containsEdge(e6));
		assertTrue(gMult.edgesOf(n1).size() == 1);
		for(Edge e: gMult.edgesOf(n2)){
			System.out.println(e.getTarget() + " " + e.getSource() + " " + ((MarvelEdge)e).getBook());
		}
		assertTrue("size of edgesOf is " + gMult.edgesOf(n2).size() + " when it should be 2",gMult.edgesOf(n2).size() == 2);
	}
	
	/*@Test
	public void testAddEdgeOtherConstructor(){	
		DirectedGraph<String> gMult = new DirectedGraph<String>(multiNodesList);

		// add legal edges onto graph already containing some elements
		gMult.addEdge(n1, n2, "label 1");
		assertTrue(gMult.containsEdge(e1));
		assertTrue(gMult.edgesOf(n1).size() ==1);
		gMult.addEdge(n2, n1, "label 2");
		assertTrue(gMult.containsEdge(e1));
		assertTrue(gMult.containsEdge(e2));
		assertTrue(gMult.edgesOf(n1).size() ==1);
		assertTrue(gMult.edgesOf(n2).size() ==1);

		gMult.addEdge(n2, n3, "label 3");
		assertTrue(gMult.containsEdge(e1));
		assertTrue(gMult.containsEdge(e2));
		assertTrue(gMult.containsEdge(e3));
		assertTrue(gMult.edgesOf(n1).size() ==1);
		assertTrue(gMult.edgesOf(n2).size() ==2);

		// try to insert same edge multiple times (legal)
		// Should have no effect.
     	gMult.addEdge(new MarvelEdge(n2, n3, "label 3")); 
		gMult.addEdge(new MarvelEdge(n2, n3, "label 3"));
		gMult.addEdge(n2, n1, "label 2");
		assertTrue(gMult.containsEdge(e1));
		assertTrue(gMult.containsEdge(e2));
		assertTrue(gMult.containsEdge(e3));
		assertFalse(gMult.containsEdge(e6));
		assertTrue(gMult.edgesOf(n1).size() ==1);
		assertTrue(gMult.edgesOf(n2).size() ==2);
	}*/
	
	// **************************************************************
	// Test removeEdge: Assumes addEdge and containsEdge work properly,
	// since they were tested earlier.
	//***************************************************************
	
	@Test
	public void testRemoveEdge(){
		// add 1, remove 1
		DirectedGraph<String> g1 = new DirectedGraph<String>(multiNodesList);
		g1.addEdge(e1);
		g1.removeEdge(e1);
		assertFalse(g1.containsEdge(e1));
		
		// add 3, remove 1
		DirectedGraph<String> g2 = new DirectedGraph<String>(multiNodesList);
		g2.addEdge(e1);
		g2.addEdge(e3);
		g2.addEdge(e2);
		g2.removeEdge(e3);
		assertFalse(g2.containsEdge(e3));
		
		// add 3, remove 3
		DirectedGraph<String> g3 = new DirectedGraph<String>(multiNodesList);
		g3.addEdge(e1);
		g3.addEdge(e3);
		g3.addEdge(e2);
		g3.removeEdge(e3);
		g3.removeEdge(e1);
		g3.removeEdge(e2);
		assertFalse(g3.containsEdge(e1));
		assertFalse(g3.containsEdge(e2));
		assertFalse(g3.containsEdge(e3));
		
		// mixed combination of add, remove
		DirectedGraph<String> g4 = new DirectedGraph<String>(multiNodesList);
		g4.addEdge(e1);
		g4.addEdge(e3);
		g4.removeEdge(e1);
		g4.addEdge(e2);
		g4.addEdge(e1);
		g4.removeEdge(e3);
		g4.removeEdge(e2);
		assertTrue(g4.containsEdge(e1));
		assertFalse(g4.containsEdge(e2));
		assertFalse(g4.containsEdge(e3));

		// try to remove edge not contained in graph
		DirectedGraph<String> g5 = new DirectedGraph<String>(multiNodesList, multiEdgesList);
		g4.removeEdge(e5);
		assertTrue(g5.containsEdge(e1));
		assertTrue(g5.containsEdge(e2));
		assertTrue(g5.containsEdge(e3));
	}	
	
	// **************************************************************
	// Test leastPaths
	//***************************************************************
	@Test(expected=IllegalArgumentException.class)
	public  void testLeastPathsNonExistentSource(){
		// test trying to find path of Source Node not in graph
		DirectedGraph<String> g = new DirectedGraph<String>(multiNodesList, multiEdgesList);
		g.leastPaths(n4, n2);
		
	}
	@Test(expected=IllegalArgumentException.class)
	public  void testLeastPathsNonExistentTarget(){
		// test trying to find path of Target Node not in graph
		DirectedGraph<String> g = new DirectedGraph<String>(multiNodesList, multiEdgesList);
		g.leastPaths(n1, n4);	
	}
	
	@Test
	public void testLeastPaths(){
		DirectedGraph<String> g = new DirectedGraph<String>(multiNodesList, multiEdgesList);		
		// one edge path
		assertTrue(g.leastPaths(n1, n2).size() == 1);
		assertTrue(g.leastPaths(n1, n2).contains(e1));
		// path to self
		assertTrue(g.leastPaths(n1, n1).size() == 0);
		// no path
		assertNull(g.leastPaths(n3, n1));
		// two edge path
		assertTrue(g.leastPaths(n1, n3).size() == 2);
		assertTrue(g.leastPaths(n1, n3).contains(e1));
		assertTrue(g.leastPaths(n1, n3).contains(e3));
	}
}