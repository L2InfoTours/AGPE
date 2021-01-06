package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import liaisonappliBDopta.ContrainteSalle;

class ContrainteSalleTest {

	@Test
	void testContrainteSalle() {
		ContrainteSalle a = new ContrainteSalle(5, "ROOM_EXCLUSIVE", 4);
		assertEquals("ROOM_EXCLUSIVE",a.getType());
		assertEquals(5,a.getArg());
		assertEquals(4,a.getArg2());
	}



}
