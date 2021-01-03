package JFX.mote.layout;

import JFX.mote.Component;
import JFX.mote.controls.Submit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class Form extends Panel<VBox>{
	private Submit submit;
	public void  setSubmitAction(EventHandler<ActionEvent> onclick) {
		submit.setOnclick(onclick);
	}
	public Form(String name,EventHandler<ActionEvent> onclick){
		super(new VBox(),name);
		if(onclick==null) {
			onclick = x->{this.next();};
		}
		submit = new Submit("Name",onclick);
		layout.setAlignment(Pos.CENTER);
		setAlignment(Pos.CENTER);
	}
	public void open() {
		add(submit);
		
	}
}
