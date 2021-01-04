package gui.page;

import JFX.mote.components.Text;
import JFX.mote.components.Title;
import JFX.mote.controls.PassField;
import JFX.mote.controls.TextField;
import JFX.mote.layout.Form;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class Inscription extends Form{

	private PassField password;
	private TextField username;
	private PassField password1;
	private TextField mail;
	private Text errormsg;
	public Inscription(String name, EventHandler<ActionEvent> onclick) {
		super(name,null);
		add(new Title("Inscription"));
		username = new TextField("Username");
		add(username);
		mail = new TextField("Email");
		add(mail);
		password = new PassField("Mot de Passe");
		add(password);
		password1 = new PassField("Repeter le Mot de Passe");
		add(password1);
		open();
	}
	public String getUsername(){
		return username.getText();
	}
	public String getVerifiedPassWord (){
		if(password.getText().contentEquals(password1.getText())) {
			return password.getText();
		}else {
			setErrorMessage("Les Mots de Passes ne corresponde pas");
		}
		return null;
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
