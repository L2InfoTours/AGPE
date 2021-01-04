package gui.page;

import java.util.ArrayList;
import java.util.Arrays;

import JFX.mote.components.Text;
import JFX.mote.components.Title;
import JFX.mote.controls.Email;
import JFX.mote.controls.PassField;
import JFX.mote.controls.Select;
import JFX.mote.controls.TextField;
import JFX.mote.layout.Form;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class Inscription extends Form{

	//private PassField password;
	private TextField username;
	//private PassField password1;
	private TextField mail;
	private Text errormsg;
	private Select acc;
	public Inscription(String name, EventHandler<ActionEvent> onclick) {
		super(name,null);
		add(new Title("Inscription"));
		username = new TextField("Username");
		add(username);
		mail = new Email("Email");
		add(mail);
		acc = new Select(Arrays.asList("Secretariat","Scolarité","Admin"));
		add(acc);
		/*
		password = new PassField("Mot de Passe");
		add(password);
		password1 = new PassField("Repeter le Mot de Passe");
		add(password1);
		*/
		open();
	}
	public String getUsername(){
		return username.getText();
	}
	/*
	public String getVerifiedPassWord (){
		if(password.getText().contentEquals(password1.getText())) {
			return password.getText();
		}else {
			setErrorMessage("Les Mots de Passes ne corresponde pas");
		}
		return null;
	}
	*/
	public String getAccType() {
		return acc.getValue();
	}
	public void setErrorMessage(String value) {
		if(errormsg==null) {
			errormsg = new Text(value);
			errormsg.setColor(Color.RED);
			add(errormsg);
		}else {
			errormsg.setText(value);
		}
	}
	public String getEmail() {
		return mail.getText();
	}
}
