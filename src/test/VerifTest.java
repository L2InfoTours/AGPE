package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import liaisonappliBDopta.Verif;

class VerifTest {

	
	
	@Test
	void testVerification() {
		Verif a = new Verif();
		assertEquals(true,a.Verification("Tom", "tom.martin@gmail.com"));
		assertEquals(true,a.Verification("Tom", "asss@gmail.com"));
		assertEquals(true,a.Verification("ahhh", "tom.martin@gmail.com"));
		assertEquals(false,a.Verification("ahhh","asss@gmail.com"));
		
	}

}
