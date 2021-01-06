package JFX.mote.layout;

import JFX.mote.Component;
import JFX.mote.controls.Submit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class Form extends Panel<VBox>{
	protected Submit submit;
	
	/**
	 * The
	 * @param onclick
	 * was executed when the Submit Button is Pressed
	 */
	public void  setSubmitAction(EventHandler<ActionEvent> onclick) {
		submit.setOnclick(onclick);
	}
	/**
	 * @param name
	 * @param onclick
	 */
	public Form(String name,EventHandler<ActionEvent> onclick){
		super(new VBox(),name);
		if(onclick==null) {
			onclick = x->{this.next();};
		}
		submit = new Submit("Valider",onclick);
		layout.setAlignment(Pos.CENTER);
		setAlignment(Pos.CENTER);
	}
	/**
	 * open the form to access to submit Action 
	 */
	public void open() {
		add(submit);
		
	}
}
