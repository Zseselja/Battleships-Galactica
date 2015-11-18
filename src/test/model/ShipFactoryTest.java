package test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import model.Ship;
import model.ShipFactory;


public class ShipFactoryTest {

	@Test
	public void testBuildAircraftCarrierPoint() {
		ShipFactory shipFactory = new ShipFactory();
		Point shipPoint = new Point(1, 1);
		
		Ship newShip = shipFactory.buildAircraftCarrier(shipPoint);
		
		assertEquals(shipPoint, newShip.getHead());
	}

	@Test
	public void testBuildDestroyerPoint() {
		ShipFactory shipFactory = new ShipFactory();
		Point shipPoint = new Point(1, 1);
		
		Ship newShip = shipFactory.buildDestroyer(shipPoint);
		
		assertEquals(shipPoint, newShip.getHead());
	}
}
