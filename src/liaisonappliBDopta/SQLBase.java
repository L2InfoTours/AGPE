package liaisonappliBDopta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
		ResultSet rs = Query("SELECT Nom FROM Matiere");
		if(rs != null) {			try {
				while (rs.next()) {
					String nom= rs.getString("Nom");
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
		ResultSet rs = Query("SELECT Nom FROM Materiel");
		if(rs != null) {			try {
				while (rs.next()) {
					String nom= rs.getString("Nom");
					ls.add(nom);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ls = ls.stream().distinct().collect(Collectors.toList());
		return ls;
	}
	public static List<String> getElevesIn(String x) {
		List<String> ls = new ArrayList<String>();
		String a = x.split(" ")[0].substring(1);
		String b = x.substring(2+a.length());
		System.out.println("SELECT EleveNom,ElevePrenom FROM eleves Where Annee = "+a+" AND Promo = '"+b+"'");
		ResultSet rs = Query("SELECT EleveNom,ElevePrenom,EleveNum FROM eleves Where Annee = "+a+" AND Promo = '"+b+"'");
		if(rs != null) {			try {
				while (rs.next()) {
					String nom= rs.getString("EleveNom");
					String prenom= rs.getString("ElevePrenom");
					String num= rs.getString("EleveNum");
					ls.add(nom+"\t"+prenom+"\t"+num);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ls = ls.stream().distinct().collect(Collectors.toList());
		return ls;
	}
	
}
