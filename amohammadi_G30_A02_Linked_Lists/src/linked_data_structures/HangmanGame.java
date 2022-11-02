/**
 * 
 */
package linked_data_structures;

import java.util.StringTokenizer;

import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Arah Mohammadi
 *
 */
public class HangmanGame {
	protected SinglyLinkedList<String> wordList = new SinglyLinkedList();
	protected SinglyLinkedList<Character> lettersList = new SinglyLinkedList();
	protected SinglyLinkedList<Character> guessedLetters = new SinglyLinkedList();

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

} // HangmanGame
