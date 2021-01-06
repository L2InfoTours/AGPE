package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import liaisonappliBDopta.Examen;
import liaisonappliBDopta.Salle;

class SalleTest {

	@Test
	void testSalle() {
		Salle a = new Salle("F201", "pc", 210);
		assertEquals("qcm",a.getNom());
		assertEquals("pc",a.getMateriel());
		assertEquals(210,a.getCapacite());
	}
}
