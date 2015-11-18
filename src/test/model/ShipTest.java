package test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import model.Ship;
import model.ShipType;

public class ShipTest {

	@Test
	public void testConstruction() throws Exception {
		Point headPoint = new Point(5, 5);
		
		Ship newShip = new Ship(ShipType.AIRCRAFT_CARRIER, 6, headPoint);
		
		assertTrue(newShip.getHead() == headPoint);
		assertTrue(newShip.getType() == ShipType.AIRCRAFT_CARRIER);
	}

}
