package linked_data_structures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {
	@Test
	void testPlayer() {
		Player p = new Player();

		p.setNumGW(p.getNumGW() + 1);
		assertEquals(p.getNumGW(), 1);
	} // testPlayer()

	@Test
	void testPlayer2() {
		Player p = new Player();

		p.setNumGP(p.getNumGP() + 1);
		assertEquals(p.getNumGP(), 1);
	} // testPlayer2()
} // PlayerTest
