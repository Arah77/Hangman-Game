package linked_data_structures;

import java.util.Scanner;
import java.io.*;

/**
 * @author Arah Mohammadi
 * @Description: This class contains the code for the Hangman game file reading
 *               and handling
 *
 */

public class Dictionary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Scanner listReader;
	private String filename = "dictionary.txt";

	public Dictionary() {
		readFile(filename);
	}

	protected boolean readFile(String fn) {
		try {
			listReader = new Scanner(new File(fn));
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	} // readFile

} // Dictionary
