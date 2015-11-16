package test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Player;

public class PlayerTest {

	@Test
	/**
	 * Tests that setting and retreiving win on a player works
	 */
	public void testWin() {
		Player player = new Player();
		
		player.setWin(true);
		assertTrue(player.isWin());
		
		player.setWin(false);
		assertFalse(player.isWin());
	}
}
