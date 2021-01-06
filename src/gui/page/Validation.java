package gui.page;

import JFX.mote.components.Text;
import JFX.mote.components.Title;
import JFX.mote.controls.TextField;
import JFX.mote.layout.Form;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class Validation extends Form {

	private TextField code;
	private Text errormsg;

	public Validation(String name, EventHandler<ActionEvent> onclick) {
		super(name, onclick);
		add(new Title("Connection"));
		code = new TextField("Code de Validation");
		add(code);
	}
	public String getCode() {
		return code.getText();
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
