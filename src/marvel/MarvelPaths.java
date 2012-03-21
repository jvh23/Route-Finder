package marvel;

import graph.*;
import java.util.*;
/**
 * MarvelPaths takes the names of two Marvel characters as command-line arguments
 * and then finds and prints the shortest path between them (if a path exists).
 * It prints the path in the form:

    Char1	Book1	Char2
    Char2	Book2	Char3
    ...
    CharN-1	BookN-1	CharN

* where Char1 is the first command-line argument, CharN is the second argument, 
* Char_k is the name of some character, and Book_k is the title of some book for all k.
 * 
 * If the two character names are the same, nothing is printed.
 * @author Janelle Van Hofwegen
 *
 */
public class MarvelPaths {
	public static void main(String[] args){
		
		// make a graph
		DirectedGraph<String> marvelGraph = new DirectedGraph<String>();
		
		// get the data to fill the marvelGraph
		Collection<String> characters = new LinkedList<String>();
		Map<String, List<String>> books = new HashMap<String, List<String>>();
		MarvelParser.parseData(characters, books);
		
		// fill graph with data
		fillGraph(marvelGraph, characters, books);
		
		// print
		if (args.length != 2){
			System.out.println("Incorrect number of arguments");
		} else {
			List<Edge> shortestPath= marvelGraph.leastPaths(args[0].trim(), args[1].trim());
			printPaths(shortestPath);
		}	
	}
	
	/**
	 * Fills a graph with the MarvelPath character data
	 * @param graph graph to be filled with data
	 * @param characters list of characters to add as nodes
	 * @param books map of books and characters contained in those books
	 */
	public static void fillGraph(DirectedGraph<String> graph,
			Collection<String> characters, Map<String, List<String>> books){
		// Initialize nodes, one for each character
		for(String character : characters){
			graph.addNode(character);
		}
		// Initialize edges based on Map
		for(String book: books.keySet()){
			// Get list of characters in this book
			List<String> charactersInBook = books.get(book); 
			for(int i = 0; i < charactersInBook.size(); i++){
				for(int j = 0; j < charactersInBook.size(); j++){
					if(i != j){
						graph.addEdge(new MarvelEdge(charactersInBook.get(i), charactersInBook.get(j), book));
					}
				}
			}
		}
	}

	/**
	 * Prints out paths
	 * @param shortestPath : path to be printed
	 */
	public static void printPaths(List<Edge> shortestPath){
		if(shortestPath == null){
			System.out.println("No path found");
		} else{
			for(Edge e: shortestPath){
				System.out.print(e.getSource() + "    ");
				System.out.print(((MarvelEdge) e).getBook() + "     ");
				System.out.print(e.getTarget());
				System.out.println();
			}
		}
	}
}