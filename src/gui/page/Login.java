package gui.page;

import JFX.mote.components.Text;
import JFX.mote.components.Title;
import JFX.mote.controls.PassField;
import JFX.mote.controls.Submit;
import JFX.mote.controls.TextField;
import JFX.mote.layout.Form;
import JFX.mote.layout.ListLine;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Login extends Form{
	private TextField username;
	private PassField password;
	private Text errormsg;
	private ListLine a;
	public Login(String name){
		super(name,null);
		add(new Title("Connection"));
		username = new TextField("Utilisateur");
		add(username);
		password = new PassField("Mot de passe");
		add(password);
		a = new ListLine("Mot de Passe oublié", event->{
			System.out.println("mot de pass oublie");
		});
		add(a);
		open();
	}
	public String getUsername(){
		return username.getText();
	}
	public String getPassWord (){
		return password.getText();
	}
	public void setForgetAction(EventHandler<MouseEvent> event) {
		a.setOnMouseClicked(event);
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
}
