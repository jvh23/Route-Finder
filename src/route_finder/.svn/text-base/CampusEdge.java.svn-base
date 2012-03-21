package route_finder;
import graph.Edge;
/**
 * CampusEdge is an Edge which represents a straight-line segment of a walking path.
 * @author Janelle Van Hofwegen
 *
 */
/*
 * Abstraction Function:
 * source -> EndPoint where the path begins
 * target -> EndPoint where the path ends
 * distance -> length of this path.  Number of feet between the source and target.
 * 
 * CampusEdge represents a directed path, meaning that the path goes one way only
 * (source to target):
 * 
 * +--------+   distance   +--------+
 * | source |  ==========> | target |
 * +--------+              +--------+
 * 
 * Representation Invariant:
 * source != null
 * target != null
 * distance >= 0
 */
public class CampusEdge implements Edge, Comparable<CampusEdge>{
	
	private EndPoint source;
	private EndPoint target;
	private double distance;
	
	/**
	 * Constructs a new CampusEdge with the given source, target, and distance.
	 * @param source : EndPoint where the CampusEdge begins
	 * @param target : EndPoint where the CampusEdge ends
	 * @param distance : distance of the path between the source and target EndPoints
	 * @requires distance >= 0
	 */
	public CampusEdge (EndPoint source, EndPoint target, double distance){
		if(source == null || target == null ){
			throw new IllegalArgumentException("Argument(s) cannot be equal to null");
		}
		if(distance < 0){
			throw new IllegalArgumentException("Distance must be positive");
		}
		this.source = source;
		this.target = target;
		this.distance = distance;
		checkRep();
	}
	
	/**
	 * Returns the source EndPoint of this CampusEdge
	 * @return: source of this CampusEdge
	 */
	public EndPoint getSource(){
		return source;
	}
	
	/**
	 * Returns the target EndPoint of this CampusEdge
	 * @return: target of this CampusEdge
	 */
	public EndPoint getTarget(){
		return target;
	}
	
	/**
	 * Returns the distance of this CampusEdge
	 * @return distance of this CampusEdge
	 */
	public double getDistance(){
		return distance;
	}
	
	/**
	 * Compares this CampusEdge to another CampusEdge.
	 * @return the value 0 if the distances of both CampusEdges are equal, a value
	 * greater than zero if this CampusEdge is longer (has a greater distance) than
	 * the other CampusEdge, and a value less than zero if this CampusEdge is shorter
	 * (has a smaller distance) than the other CampusEdge.
	 */
	public int compareTo(CampusEdge other){
		double diff = distance - other.getDistance();
		if(diff < 0)
			return -1;
		else if (diff > 0)
			return 1;
		else
			return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	/**
	 * Returns whether this CampusEdge is equal to the given Object.
	 * @return true if the given Object is a CampusEdge with an equal 
	 * source, target, and distance to this CampusEdge.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CampusEdge))
			return false;
		CampusEdge other = (CampusEdge) obj;
		if (Double.doubleToLongBits(distance) != Double
				.doubleToLongBits(other.distance))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}

	/**
	 * Returns a string representation of this CampusEdge
	 */
	@Override
	public String toString() {
		return "CampusEdge [source=" + source + ", target=" + target + ", distance="
				+ Math.round(distance) + "]";
	}
	
	/**
	 *  Throws an exception if the representation invariant is violated.
	 */
	private void checkRep(){
		assert source != null;
		assert target != null;
		assert distance >= 0;
	}
}