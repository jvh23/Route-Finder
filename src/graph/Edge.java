package graph;

// A generic edge for use in a graph.
/* An edge is a pointer from one node to another.
 * An edge directed, meaning it points in only one direction.
 * 
 * Abstraction Function:
 * Edge e = (source, target)
 * Where source is the source node which is pointing at the target node.
 * +--------+           +--------+
 * | source |  =======> | target |
 * +--------+           +--------+
 * 
 * Class invariant:
 * this.source != this.target
 * source != null
 * target != null
 */

public interface Edge {
	/**
	 * Returns source node of edge
	 * @return Node representing the source of this edge
	 */
	public Object getSource();
	
	/**
	 * Returns target node of edge
	 * @return Node representing the target of this edge 
	 */
	public Object getTarget();
		
}