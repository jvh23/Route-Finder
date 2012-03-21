package route_finder;
/**
 * EndPoint represents a location in an (x, y) coordinate space, specified in
 * double precision. 
 * @author Janelle Van Hofwegen
 */
/*
 * Abstraction Function:
 * x -> the x-coordinate of the point
 * y -> the y-coordinate of the point
 * 
 * Representation Invariant:
 * true
 */
public class EndPoint {
	
	public final double x;
	public final double y;
	
	/**
	 * Constructs a new EndPoint with the given coordinates
	 * @param x : x-coordinate of the EndPoint
	 * @param y : y-coordinate of the EndPoint
	 */
	public EndPoint(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	/**
	 * Returns whether this EndPoint is equal to the given Object
	 * Return true if the given object is an EndPoint (or a subclass of EndPoint)
	 * and the two EndPoints have the same coordinates. Returns false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EndPoint))
			return false;
		EndPoint other = (EndPoint) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	/**
	 * Returns a string representation of this EndPoint.
	 */
	@Override
	public String toString() {
		return "EndPoint [x=" + Math.round(x) + ", y=" + Math.round(y) + "]";
	}
	
	/**
	 * Returns the x-coordinate of this EndPoint
	 * @return x-coordinate of this EndPoint
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * Returns the y-coordinate of this EndPoint
	 * @return y-coordinate of this EndPoint
	 */
	public double getY(){
		return y;
	}
}