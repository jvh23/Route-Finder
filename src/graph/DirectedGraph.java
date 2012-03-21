package graph;



import java.util.*;

/**
 * A DirectedGraph models relationships between nodes. The objects
 * in the graph are referred to as nodes and the relationships between
 * those nodes are called edges. Each edge connects two nodes.
 * <br>
 * <br>
 * The edges are directed (one-way). So e => (n1, n2) means that there is a path
 * from node n1 to n2. Node n1 is referred to as the source node, and n2 is 
 * referred to as the target node.
 * <br>
 * <br>
 * There can be any number of edges (zero, one, or more) between a pair of nodes.
 * <br>
 * <br>
 * Each edge also has a label.
 * <br>
 * <br>
 * Graph nodes are generic (N); you must specify the type of node to be used in the graph.
 * Edges used in the graph must implement the Edge interface.
 * <br>
 * <br>
 * DirectedGraph is technically a type of graph called a "directed labeled multi-graph",
 * which you can read more about at: http://en.wikipedia.org/wiki/Multigraph
 * @author Janelle Van Hofwegen
 *
 */

/* Abstraction function:
 * Map<N, Set<Edge> nodes represents a graph of nodes and edges for
 * which the type of node of the graph is N, and the type of labels is L.
 *
 * Every key n in nodes.keySet() represents one unique node of the graph. 
 
 * Every key n in nodes.keySet() maps to a set of Edges, which are the 
 * edges that belong to n (if no edges belong to n, the set is empty).
 * 
 * An edge belongs to n if  e = (n, some other node)
 *
 * Representation invariant:
 * nodes != null
 * For every key/node n in nodes, the associated value/set s != null
 * All edges contained in the graph are legal, meaning that The edge's 
 * target and source is a node within the graph (possibly the same node).
 * <br>
 * <br>
 * No duplicate nodes can exist. If n1.equals(n2), n1 and n2 cannot both exist in the graph.
 * <br>
 * No duplicate edges can exist. If e1.equals(e2), e1 and e2 cannot both exist in the graph.
 *
 */
public class DirectedGraph<N> {
	
	private Map<N, Set<Edge>> nodes;
	/**
	 * Constructs an empty DirectedGraph with no nodes, no edges
	 * @modifies this
	 */
	public DirectedGraph(){
		nodes = new HashMap<N, Set<Edge>>();
		checkRep();
	}
	
	/**
	 * Constructs a DirectedGraph containing the nodes within list nodes
	 * @param nodes - list of nodes to be added to the graph
	 * @requires nodes != null
	 * @throws IllegalArgumentException if nodes == null
	 * @modifies this
	 */
	public DirectedGraph(List<N> nodes){
		this();
		if(nodes == null){
			throw new IllegalArgumentException();
		}
		for(N node: nodes){
			this.nodes.put(node, new HashSet<Edge>());
		}
		checkRep();
	}
	
	/**
	 * Constructs a DirectedGraph containing the nodes within list nodes, and
	 * all the Edges within list edges.
	 * @param nodes - collection of nodes to be added to the graph
	 * @param edges - collection of edges to be added to the graph
	 * @requires all edges are legal
	 * @modifies this
	 */
	public DirectedGraph(List<N> nodes, List<Edge> edges){
		this(nodes);
		for(Edge e: edges){
			// check that each edge is legal for given list of nodes
			if(!containsNode(e.getSource()) || !containsNode(e.getTarget())){
				throw new IllegalArgumentException("Edge is not legal");
			}
			this.nodes.get(e.getSource()).add(e);
		}
		checkRep();
	}
	
	/**
	 * Returns a set of all nodes contained in the graph.
	 * @return a set of all nodes in the graph.  Returns an empty set if no nodes
	 * are contained in graph.
	 */
	public Set<N> nodeSet() {
		return new HashSet<N>(nodes.keySet());
	}
	
	/**
	 * Adds the given node to the graph.  Does nothing if node already exists in graph.
	 * @param node - node to be added to the graph
	 * @requires node != null
	 * @throws IllegalArgumentException if node == null
	 * @modifies this
	 */
	public void addNode(N node) {
		if(node == null){
			throw new IllegalArgumentException("node cannot be null");
		}
		if(!containsNode(node)){
			nodes.put(node, new HashSet<Edge>());
		}
		
		//checkRep();
	}
	
	/**
	 * Removes the given node from the graph.  Does nothing if node does not
	 * exist in graph.
	 * @param node - node to be removed from the graph
	 * @modifies this
	 */
	public void removeNode(N node){
		nodes.remove(node);
		//checkRep();
	}
	
	/**
	 * Returns whether the given node exists in the graph
	 * @param node - node to be searched for in the graph
	 * @return true if given node is present in the graph
	 */
	public boolean containsNode(Object node){
		return nodes.containsKey(node);
	}
	
	/**
	 * Creates a new edge between given source node and target node, 
	 * with given label, and adds this edge to the graph.
	 * @param source - Source node (node edge is pointing from)
	 * @param target - Target node (node edge is pointing at)
	 * @param label - Label for newly created edge
	 * @ requires source != null, target !=null, label != null
	 * @ requires source node, target node are both present within graph
	 * @throws IllegalArgumentException if any parameters are null
	 * @throws IllegalArgumentException if the source and/or target
	 * node is not present within the graph
	 * @modifies this
	 */
	/* void addEdge(N source, N target, L label) {
		if(source == null || target == null || label == null){
			throw new IllegalArgumentException("parameter(s) cannot be null");
		}
		addEdge(new Edge<N, L>(source, target, label));
	}*/
	
