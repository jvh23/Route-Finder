package route_finder;


/** Building is a labeled EndPoint that marks the location of a building.
 *  It has two labels: a short name, and a long name.
 * @author Janelle Van Hofwegen
 */
/*
 * Abstraction Function:
 * A building is a type of EndPoint that marks the location of a building.
 * 
 * shortName -> abbreviated name of the building
 * longName -> long name of the building
 * 
 * Representation Invariant:
 * shortName != null
 * longName != null
 */

public class Building extends EndPoint implements Comparable<Building>{
	public final String shortName;
	public final String longName;
	
	/**
	 * Constructs a new building with the given coordinates and labels.
	 * @param x : x-coordinate of the building
	 * @param y : y-coordinate of the building
	 * @param shortName : abbreviated name of the building
	 * @param longName : long name of the building
	 */
	public Building(double x, double y, String shortName, String longName){
		super(x, y);
		this.shortName = shortName;
		this.longName = longName;
		checkRep();
	}
	
	/**
	 * Returns the abbreviated name of the building
	 * @return the abbreviated name of the building.
	 */
	public String shortName(){
		return shortName;
	}
	
	/**
	 * Returns the long name of the building.
	 * @return the long name of the building
	 */
	public String longName(){
		return longName;
	}
	
	/**
	 * Compares this Building to another Building.  Uses lexicographical comparison of
	 * the two buildings' short names to determine relative ordering.
	 * @param other : building to be compared to
	 * @return the value 0 if the other building's short name is lexicographically 
	 *  equal to this building's short name, a value greater than 0 if this building's
	 *  short name is lexicographically greater than the other building's short name, and 
	 *  and a value less than 0 if this building's short name is lexicographically less than
	 *  the other building's short name
	 */
	public int compareTo(Building other){
		return shortName().compareTo(other.shortName());
	}
	
	/**
	 *  Throws an exception if the representation invariant is violated.
	 */
	private void checkRep(){
		assert shortName != null;
		assert longName != null;
	}
}
