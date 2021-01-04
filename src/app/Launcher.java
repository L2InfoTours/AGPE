package app;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;

import JFX.mote.Frame;
import JFX.mote.layout.Form;
import JFX.mote.layout.Popup;
import gui.page.Inscription;
import gui.page.Login;
import gui.page.MainApp;
import gui.page.MenuApp;
import gui.page.TimeExamElement;
import gui.page.Validation;
import liaisonappliBDopta.Authentification;

public class Launcher {

	public static void main(String[] args) throws NumberFormatException, NamingException{

		Frame frame = new Frame("Projet 1");
		
		//Inscription 
		Inscription inscription = new Inscription("inscription", null);
		inscription.setSubmitAction(event->{
			String user = inscription.getUsername();
			String email = inscription.getEmail();
			//Some Code
		});
		
		//Validation
		Validation val = new Validation("val", null);
		val.setSubmitAction(event->{
			String code = val.getCode();
			//Some Code
		});
		//MainApp
		lectureBD a = new lectureBD();
		List<TimeExamElement> diary = Arrays.asList(
				new TimeExamElement("F 021","Français",LocalDateTime.of(2021, 01, 01, 12, 30),LocalTime.of(1, 30),null)
				);
		
		List<List<TimeExamElement>> diaries = Arrays.asList(
				diary,a.execute()
				);
		MainApp maz = new MainApp("calendar");
		maz.getCalendar().setDiaries(diaries);
		
		// Login
		Login login = new Login("login");
		login.setErrorMessage("invalid");
		login.setSubmitAction(event->{
			Authentification Authen = new Authentification(login.getUsername(),login.getPassWord());
			if (Authen.getAutorise()) {
				MenuApp menu = new MenuApp("menu", login.getUsername());
				menu.setApp(Arrays.asList(
						"inscription",
						"calendar"
						));
				login.setNext("menu");
				login.next();
			}
			else {
				login.setErrorMessage("Identifiant ou Mot de passe non valide");
			}
		});
	

		frame.setPanel(login);
		frame.show();
	}
	


}
