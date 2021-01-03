package gui.page;

import JFX.mote.components.Text;
import JFX.mote.components.Title;
import JFX.mote.controls.PassField;
import JFX.mote.controls.Submit;
import JFX.mote.controls.TextField;
import JFX.mote.layout.Form;
import javafx.scene.paint.Color;

public class Login extends Form{
	private TextField username;
	private PassField password;
	private Text errormsg;
	public Login(String name){
		super(name,null);
		add(new Title("Connection"));
		username = new TextField("Name2");
		add(username);
		password = new PassField("Name2");
		add(password);
		open();
	}
	public String getUsername(){
		return username.getText();
	}
	public String getPassWord (){
		return password.getText();
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
