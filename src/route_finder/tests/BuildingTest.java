package route_finder.tests;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import route_finder.*;

public class BuildingTest {
	Building b1 = new Building(1, 1, "label1", "label2"); // same coords , diff labels
	Building b2 = new Building(1, 2, "label1", "label2"); // same labels
	Building b3 = new Building(2, 1, "label5", "label8"); // second bigger than first
	Building b4 = new Building(0, 0, "label1", "label2"); // zero case
	Building b5 = new Building(99.4, 265.2, "label3", "label4"); // larger numbers w/decimals
	Building b6  = new Building(-3.4, 2, "label3", "label3"); // one negative number
	Building b7 = new Building(-8.454341, -6.999, "label3", "label4"); // two negative numbers, longer decimals
	Building b8 = new Building(-8.454341, -6.999, "label6", "label4");
	List<Building> buildings;
	List<EndPoint> endPoints;
	double[] xValues;
	double[] yValues;
	
	public BuildingTest(){
		buildings = new ArrayList<Building>();
		buildings.add(b1);
		buildings.add(b2);
		buildings.add(b3);
		buildings.add(b4);
		buildings.add(b5);
		buildings.add(b6);
		buildings.add(b7);
		xValues = new double[]{1,1,2,0,99.4,-3.4,-8.454341};
		yValues = new double[]{1,2,1,0,265.2,2,-6.999};
		EndPointTest helper = new EndPointTest();
		endPoints = helper.getEndPoints();
	}	
	

	@Test
	public void testEquals(){
		// A Building with the same coordinates as a (super type) EndPoint should
		// be considered equivalent
		
		for(int i = 0; i < buildings.size(); i++ ){
			assertEquals(buildings.get(i), endPoints.get(i));
		}
		
		// Buildings with the same coordinates, but different labels should be equivalent
		assertEquals(b7, b8); // different labels
		
		// Test unequal Objects 
		assertFalse(b1.equals(b2)); // different coordinates
		assertFalse(b2.equals(b1)); // different coordinates
	}
	
	@Test
	public void testGetX(){
		for(int i = 0; i < xValues.length; i++){
			assertEquals(xValues[i], buildings.get(i).getX(), .0000000001);
		}
	}
	
	@Test
	public void testGetY(){
		for(int i = 0; i < xValues.length; i++){
			assertEquals(yValues[i], buildings.get(i).getY(), .0000000001);
		}
	}
	
	@Test
	public void testCompareTo(){
		assertTrue(b1.compareTo(b3) < 0); // b1 short label is alphabetically before b3
		assertTrue(b3.compareTo(b1) > 0);
		assertTrue(b1.compareTo(b2) == 0); // same short label
		assertTrue(b2.compareTo(b1) == 0); // same short label
	}
	public List<Building> getBuildings(){
		return buildings;
	}
}
