package liaisonappliBDopta;

import java.util.ArrayList;
import java.sql.DriverManager;

/**
 * @author Mote
 *
 * @param <T>
 */
public class SQLList<T> extends ArrayList<Object> {

	private DriverManager Driver; 
	public SQLList() {
		
	}
	public SQLList(DriverManager Driver) {
		this.Driver = Driver;
	}
	
	
	
}
