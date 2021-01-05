package liaisonappliBDopta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Verif {
     String url="jdbc:mysql://localhost/proj_exam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String login="root"; 
        String password="";
        Connection cn=null;
        
    public Verif() {
        }

    public boolean Verification(String username , String mail){
        ResultSet resultats = null ;
        
        String requete = "SELECT LoginUser,LoginMail FROM login WHERE ( LoginUser = '"+username+"' OR LoginMail = '"+mail+"')";
        System.out.println(requete);
        Statement st = null;
       
        try {
            cn= DriverManager.getConnection(url, login, password);
            st = cn.createStatement();
            resultats = st.executeQuery(requete);
            
            if (resultats.next()) {
                return true;
            }
            else {
                return false;
            }
        }
        catch(SQLException e) {
            System.err.println("Erreur requète SQL");
            e.printStackTrace(); 
            return false;
        }

        
        finally {
            try {
                cn.close();
                st.close();
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        
    }
    
    
}