package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.jupiter.api.Test;

import liaisonappliBDopta.CrenauHoraire;

class CrenauHoraireTest {

	@Test
	void testCrenauHoraire() throws NamingException, SQLException {
		CrenauHoraire c1 = new CrenauHoraire("13:05:2010", "09:00:00", 210);
		
		assertEquals(210,c1.getDuree());
		assertEquals("09:00:00",c1.getHeure());
		assertEquals("13:05:2010",c1.getDate());
		
	}
	@Test
	void testFormate() throws NamingException, SQLException {
		CrenauHoraire c1 = new CrenauHoraire("13:05:2010", "09:00:00", 210);
		CrenauHoraire c2 = new CrenauHoraire("26:06:2021", "22:00:00", 150);
		
		String s1 = c1.formate();
		String s2 = c2.formate();
		
		String r1 = "2010-05-13 09:00:00";
		String r2 = "2021-06-26 22:00:00";
		String r3 = "akqlsjdpowxckwknnen";
		String r4 = "2021:06:26 22:00:00";
		assertEquals(true,s1.equals(r1));
		assertEquals(true,s2.equals(r2));
		assertEquals(false,s1.equals(r3));
		assertEquals(false,s2.equals(r4));
	}
}