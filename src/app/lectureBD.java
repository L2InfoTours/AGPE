package app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;

import gui.page.TimeExamElement;
import liaisonappliBDopta.Connexion;
import liaisonappliBDopta.ContrainteExam;
import liaisonappliBDopta.ContrainteSalle;
import liaisonappliBDopta.Etudiant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class lectureBD {
	 String url="jdbc:mysql://localhost/proj_exam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String login="root"; 
		String password="";
		Connection cn=null;
		Connection cn2=null;
		private List<TimeExamElement> diary = new ArrayList<TimeExamElement>();
		
		public lectureBD() {
			
		}
		private int i ;
		
		public List<TimeExamElement> execute() throws NumberFormatException, NamingException {
			
			String requete ="SELECT COUNT(ExamenID) FROM examen";
			i = 0 ;
			Statement st = null;
			ResultSet rsi = null ;
			try {
				cn= DriverManager.getConnection(url, login, password);
				st = cn.createStatement();
				rsi = st.executeQuery(requete);
				if (rsi.next()) {
				i = rsi.getInt("COUNT(ExamenID)");
				}
			}
			catch(SQLException e) {
				System.err.println("Erreur requ�te SQL");
				e.printStackTrace(); 
			}
			
			for ( int a = 1 ; a<(i+1) ; a++) {
			String requete2 = "SELECT YEAR(c.CreneauxDT),MONTH(c.CreneauxDT),DAY(c.CreneauxDT),HOUR(c.CreneauxDT),MINUTE(c.CreneauxDT),e.ExamenTitre,s.sallesNom FROM creneaux c INNER JOIN liaison l ON c.CreneauxID = l.CreneauxID INNER JOIN Examen e ON e.ExamenID = l.ExamenID INNER JOIN salles s ON s.sallesID = l.sallesID WHERE e.ExamenID ="+a+" ORDER BY e.ExamenID";
			System.out.println(requete2);
			
			ResultSet resultats = null ;
			Statement ste = null;
			try {
				System.out.println("ok");
				cn2= DriverManager.getConnection(url, login, password);
				ste = cn2.createStatement();
				resultats = ste.executeQuery(requete2);
				if (resultats.next()) {
					
				diary.add(new TimeExamElement(resultats.getString("s.sallesNom"),resultats.getString("e.ExamenTitre"),LocalDateTime.of(resultats.getInt("YEAR(c.CreneauxDT)"),resultats.getInt("MONTH(c.CreneauxDT)"),resultats.getInt("DAY(c.CreneauxDT)"), resultats.getInt("HOUR(c.CreneauxDT)"), resultats.getInt("MINUTE(c.CreneauxDT)")),LocalTime.of(1, 30),this.getStudentStr(getStudent(a))));
				System.out.println(resultats.getString("e.ExamenTitre"));
				}
				
			}
			catch(SQLException e) {
				System.err.println("Erreur requ�te SQL");
				e.printStackTrace(); 
			}
			}
			
						
			return  diary;
			
		}
		
		public List<String> getStudentStr(List<Etudiant> E){
			List<String> liste = new ArrayList<String>();
			for (Etudiant e : E) {
				String a = e.getNom() + " " + e.getPrenom();
				liste.add(a);
				System.out.println(a);
			}	
			return liste;
			
		}
		public List<Etudiant> getStudent(int i) throws NumberFormatException, NamingException{
			List<Etudiant> liste = new ArrayList<Etudiant>();
			
		
				String requete2 = "SELECT e.Promo,e.Annee,e.EleveNum , e.EleveNom, e.ElevePrenom FROM eleves e INNER JOIN contraintes c ON c.ContrainteArgument = e.EleveID WHERE c.ContrainteType = 5 AND c.ExamID ="+i+"";
				System.out.println(requete2);
				
				ResultSet resultats = null ;
				Statement ste = null;
				try {
					System.out.println("ok");
					cn2= DriverManager.getConnection(url, login, password);
					ste = cn2.createStatement();
					resultats = ste.executeQuery(requete2);
					while (resultats.next()) {
					
					Etudiant a = new Etudiant( resultats.getInt("e.EleveNum"), resultats.getString("e.EleveNom"), resultats.getString("e.ElevePrenom"), resultats.getString("e.Promo"), resultats.getInt("Annee"));
					liste.add(a);
					
					
					}
					
				}
				catch(SQLException e) {
					System.err.println("Erreur requ�te SQL");
					e.printStackTrace(); 
				}
				
				
				
			
			return liste;
			
		}

		public List<ContrainteExam> getCtrnExam(int i ){
			 List<ContrainteExam> liste = new ArrayList<ContrainteExam>();
			 String requeteAfter = "SELECT * FROM contraintes  WHERE ContrainteType = 1  AND ExamID ="+i+"";
			 String requeteCoincidence = "SELECT * FROM contraintes  WHERE ContrainteType = 2  AND ExamID ="+i+"";
			 String requeteExclusion = "SELECT * FROM contraintes  WHERE ContrainteType = 3  AND ExamID ="+i+"";
				
				
				ResultSet resultats4 = null ;
				ResultSet resultats2 = null ;
				ResultSet resultats3 = null ;
				Statement ste = null;
				try {
					System.out.println("ok");
					cn2= DriverManager.getConnection(url, login, password);
					ste = cn2.createStatement();
					resultats4 = ste.executeQuery(requeteAfter);
					
					
					while (resultats4.next()) {
					ContrainteExam a = new ContrainteExam(resultats4.getInt("ExamID"),"AFTER", resultats4.getInt("ContrainteArgument"));
					liste.add(a);
					}
					resultats2 = ste.executeQuery(requeteCoincidence);
					while (resultats2.next()) {
						ContrainteExam a = new ContrainteExam(resultats2.getInt("ExamID"),"EXAM_COINCIDENCE", resultats2.getInt("ContrainteArgument"));
						liste.add(a);
						}
					resultats3 = ste.executeQuery(requeteExclusion);
					while (resultats3.next()) {
						ContrainteExam a = new ContrainteExam(resultats3.getInt("ExamID"),"EXCLUSION", resultats3.getInt("ContrainteArgument"));
						liste.add(a);
						}
					
				}
				catch(SQLException e) {
					System.err.println("Erreur requ�te SQL");
					e.printStackTrace(); 
				}
			return liste;
		}
		public List<ContrainteSalle> getCtrnSalle(int i){
			 List<ContrainteSalle> liste = new ArrayList<ContrainteSalle>();
			 String requete2 = "SELECT * FROM contraintes WHERE ContrainteType = 0 AND ExamID ="+i+"";
				System.out.println(requete2);
				
				ResultSet resultats = null ;
				Statement ste = null;
				try {
					System.out.println("ok");
					cn2= DriverManager.getConnection(url, login, password);
					ste = cn2.createStatement();
					resultats = ste.executeQuery(requete2);
					while (resultats.next()) {
						ContrainteSalle a = new ContrainteSalle(resultats.getInt("ExamID"),"ROOM EXCLUSIVE", resultats.getInt("ContrainteArgument"));
						liste.add(a);
					}
					
				}
				catch(SQLException e) {
					System.err.println("Erreur requ�te SQL");
					e.printStackTrace(); 
				}
			return liste;
		}
	}