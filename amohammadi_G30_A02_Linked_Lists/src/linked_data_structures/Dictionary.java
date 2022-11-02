package linked_data_structures;

import java.util.Scanner;
import java.io.*;

public class Dictionary {

	protected Scanner listReader;
	private String filename = "dictionary.txt";
	public Dictionary() {
		readFile(filename);
	}
	
	protected void readFile(String fn) {
		try {
			listReader = new Scanner(new File(fn));
		} catch (FileNotFoundException e) {
			System.out.println("Error " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		}
	} // readFile

} // Dictionary
