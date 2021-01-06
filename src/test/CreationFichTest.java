package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.jupiter.api.Test;

import liaisonappliBDopta.CreationFich;
import liaisonappliBDopta.CrenauHoraire;
import liaisonappliBDopta.Etudiant;
import liaisonappliBDopta.Examen;
import liaisonappliBDopta.Salle;

class CreationFichTest {

	@Test
	void testCreerFile() throws NamingException, SQLException, IOException {
		List<Etudiant> let = new ArrayList<Etudiant>();
		Etudiant etu = new Etudiant(21903697, "martin", "tom", "info", 2);
		let.add(etu);
		List<Examen> le1 = new ArrayList<Examen>();
		List<Examen> le2 = new ArrayList<Examen>();
		Examen e1 = new Examen("aaa", "informatique", 150, 0, null);
		Examen e2 = new Examen("qcm", "génie logiciel", 210, 0, null);
		e1.addListEtudiant(let);
		e2.addListEtudiant(let);
		le1.add(e1);
		le2.add(e2);
		List<Salle> ls1 = new ArrayList<Salle>();
		List<Salle> ls2 = new ArrayList<Salle>();
		Salle s1 = new Salle("F201", null, 100);
		Salle s2 = new Salle("F205", null, 150);
		ls1.add(s1);
		ls2.add(s2);
		List<CrenauHoraire> lc1 = new ArrayList<CrenauHoraire>();
		List<CrenauHoraire> lc2 = new ArrayList<CrenauHoraire>();
		CrenauHoraire c1 = new CrenauHoraire("13:05:2010", "09:00:00", 210);
		CrenauHoraire c2 = new CrenauHoraire("26:06:2021", "22:00:00", 150);
		lc1.add(c1);
		lc2.add(c2);
		
		String doss1 = "data/testexam1.exam";
		String doss2 = "data/testexam2.exam";
		String doss3 = "data/testexam3.exam";
		String doss4 = "data/testexam4.exam";
		String doss5 = "data/testexam5.exam";
		
		CreationFich a = new CreationFich(le1,lc1,ls1,doss1);
		CreationFich b = new CreationFich(le1,lc1,ls1,doss2);
		CreationFich c = new CreationFich(le2,lc1,ls1,doss3);
		CreationFich d = new CreationFich(le1,lc2,ls1,doss4);
		CreationFich e = new CreationFich(le1,lc1,ls2,doss5);
		a.creerFile();
		b.creerFile();
		c.creerFile();
		d.creerFile();
		e.creerFile();
		
		String b1 = "";
		String b2 = "";
		String b3 = "";
		String b4 = "";
		String b5 = "";
		
		String r1 = "";
		String r2 = "";
		String r3 = "";
		String r4 = "";
		String r5 = "";
		
		InputStream ips1=new FileInputStream(doss1); 
		InputStreamReader ipsr1=new InputStreamReader(ips1);
		BufferedReader br1=new BufferedReader(ipsr1);
		while ((b1 = br1.readLine()) != null) {
	           r1 += b1;
		}
		br1.close();
		InputStream ips2=new FileInputStream(doss2); 
		InputStreamReader ipsr2=new InputStreamReader(ips2);
		BufferedReader br2=new BufferedReader(ipsr2);
		while ((b2 = br2.readLine()) != null) {
	           r2 += b2;
		}
		br2.close();
		InputStream ips3=new FileInputStream(doss3); 
		InputStreamReader ipsr3=new InputStreamReader(ips3);
		BufferedReader br3=new BufferedReader(ipsr3);
		while ((b3 = br3.readLine()) != null) {
	           r3 += b3;
		}
		br3.close();
		InputStream ips4=new FileInputStream(doss4); 
		InputStreamReader ipsr4=new InputStreamReader(ips4);
		BufferedReader br4=new BufferedReader(ipsr4);
		while ((b4 = br4.readLine()) != null) {
	           r4 += b4;
		}
		br4.close();
		InputStream ips5=new FileInputStream(doss5); 
		InputStreamReader ipsr5=new InputStreamReader(ips5);
		BufferedReader br5 =new BufferedReader(ipsr5);
		while ((b5 = br5.readLine()) != null) {
	           r5 += b5;
		}
		br5.close();
	
	assertEquals(true,r1.equals(r2));
	assertEquals(false,r1.equals(r3));
	assertEquals(false,r1.equals(r4));
	assertEquals(false,r1.equals(r5));

	
	}
}
