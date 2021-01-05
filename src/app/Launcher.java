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
import liaisonappliBDopta.CreationFich;
import liaisonappliBDopta.SQLBase;
import liaisonappliBDopta.Verif;
import sendMails.ClassMail;

public class Launcher {

	public static void main(String[] args) throws NumberFormatException, NamingException{
		SQLBase.connection();
		PrepFich po = new PrepFich();
		CreationFich pa = po.Recup("data/exam_import.exam");
		pa.creerFile();
		Frame frame = new Frame("Projet 1");
		
		//Inscription 
		Inscription inscription = new Inscription("inscription", null);
		inscription.setSubmitAction(event->{
			String user = inscription.getUsername();
			String email = inscription.getEmail();
			String accType = inscription.getAccType();
			//Some Code
			Verif a = new Verif();
            if(a.Verification(user, email)==false) {
                String pass="";
                final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                int j = (int)(Math.random() * 13);
                for(int i=0;i<j;i++) {
                    int randomInt = (int)(Math.random() * chars.length() + 1);
                    pass = pass + chars.charAt(randomInt);
                }
                try {
                    ClassMail.sendMail(email,"Création de votre compte","Bonjour,\nVotre compte du groupe "+accType+" a été crée.\nVotre mot de passe est:\n"+pass);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else {
                System.out.println("Compte déjà existant.");
                inscription.setErrorMessage("Un compte avec des informations similaires existe déjà.");
            }
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
				diary,
				a.execute()
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
