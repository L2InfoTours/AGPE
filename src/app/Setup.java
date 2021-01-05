package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import JFX.mote.Frame;
import JFX.mote.components.Text;
import JFX.mote.controls.PassField;
import JFX.mote.controls.TextField;
import JFX.mote.layout.Form;

/**
 * @author Mote
 *
 */
public class Setup {
	private final static String protocole = "jdbc:mysql://";
	private final static String param = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String url;
	private static String DB = "proj_exam";
	private static String login; 
	private static String password;
	private static Connection cn;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Frame frame = new Frame("Installation");
		Form IDB = new Form("Installation Base de Donnée", null);
		TextField IPDB = new TextField("IP Base de Donné");
		Text NDB = new Text(DB);
		//TextField NDB = new TextField("Nom Base de Donné");
		TextField user = new TextField("Nom Utilisateur");
		PassField pass = new PassField("Mot de Passe");
		Text error = new Text("");
		IDB.add(IPDB,NDB,user,pass);
		user.setOnchange(x->IDB.open());
		IDB.setSubmitAction(onclick->{
			url = IPDB.getText();
			url = url==""?"localhost":url;
			//DB = NDB.getText();
			DB = DB==""?"proj_exam":DB;
			login = user.getText();
			password = pass.getText();
			url = protocole+url+"/"+DB+param;
			connection();
			if(cn==null) {
				error.setText("Impossible de se connection une information doit etre erroné");
			}else {
				IDB.next();
				System.out.println(DB);
				Use(DB);
				importSQL("data/proj_exam");
				
			}
		});
		IDB.setNext("");
		
		
		
		frame.setPanel(IDB);
		frame.show();
	}
	private static void importSQL(String string) {
		// TODO Auto-generated method stub
		
	}
	private static void Use(String dB2) {
		Update("Use "+dB2);
	}
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
	private static void Update(String sql) {
		int a = -1;
		System.out.println(sql);
		if(cn!=null) {
			try {
				Statement st = cn.createStatement();
				a = st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(a);
	}
	public static void CreateTable(String name,String ...lines) {
		List<String> ls = Arrays.asList(lines);
		String sql = "DROP TABLE `"+name+"`;CREATE TABLE `"+name+"` (";
		for(String l : ls) {
			sql+=l;
		}
		sql += ") ENGINE=MyISAM DEFAULT CHARSET=latin1;";
		Update(sql);
	}
}
