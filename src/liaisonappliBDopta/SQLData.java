package liaisonappliBDopta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLData {
	static String url="jdbc:mysql://localhost/proj_exam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String login="root"; 
	static String password="";
	static Connection cn;
		
	private static void connection() {
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
				return cn.createStatement().executeQuery(sql);
				
			} catch (SQLException e) {
			}
		}
		return null;
	}
	public static List<String> getSalle() {
		ResultSet rs = execute("Select * from salles");
		System.out.println(rs);
		return null;
	}
	
	
}
