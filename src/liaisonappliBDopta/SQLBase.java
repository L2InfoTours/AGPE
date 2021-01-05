package liaisonappliBDopta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.naming.NamingException;

import JFX.mote.controls.TreeItem;
import gui.page.Side;
import gui.page.TimeExamElement;

public class SQLBase {
	static String url="jdbc:mysql://localhost/proj_exam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String login="root"; 
	static String password="";
	static Connection cn;
		
	public static void connection() {
		if(cn==null) {
			try {
				cn = DriverManager.getConnection(url, login, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private static ResultSet Query(String sql) {
		if(cn!=null) {
			try {
				Statement st = cn.createStatement();
				return st.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	private static int Update(String sql) {
		if(cn!=null) {
			try {
				Statement st = cn.createStatement();
				return st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	public static List<String> getSalle() {
		List<String> ls = new ArrayList<String>();
		ResultSet rs = Query("Select SallesNom, SallesCapacite from salles");
		if(rs != null) {			try {
				while (rs.next()) {
					String name = rs.getString("SallesNom");
					String cap = rs.getString("SallesCapacite");
					ls.add(name+"\t\t\t"+cap);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ls = ls.stream().distinct().collect(Collectors.toList());
		return ls;
	}
	public static List<String> getPromo() {
		List<String> ls = new ArrayList<String>();
		ResultSet rs = Query("SELECT Promo,Annee FROM eleves");
		if(rs != null) {			try {
				while (rs.next()) {
					String fil = rs.getString("Promo");
					int y = rs.getInt("Annee");
					String L = y<3?"L"+y:"M"+(y-3);
					ls.add(L+" "+fil);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ls = ls.stream().distinct().collect(Collectors.toList());
		return ls;
	}
	public static List<String> getTopics() {
		List<String> ls = new ArrayList<String>();
		ResultSet rs = Query("SELECT Name FROM Matiere");
		if(rs != null) {			try {
				while (rs.next()) {
					String nom= rs.getString("Name");
					ls.add(nom);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ls = ls.stream().distinct().collect(Collectors.toList());
		return ls;
	}
	public static List<String> getMateriel() {
		List<String> ls = new ArrayList<String>();
		ResultSet rs = Query("SELECT Name FROM Materiel");
		if(rs != null) {			try {
				while (rs.next()) {
					String nom= rs.getString("Name");
					ls.add(nom);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ls = ls.stream().distinct().collect(Collectors.toList());
		return ls;
	}
	public static List<Object[]> getElevesIn(String x) {
		List<Object[]> ls = new ArrayList<Object[]>();
		String a = x.split(" ")[0].substring(1);
		String b = x.substring(2+a.length());
		System.out.println("SELECT EleveNom,ElevePrenom FROM eleves Where Annee = "+a+" AND Promo = '"+b+"'");
		ResultSet rs = Query("SELECT EleveNom, ElevePrenom, EleveNum, Promo, Annee FROM eleves Where Annee = "+a+" AND Promo = '"+b+"'");
		if(rs != null) {			try {
				while (rs.next()) {
					String nom= rs.getString("EleveNom");
					String prenom= rs.getString("ElevePrenom");
					int numero= rs.getInt("EleveNum");
					String filliere= rs.getString("Promo");
					int annee= rs.getInt("Annee");
					Object[] ob = new Object[2];
					try {
						Etudiant et = new Etudiant(numero, nom, prenom, filliere, annee);
						ob[0] = (et);
					} catch (NamingException e) {
					}
					String f = nom+"\t"+prenom+"\t"+numero;
					ob[1] = f;
					ls.add(ob);						
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ls = ls.stream().distinct().collect(Collectors.toList());
		return ls;
	}
	// rewrite from lecture BD
	public static List<List<TimeExamElement>> getExams(List<TreeItem> values) {
		List<List<TimeExamElement>> ls = new ArrayList<List<TimeExamElement>>();
			values.forEach(value->{
				String a;
				String[] c;
				String b;
				switch (value.getParent()) {
				case Side.strPromo:
					a = value.getText();
					c = a.split(" ");
					a = c[0];
					b = c[c.length-1];
					
					int d = Integer.parseInt(a.substring(1));
					a = a.substring(0,1);
					d = a.charAt(0)=='L'?d:d+3;
					ls.add(getExamWithPromo(d+"", b));
					break;
				case Side.strSalle:
					a = value.getText();
					c = a.split("\t");
					a = c[0];
					b = c[c.length-1];
					ls.add(getExamWithSalle(a,b));					
					break;
				}
			});
		return ls;
	}
	static List<TimeExamElement> getExamWithPromo(String annee,String filiere){
		List<TimeExamElement> ls = new ArrayList<TimeExamElement>();
		String sql = "SELECT Distinct "+
				"YEAR(c.CreneauxDT),MONTH(c.CreneauxDT),DAY(c.CreneauxDT),e.ExamenID,"+
				"HOUR(c.CreneauxDT),MINUTE(c.CreneauxDT),c.CreneauxLength,e.ExamenTitre,"+
				"s.sallesNom FROM creneaux c INNER JOIN liaison l ON "+
				"c.CreneauxID = l.CreneauxID INNER JOIN Examen e ON "+
				"e.ExamenID = l.ExamenID INNER JOIN salles s ON "+
				"s.sallesID = l.sallesID INNER JOIN contraintes cn ON "+
				"e.ExamenID = cn.ExamID "+
				"INNER JOIN Eleves el ON cn.ContrainteArgument = el.EleveID "+
				"WHERE el.Annee = '"+annee+"' AND el.Promo = '"+filiere+"'";
		System.out.println(sql);
		ResultSet rs = Query(sql);
		System.out.println(rs);
		if(rs != null) {
			try {
				while (rs.next()) {
					int M = rs.getInt("c.CreneauxLength");
					int m = M%60;
					int h = (M-m)/60;
					ls.add(new TimeExamElement(
							rs.getString("s.sallesNom"),
							rs.getString("e.ExamenTitre"),
							LocalDateTime.of(
									rs.getInt("YEAR(c.CreneauxDT)"),
									rs.getInt("MONTH(c.CreneauxDT)"),
									rs.getInt("DAY(c.CreneauxDT)"),
									rs.getInt("HOUR(c.CreneauxDT)"),
									rs.getInt("MINUTE(c.CreneauxDT)")
							),
							LocalTime.of(h, m),
							getStudentStr(getStudent(rs.getInt("e.ExamenID")))));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ls;
	}
	static List<TimeExamElement> getExamWithSalle(String name,String capacity){
		List<TimeExamElement> ls = new ArrayList<TimeExamElement>();
		String sql = "SELECT Distinct "+
				"YEAR(c.CreneauxDT),MONTH(c.CreneauxDT),DAY(c.CreneauxDT),e.ExamenID,"+
				"HOUR(c.CreneauxDT),MINUTE(c.CreneauxDT),c.CreneauxLength,e.ExamenTitre,"+
				"s.sallesNom,s.sallesCapacite  FROM creneaux c INNER JOIN liaison l ON "+
				"c.CreneauxID = l.CreneauxID INNER JOIN Examen e ON "+
				"e.ExamenID = l.ExamenID INNER JOIN salles s ON "+
				"s.sallesID = l.sallesID "+
				"WHERE s.sallesNom = '"+name+"' AND s.sallesCapacite = '"+capacity+"'";
		System.out.println(sql);
		ResultSet rs = Query(sql);
		if(rs != null) {
			try {
				while (rs.next()) {
					int M = rs.getInt("c.CreneauxLength");
					int m = M%60;
					int h = (M-m)/60;
					ls.add(new TimeExamElement(
							rs.getString("s.sallesNom"),
							rs.getString("e.ExamenTitre"),
							LocalDateTime.of(
									rs.getInt("YEAR(c.CreneauxDT)"),
									rs.getInt("MONTH(c.CreneauxDT)"),
									rs.getInt("DAY(c.CreneauxDT)"),
									rs.getInt("HOUR(c.CreneauxDT)"),
									rs.getInt("MINUTE(c.CreneauxDT)")
							),
							LocalTime.of(h, m),
							getStudentStr(getStudent(rs.getInt("e.ExamenID")))));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("aa "+ls);
		return ls;
	}
	
	//copy from lecture DB
	public static List<String> getStudentStr(List<Etudiant> E){
		List<String> liste = new ArrayList<String>();
		for (Etudiant e : E) {
			String a = e.getNom() + " " + e.getPrenom();
			liste.add(a);
			System.out.println(a);
		}	
		return liste;
		
	}
	//copy from lecture DB
	public static List<Etudiant> getStudent(int i){
		List<Etudiant> liste = new ArrayList<Etudiant>();
		String requete = "SELECT e.Promo,e.Annee,e.EleveNum , e.EleveNom, e.ElevePrenom FROM eleves e INNER JOIN contraintes c ON c.ContrainteArgument = e.EleveID WHERE c.ContrainteType = 5 AND c.ExamID ="+i+"";
		ResultSet rs;
			try {
				rs = Query(requete);
				while (rs.next()) {
					Etudiant a = new Etudiant(
							rs.getInt("e.EleveNum"),
							rs.getString("e.EleveNom"),
							rs.getString("e.ElevePrenom"),
							rs.getString("e.Promo"),
							rs.getInt("Annee"));
					liste.add(a);
				}	
			}
			catch(Exception e) {
				e.printStackTrace(); 
			}

		return liste;
	}
}