	/**
	 * Adds given edge to graph.  Does nothing if edge already exists.
	 * @param e Edge to be added to graph
	 * @requires e != null
	 * @ requires given edge is legal for the graph
	 * @throws IllegalArgumentException if edge is null
	 * @throws IllegalArgumentException edge is not legal.
	 * @modifies this
	 */
	public void addEdge(Edge e) {
		if(e == null){
			throw new IllegalArgumentException("e cannot be null");
		}
		if(!containsNode(e.getSource()) || !containsNode(e.getTarget())){
			throw new IllegalArgumentException("node not contained within graph");
		}
		nodes.get(e.getSource()).add(e);
		//checkRep();
	}
		
	/**
	 * Removes the given Edge.  If given Edge is not present within graph,
	 * this method has no effect on the graph.
	 * @param e - Edge to be removed from the graph
	 * @requires - E is an edge present within the graph.
	 * @modifies this
	 */
	public void removeEdge(Edge e){
		if(containsNode(e.getTarget())){
			nodes.get(e.getSource()).remove(e);
		}
		//checkRep();
	}
	
	/**
	 * Returns whether the given Edge is contained in the graph.
	 * @param e - Edge being searched for in the graph
	 * @return true if the given Edge is contained in the graph
	 * @requires e != null
	 * @throws IllegalArgumentException if e == null
	 */
	public boolean containsEdge(Edge e) {
		if(e == null){
			throw new IllegalArgumentException();
		}
		//Not possible for the map to contain the edge if it is illegal
		if(!nodes.containsKey(e.getTarget())){
			return false;
		} else{
			for(N node : nodes.keySet()){
				if(nodes.get(node).contains(e)){
					return true; 
				}
			}
			return false;
		}
	}
	
	/**
	 * Returns a List of all edges whose source is node.  Therefore every Edge e in
	 * the List follows the form e = (node, some other node)
	 * @param node - the source node for which a set of edges is being returned
	 * @return a Set of all edges whose source is node. Returned set is empty if the
	 * node has no edges.
	 * @requires node != null
	 * @requires that node is contained within the method
	 * @throws IllegalArgumentException if node == null
	 * @throws IllegalArgumentException if node node is not contained within the graph
	 */
	public List<Edge> edgesOf(Object node) {
		if(node == null || !containsNode(node)){
			throw new IllegalArgumentException();
		}
		return new ArrayList<Edge>(nodes.get(node));
	}
	
	/**
	 * Finds and returns the shortest path (in terms of # of nodes visited)
	 * to get from node a to node b. Returns this path as an ordered list
	 * of edges that can be followed to get from a to b.
	 * <br>
	 * The first edge of the list is the starting edge.  It points from a to
	 * the second node in the path. The last edge of the list is the ending edge.
	 * It points from  the second to last node in the path to node b.
	 * <br>
	 * If node a and node b are not connected by any path, returns null.
	 * @param a - the source node for which a set of edges is being returned
	 * @return a List of edges representing the shortest (in terms of nodes visited)
	 * @requires a != null, b != null
	 * @requires that a and b are both contained within the graph
	 * @throws IllegalArgumentException if a == null or b == null
	 * @throws IllegalArgumentException if a and/or b is not in the graph
	 */
	public List<Edge> leastPaths(N a, N b) {
		if(a == null || b == null){
			throw new IllegalArgumentException("parameter(s) cannot be null");
		}
		if(!containsNode(a) || !containsNode(b)){
			throw new IllegalArgumentException("node does not exist within graph");
		}
		// paths is a Map from from nodes to paths (node -> lists of Edges that form a path)
		Map<Object, List<Edge>> paths = new HashMap<Object, List<Edge>>();
		// visit is a queue to of nodes to visit
		Queue<Object> visit = new LinkedList<Object>();;
		// add initial node (first name)
		visit.add(a);
		// add first path to map paths (u to empty path)
		paths.put(a, new ArrayList<Edge>());
		// while there are still nodes to visit in the visit queue
		while(!visit.isEmpty()){
			// dequeue node
			Object n = visit.remove();
			// if node is last name, we've found the shortest path
			if(n.equals(b)){
				return paths.get(n);
			} 
			// get a set of all the edges for this node in order to explore them
			List<Edge> edgeList = edgesOf(n);  
			// explore all of this node's edges
			for(Edge e: edgeList){

				// Target of edge is not in map (hasn't been visited)
				if(!paths.containsKey(e.getTarget())){
					// target node hasn't been visited, so add to visit Queue
					visit.add(e.getTarget()); 
					// look up path of node n and create copy, newPath
					List<Edge> newPath = new ArrayList<Edge>(paths.get(n));
					// append current edge to newPath
					newPath.add(e);
					// add newPath to paths Map
					paths.put(e.getTarget(), newPath);
				} 
			}
		}
		return null;
	}
	
	// Throws a RuntimeException if the rep invariant is violated.
	private void checkRep(){
		assert nodes != null;
		
		// assert all values (sets of edges)are not equal to null
		for(N node : nodes.keySet()){
			assert nodes.get(node) != null;
		}
		
		// assert all nodes have legal edges
		for(N node : nodes.keySet()){
			
			// check that each of single node's edges are legal
			for(Edge e: nodes.get(node)){
				if(!nodes.containsKey(e.getTarget())){
					throw new IllegalStateException("Edge is not legal: " +
							"target node not in graph");
				}
			}
		}
	}
}