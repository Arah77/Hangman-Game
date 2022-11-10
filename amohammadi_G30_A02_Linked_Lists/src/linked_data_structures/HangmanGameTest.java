package linked_data_structures;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class HangmanGameTest {

	@Test
	void populateWordListTest() {
		HangmanGame hg = new HangmanGame();

		assertTrue(hg.populateWordList());
	} // populateWordListTest()

	@Test
	void getWordsListTest() {
		HangmanGame hg = new HangmanGame();

		hg.populateWordList();
		assertEquals(hg.getWordList(), hg.wordList);
	} // getWordsListTest()

	@Test
	void pickwordTest() {
		HangmanGame hg = new HangmanGame();

		hg.wordList.add("wordTest");
		assertEquals(hg.pickWord(), "wordTest");
	} // pickwordTest()

	@Test
	void checkLetterTest() {
		HangmanGame hg = new HangmanGame();

		hg.lettersList.add('h');

		assertTrue(hg.checkLetter('h'));
	} // checkLetterTest()

	@Test
	void checkNumTest() {
		HangmanGame hg = new HangmanGame();

		assertTrue(hg.checkNum("3"));
	} // checkNumTest()

	@Test
	void checkNumTest2() {
		HangmanGame hg = new HangmanGame();

		assertFalse(hg.checkNum("a"));
	} // checkNumTest2()

	@Test
	void findLetIndexTest() {
		HangmanGame hg = new HangmanGame();
		ArrayList<Integer> ans = new ArrayList<Integer>();

		hg.lettersList.add('h');
		hg.lettersList.add('c');
		ans.add(0);

		assertEquals(hg.findLetIndex('c'), ans);
	} // findLetIndexTest

	@Test
	void winLossTest() {
		HangmanGame hg = new HangmanGame();
		hg.lettersList.add('a');
		hg.lettersList.add('l');
		hg.lettersList.add('l');

		assertTrue(hg.winLoss(2, 3));
	} // winLossTest()

	@Test
	void winLossTest2() {
		HangmanGame hg = new HangmanGame();
		hg.lettersList.add('a');
		hg.lettersList.add('l');
		hg.lettersList.add('l');

		assertFalse(hg.winLoss(6, 2));
	} // winLossTest2()

	@Test
	void removeWordTest() {
		HangmanGame hg = new HangmanGame();
		hg.wordList.add("Test");
		hg.wordList.add("Hello");
		hg.setWord("Test");

		hg.removeWord();

		assertEquals(hg.wordList.getLength(), 1);
	} // removeWordTest()

} // HangmanGameTest()
