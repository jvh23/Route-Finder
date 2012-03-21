package marvel;

import graph.Edge;
/**
 * MarvelEdge is a Edge that represents relationships between characters in
 * Marvel Comic Books.
 * @author Janelle Van Hofwegen
 *
 */
public class MarvelEdge implements Edge {
	
	private String book;
	private String source;
	private String target;
		
	public MarvelEdge (String source, String target, String  book){
		if(source == null || target == null || book == null){
			throw new IllegalArgumentException("Argument(s) cannot be equal to null");
		}
		this.source = source;
		this.target = target;
		this.book = book;
	}
	
	public String getBook(){
		return book;
	}
	
	public String getSource(){
		return source;
	}
	
	public String getTarget(){
		return target;
	}
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof MarvelEdge))
			return false;
		MarvelEdge other = (MarvelEdge) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		return true;
	}
	*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MarvelEdge))
			return false;
		MarvelEdge other = (MarvelEdge) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
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
}
