package linked_data_structures;

import static org.junit.jupiter.api.Assertions.*;

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

} // HangmanGameTest()
