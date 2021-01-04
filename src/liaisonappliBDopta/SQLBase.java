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
	private static ResultSet execute(String sql) {
		if(cn!=null) {
			try {
				Statement st = cn.createStatement();
				return st.executeQuery(sql);
			} catch (SQLException e) {
			}
		}
		return null;
	}
	public static List<String> getSalle() {
		List<String> ls = new ArrayList<String>();
		ResultSet rs = execute("Select SallesNom, SallesCapacite from salles");
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
		ResultSet rs = execute("SELECT Promo,Annee FROM eleves");
		if(rs != null) {			try {
				while (rs.next()) {
					String fil = rs.getString("Promo");
					String y = rs.getString("Annee");
					ls.add(y+fil);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ls = ls.stream().distinct().collect(Collectors.toList());
		return ls;
	}
	
}
