package marvel;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class MarvelParser {
	private static final String DATA_FILE = "marvel.tsv";

	/**
	 * Reads in the Marvel Universe dataset storing characters' appearances in comic books
	 * 
	 * @param characters empty list for storing all character names
	 * @param books empty map for mapping titles of comic books to characters
	 * 		that appear in them
	 * @modifies characters, books
	 * @effects fills characters with a list of all unique character names
	 * @effects fills books with a map from each comic book to all characters appearing in it
	 */
	public static void parseData(Collection<String> characters, Map<String, List<String>> books) {
		try {
			// Load the data file
			InputStream is = new FileInputStream(DATA_FILE);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader rdr = new BufferedReader(isr);
			
			// Construct the Collections one <character, book> pair at a time
			String line = rdr.readLine();
			String lastCharacter = "";
			while (line != null) {
				String[] tokens = line.split("\t");
				
				if (tokens.length == 2) {
					String character = tokens[0];
					String book = tokens[1];
					
					// Add to list of characters if necessary.
					// Data is sorted by character, so all entries for the same character will be
					// together. To determine if we've seen this character before, it suffices to
					// compare against the character from the previous entry.
					if (!character.equals(lastCharacter)) {
						characters.add(character);
						lastCharacter = character;
					}
					
					// Add to book-character map
					List<String> bookChars = books.get(book);
					if (bookChars == null) {
						bookChars = new ArrayList<String>();
						bookChars.add(character);
						books.put(book, bookChars);
					} else {
						bookChars.add(character);
					}
				}
				
				line = rdr.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
