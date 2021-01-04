package app;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import JFX.mote.Frame;
import JFX.mote.layout.Form;
import JFX.mote.layout.Popup;
import gui.page.Inscription;
import gui.page.Login;
import gui.page.MainApp;
import gui.page.TimeExamElement;
import gui.page.Validation;
import liaisonappliBDopta.Authentification;

public class Launcher {

	public static void main(String[] args) throws NumberFormatException, NamingException{
		System.out.println("app");
		Frame frame = new Frame("Projet 1");
		
		// 
		
		Inscription ins = new Inscription("", null);
		ins.setSubmitAction(event->{
			String user = ins.getUsername();
			String email = ins.getEmail();
			String pass = ins.getVerifiedPassWord();
			//Some Code
		});
		
		
		Validation val = new Validation("val", null);
		val.setSubmitAction(event->{
			String code = val.getCode();
			//Some Code
		});
		
		ins.setNext("val");
		// APP
		Login login = new Login("login");
		login.setErrorMessage("invalid");
		login.setSubmitAction(event->{
			Authentification a = new Authentification(login.getUsername(),login.getPassWord());
			if (a.getAutorise() == true) {
				login.next();
				Popup pop = new Popup("Bienvenue , "+ login.getUsername());
				pop.open();
			}
			else {
				login.setErrorMessage("Identifiant ou Mot de passe non valide");
			}
		});
		FormulaireExam b = new FormulaireExam("Nouveau EXAMEN");
		if (statuActuel == 1 || statuActuel == 3 )//secretariat or admin
			{
		b.setSubmitAction(event->{
			int duree = Integer.parseInt(b.getDuree());
			int type = Integer.parseInt(b.getType());
			try {
				new Examen(b.getNom(), b.getMat(), duree, type, b.getMateriel());
			} catch (NamingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		}
		
		lectureBD a = new lectureBD();
		List<TimeExamElement> diary = Arrays.asList(
				new TimeExamElement("F 021","Français",LocalDateTime.of(2021, 01, 01, 12, 30),LocalTime.of(1, 30),null)
				);
		 
		List<List<TimeExamElement>> diaries = Arrays.asList(
				diary,a.execute()
				);
		MainApp maz = new MainApp("app");
		System.out.println(diaries);
		maz.getCalendar().setDiaries(diaries);
		login.setNext("app");
		
		
		
		
		
		frame.setPanel(ins);
		frame.show();
	}
	


}
