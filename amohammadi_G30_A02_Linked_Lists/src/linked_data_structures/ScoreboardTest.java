package linked_data_structures;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ScoreboardTest {

	@Test
	void testNumGP() {
		Player p = new Player("John");
		Scoreboard s = new Scoreboard();
		s.gamePlayed(p, true);
		assertEquals(p.getNumGP(), 1);
	} // testNumGP()

	@Test
	void testNumGW() {
		Player p = new Player("John");
		Scoreboard s = new Scoreboard();
		s.gamePlayed(p, true);
		assertEquals(p.getNumGW(), 1);
	} // testNumGW()

	@Test
	void testNumGW2() {
		Player p = new Player("Tom");
		Scoreboard s = new Scoreboard();
		s.gamePlayed(p, false);
		assertEquals(p.getNumGW(), 0);
	} // testNumGW2()

	@Test
	void addPlayerTest() {
		Scoreboard sc = new Scoreboard();
		Player pl = new Player("arah");
		sc.addPlayer(pl);
		assertEquals(sc.playersList.getElementAt(0).name, "arah");
	} // addPlayerTest

	@Test
	void getNextPlayerTest() {
		Scoreboard sc = new Scoreboard();
		sc.playersList = new DoublyLinkedList<Player>();
		assertNull(sc.getNextPlayer(1));
	} // getNextPlayerTest()

	@Test
	void getNextPlayerTest2() {
		Scoreboard sc = new Scoreboard();
		sc.playersList = new DoublyLinkedList<Player>();

		Player a = new Player();
		a.name = "arah";

		sc.playersList.add(a);
		assertEquals(sc.getNextPlayer(0).name, a.name);
	} // getNextPlayerTest2()

	@Test
	void getNextPlayerTest3() {
		Scoreboard sc = new Scoreboard();
		sc.playersList = new DoublyLinkedList<Player>();

		Player a = new Player();
		a.name = "arah";

		Player b = new Player();
		b.name = "joe";

		Player c = new Player();
		c.name = "Ron";

		sc.playersList.add(a);
		sc.playersList.add(b);
		sc.playersList.add(c);
		assertEquals(sc.getNextPlayer(1).name, b.name);
	} // getNextPlayerTest3()

} // ScoreboardTest
