/**
 * 
 */
package linked_data_structures;

import java.util.StringTokenizer;

import java.io.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import linked_data_structures.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * @author Arah Mohammadi
 * @Description: This class contains the code for the Hangman game functionality
 *
 */
public class HangmanGame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected SinglyLinkedList<String> wordList = new SinglyLinkedList();
	protected SinglyLinkedList<Character> lettersList = new SinglyLinkedList();
	protected SinglyLinkedList<Character> guessedLetters = new SinglyLinkedList();
	protected ArrayList<Integer> guessedIndices = new ArrayList<Integer>();
	private String word;
	private Player player;

	public HangmanGame() {

	} // HangmanGame()

	// populates word SinglyLinkedList
	protected boolean populateWordList() {
		Dictionary d = new Dictionary();
		boolean isValid = true;
		try {
			while (d.listReader.hasNext()) {
				StringTokenizer line = new StringTokenizer(d.listReader.nextLine(), "\n");
				wordList.add(line.nextToken());
			}
			d.listReader.close();
		} catch (NullPointerException e) {
			isValid = false;
		}
		return isValid;
	} // populateList()

	protected SinglyLinkedList<String> getWordList() {
		return wordList;
	} // getWordList()

	protected SinglyLinkedList<Character> getLettersList() {
		return lettersList;
	} // getLetters()

	protected void clearLists() {
		int j = guessedLetters.getLength() - 1;

		for (int i = lettersList.getLength() - 1; i >= 0; i--) {
			lettersList.remove(i);
		}

		while (j > 0) {
			guessedLetters.remove(j);
			--j;
		}
	} // clearLists()

	// populates letter SinglyLinkedList
	protected void populateLettersList() {
		String word = pickWord();
		char letter;
		for (int i = word.length() - 1; i >= 0; --i) {
			letter = word.charAt(i);
			lettersList.add(letter);
		} // for
	} // populateLettersList

	// picks a random word in list
	protected String pickWord() {
		int max = wordList.getLength();
		int ranNum = (int) Math.floor(Math.random() * (max - 0) + 0);

		String selectedWord = wordList.getElementAt(ranNum);
		setWord(selectedWord);
		return selectedWord;
	} // pickWord()

	// selects a random index in list
	protected int selectRanIndex() {
		int l = lettersList.getLength();
		Random ran = new Random();
		int ranIndex = ran.nextInt(l);
		return ranIndex;
	} // selectRanIndex()

	// checks if letter is in list
	protected boolean checkLetter(char guess) {
		boolean isRight = false;
		for (int i = 0; i < lettersList.getLength(); ++i) {
			if (guess == lettersList.getElementAt(i)) {
				isRight = true;
			} // if
		} // for
		return isRight;
	} // checkLetter(char)

	// finds list index for specific letter
	protected ArrayList<Integer> findLetIndex(char guess) {
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < lettersList.getLength(); ++i) {
			if (guess == lettersList.getElementAt(i)) {
				indices.add(i);
			} // if
		} // for
		return indices;
	} // findLetIndex(char)

	// checks if input is a number
	protected boolean checkNum(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	} // checkNum(String)

	protected void setWord(String w) {
		word = w;
	} // setWord(String)

	protected String getWord() {
		return word;
	} // getWord()

	protected void setPlayer(String name) {
		player = new Player(name);
	} // setPlayer(String)

	protected Player getPlayer() {
		return player;
	} // getPlayer()

	protected boolean winLoss(int numErr, int numCorr) {
		boolean hasWon = false;
		if (numCorr == getLettersList().getLength()) {
			hasWon = true;
		}
		return hasWon;
	} // winLoss(int, int)

	protected void removeWord() {
		for (int i = 0; i < wordList.getLength(); ++i) {
			if (wordList.getElementAt(i).equals(getWord())) {
				wordList.remove(i);
			} // if
		} // for

	} // removeWord()

	protected DoublyLinkedList<Player> sort(DoublyLinkedList<Player> list) {
		Player[] tempArr = new Player[list.getLength()];
		Player temp;
		boolean sorted = false;
		// temporarily adds to an array to be sorted
		for (int i = 0; i < list.getLength(); ++i) {
			tempArr[i] = list.getElementAt(i);
		}

		// clears list
		for (int i = list.getLength() - 1; i >= 0; i--) {
			list.remove(i);
		}

		// sorts array
		while (!sorted) {
			sorted = true;

			for (int i = 0; i < tempArr.length - 1; i++) {
				for (int j = i + 1; j < tempArr.length; j++) {
					if (tempArr[i].name.compareToIgnoreCase(tempArr[j].name) > 0) {
						temp = tempArr[i];
						tempArr[i] = tempArr[j];
						tempArr[j] = temp;
						sorted = false;
					} // if
				} // inner for

			} // outer for
		} // while

		// repopulates list
		for (int i = tempArr.length - 1; i >= 0; i--) {
			list.add(tempArr[i]);
		}

		return list;
	} // sort

} // HangmanGame
