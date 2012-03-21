package route_finder;

import graph.Edge;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
/**
 * CampusDataParser parses a path file and buildings file.
 * 
 *  The paths file contains straight-line walking path data, and the building
 *  file contains containing building coordinate locations.
 * @author Janelle Van Hofwegen
 */
public class CampusDataParser {
	private static final String PATHS_FILE = "campus_paths.txt";
	private static final String BUILDINGS_FILE = "campus_buildings.txt";

	// Parses building file into a list of building objects
	// returns null if file was not found
	/**
	 * Reads in the campus Building data file and returns a set of those Buildings
	 * @return set of Buildings present on campus
	 */
	public static Set<Building> parseBuildingData(){
		try {
			// Load the data file
			InputStream is = new FileInputStream(BUILDINGS_FILE);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader rdr = new BufferedReader(isr);
			
			// Fill this with buildings
			Set<Building> buildings = new TreeSet<Building>();
			
			// Parse
			String line = rdr.readLine();
			while (line != null) {
				String[] tokens = line.split("\t");
				if(tokens.length == 4){
					buildings.add(new Building(Double.parseDouble(tokens[2]), 
							Double.parseDouble(tokens[3]), tokens[0], tokens[1]));
				}
				line = rdr.readLine();
			}		
			return buildings;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Reads in the campus paths data file and fills a list of the Edges representing
	 * those paths, and fills a list of EndPoints representing all the coordinate
	 * locations present in the paths file 
	 * @param paths: empty list of edges for storing edges/paths 
	 * @param endPoints: empty list of EndPoints for storing EndPoints/coordinate locations
	 * @modifies paths, endPoints
	 * @effects fills paths with edges representing the paths in the paths file
	 * @effects fills endPoints with EndPoints representing each coordinate location 
	 * present in the paths file
	 */
	public static void parsePathsData(List<Edge> paths, List<EndPoint> endPoints){
		try {
			// Load the data file
			InputStream is = new FileInputStream(PATHS_FILE);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader rdr = new BufferedReader(isr);
			
			// Parse
			String sourceCoordinate = rdr.readLine();
			while(sourceCoordinate != null){
				String[] sourceTokens = sourceCoordinate.split("[,]");
				//System.out.println(Arrays.toString(sourceTokens));
				EndPoint e = new EndPoint(Double.parseDouble(sourceTokens[0]), 
						Double.parseDouble(sourceTokens[1]));
				endPoints.add(e);
				String next = rdr.readLine().trim();
				String[] tokens = next.split("[ \t,:]+");
				while(next != null && tokens.length == 3){
						//System.out.println(Arrays.toString(tokens)+ "hi!");
						CampusEdge p = new CampusEdge(e, new EndPoint(Double.parseDouble(tokens[0]),
								Double.parseDouble(tokens[1])), Double.parseDouble(tokens[2]));
						paths.add(p);
						next = rdr.readLine();
						if(next != null){
							next = next.trim();
							tokens = next.split("[ \t,:]+");
						}
				}
				sourceCoordinate = next;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}