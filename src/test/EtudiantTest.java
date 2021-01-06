package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.jupiter.api.Test;

import liaisonappliBDopta.Etudiant;

class EtudiantTest {

	@Test
	void testEtudiant() throws NamingException, SQLException {
		Etudiant a = new Etudiant(21903697, "martin", "tom", "info", 2);
		assertEquals(21903697,a.getNumeroetu());
		assertEquals("martin",a.getNom());
		assertEquals("tom",a.getPrenom());
		assertEquals("info",a.getPromo());
		assertEquals(2,a.getAnnee());
	}

	

}
