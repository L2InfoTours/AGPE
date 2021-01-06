package test;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.jupiter.api.Test;

import liaisonappliBDopta.ContrainteExam;
import liaisonappliBDopta.ContrainteSalle;
import liaisonappliBDopta.Etudiant;
import liaisonappliBDopta.Examen;

class ExamenTest {

	@Test
	void testExamen() {
		try {
			Examen a = new Examen("qcm", "anglais", 210, 1, "pc");
			assertEquals("qcm",a.getNom());
			assertEquals("anglais",a.getMatiere());
			assertEquals(210,a.getDuree());
			assertEquals(1,a.getType());
			assertEquals("pc",a.getMateriel());
			
		} catch (NamingException | SQLException e) {
			fail("error");
			e.printStackTrace();
		}
	}

	@Test
	void testAddEtudiant() {
		try {
			Examen a = new Examen("qcm", "anglais", 210, 1, "pc");
			Examen a1 = new Examen("interrogation", "mathematique", 210, 1, "pc");
			Etudiant b = new Etudiant(21903697, "martin", "tom", "info", 2);
			a.addEtudiant(b);
			a1.addEtudiant(b);
			
			assertEquals(a.getEtudiants() , a1.getEtudiants());
			Etudiant b1 = new Etudiant(21903697, "martin", "tom", "info", 2);
			a1.addEtudiant(b1);
			assertEquals(false , a.getEtudiants().equals(a1.getEtudiants()));
			
			
		} catch (NamingException | SQLException e) {
			fail("erreur");
			e.printStackTrace();
		}
	}

	@Test
	void testAddListEtudiant() throws NamingException, SQLException {
		Examen a = new Examen("qcm", "anglais", 210, 1, "pc");
		Examen a1 = new Examen("interrogation", "mathematique", 210, 1, "pc");
		Etudiant b = new Etudiant(21903697, "martin", "tom", "info", 2);
		Etudiant b1 = new Etudiant(21903657, "martin", "tom", "info", 2);
		List<Etudiant> le = new ArrayList<Etudiant>();
		le.add(b);
		le.add(b1);
		a.addEtudiant(b);
		a.addEtudiant(b1);
		a1.addListEtudiant(le);
		
		assertEquals(true , a.getEtudiants().equals(a1.getEtudiants()));
	}

	

	@Test
	void testAddContrainteExam() {
		try {
			Examen a = new Examen("qcm", "anglais", 210, 1, "pc");
			Examen a1 = new Examen("interrogation", "mathematique", 210, 1, "pc");
			ContrainteExam e = new ContrainteExam(1, "AFTER", 3);
			a.addContrainteExam(e);
			a1.addContrainteExam(e);
			
			assertEquals(a.getContraintesExam() , a1.getContraintesExam());
			ContrainteExam e1 = new ContrainteExam(1, "AFTER", 3);
			a1.addContrainteExam(e1);
			assertEquals(false , a.getContraintesExam().equals(a1.getContraintesExam()));
			
			
		} catch (NamingException | SQLException e) {
			fail("erreur");
			e.printStackTrace();
		}
	}

	

	@Test
	void testAddListContrainteExam() throws NamingException, SQLException {
		Examen a = new Examen("qcm", "anglais", 210, 1, "pc");
		Examen a1 = new Examen("interrogation", "mathematique", 210, 1, "pc");
		ContrainteExam c = new ContrainteExam(1, "AFTER", 3);
		ContrainteExam c1 = new ContrainteExam(1, "AFTER", 3);
		List<ContrainteExam> lc = new ArrayList<ContrainteExam>();
		lc.add(c);
		lc.add(c1);
		a.addContrainteExam(c);
		a.addContrainteExam(c1);
		a1.addListContrainteExam(lc);
		
		assertEquals(true , a.getContraintesExam().equals( a1.getContraintesExam()));
	}

	

	@Test
	void testAddContrainteSalle() {
		try {
			Examen a = new Examen("qcm", "anglais", 210, 1, "pc");
			Examen a1 = new Examen("interrogation", "mathematique", 210, 1, "pc");
			ContrainteSalle s = new ContrainteSalle(1, "ROOM_EXCLUSIVE", 3);
			a.addContrainteSalle(s);
			a1.addContrainteSalle(s);
			
			assertEquals(a.getContraintesSalle() , a1.getContraintesSalle());
			ContrainteSalle s1 = new ContrainteSalle(1, "ROOM_EXCLUSIVE", 3);
			a1.addContrainteSalle(s1);
			assertEquals(false , a.getContraintesSalle().equals(a1.getContraintesSalle()));
			
			
		} catch (NamingException | SQLException e) {
			fail("erreur");
			e.printStackTrace();
		}
	}



	@Test
	void testAddListContrainteSalle() throws NamingException, SQLException {
		Examen a = new Examen("qcm", "anglais", 210, 1, "pc");
		Examen a1 = new Examen("interrogation", "mathematique", 210, 1, "pc");
		ContrainteSalle s = new ContrainteSalle(1, "ROOM_EXCLUSIVE", 3);
		ContrainteSalle s1 = new ContrainteSalle(1, "ROOM_EXCLUSIVE", 3);
		List<ContrainteSalle> ls = new ArrayList<ContrainteSalle>();
		ls.add(s);
		ls.add(s1);
		a.addContrainteSalle(s);
		a.addContrainteSalle(s1);
		a1.addListContrainteSalle(ls);
		
		assertEquals(true , a.getContraintesSalle().equals( a1.getContraintesSalle()));
	}

	


}
