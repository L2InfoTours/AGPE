package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import liaisonappliBDopta.Authentification;

class AuthetificationTest {

	@Test
	void testAuthentification() {
		Authentification a = new Authentification("Tom", "deusvult"); //admin
		Authentification a1 = new Authentification("scolarite", "scolarite"); //scolarite
		Authentification a2 = new Authentification("secretariat", "secretariat"); //secretariat
		
		Authentification b = new Authentification("Tom", "faux");
		Authentification c = new Authentification("faux", "deusvult");
		Authentification d = new Authentification("faux", "faux");
		Authentification e = new Authentification("faux", null);
		Authentification f = new Authentification(null, "faux");
		
		assertEquals(true , a.getAutorise());
		assertEquals(false ,b.getAutorise());
		assertEquals(false ,c.getAutorise());
		assertEquals(false ,d.getAutorise());
		assertEquals(false ,e.getAutorise());
		assertEquals(false ,f.getAutorise());
		
		assertEquals(3,a.getStatut());
		assertEquals(2,a1.getStatut());
		assertEquals(1,a2.getStatut());
		assertEquals(0,b.getStatut());
	}

}
