package route_finder.tests;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import route_finder.*;

public class EndPointTest {
	EndPoint e1 = new EndPoint(1, 1); // same number
	EndPoint e2 = new EndPoint(1, 2); // first bigger than second
	EndPoint e3 = new EndPoint(2, 1); // second bigger than first
	EndPoint e4 = new EndPoint(0,0); // zero case
	EndPoint e5 = new EndPoint(99.4, 265.2); // larger numbers w/decimals
	EndPoint e6  = new EndPoint(-3.4, 2); // one negative number
	EndPoint e7 = new EndPoint(-8.454341, -6.999); // two negative numbers, longer decimals
	public List<EndPoint> endPoints;
	double[] xValues;
	double[] yValues;
	
	public EndPointTest(){
		endPoints = new ArrayList<EndPoint>();
		endPoints.add(e1);
		endPoints.add(e2);
		endPoints.add(e3);
		endPoints.add(e4);
		endPoints.add(e5);
		endPoints.add(e6);
		endPoints.add(e7);
		xValues = new double[]{1,1,2,0,99.4,-3.4,-8.454341};
		yValues = new double[]{1,2,1,0,265.2,2,-6.999};
	}
	
	@Test
	public void testEquals(){
		// Test equivalent Objects (same coordinates)
		assertEquals(e1, e1);
		assertEquals(e1, new EndPoint(1,1));
		assertEquals(e2, new EndPoint(1,2));
		assertEquals(e5, new EndPoint(99.4, 265.2));
		assertEquals(e4, new EndPoint(0,0));
		assertEquals(e7, new EndPoint(-8.454341, -6.999));
		
		// Test unequal Objects (different coordinates)
		assertFalse(e2.equals(e3)); // points with reversed x & y coordinates
		assertFalse(e3.equals(e2));
		assertFalse(e1.equals(e4));
		assertFalse(e4.equals(e1));
	}
	
	@Test
	public void testGetX(){
		for(int i = 0; i < xValues.length; i++){
			assertEquals(xValues[i], endPoints.get(i).getX(), .0000000001);
		}
	}
	
	@Test
	public void testGetY(){
		for(int i = 0; i < xValues.length; i++){
			assertEquals(yValues[i], endPoints.get(i).getY(), .0000000001);
		}
	}
	
	public List<EndPoint> getEndPoints(){
		return endPoints;
	}
}
