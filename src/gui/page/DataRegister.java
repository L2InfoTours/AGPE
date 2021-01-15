package gui.page;

import JFX.mote.controls.Button;
import JFX.mote.controls.Select;
import JFX.mote.controls.Spinner;
import JFX.mote.layout.Form;
import JFX.mote.layout.Panel;
import JFX.mote.layout.Popup;
import javafx.scene.layout.VBox;

public class DataRegister extends Panel<VBox>{

	private Button Btn_Salle;

	public DataRegister(String name) {
		super(name);
		Btn_Salle = new Button("Ajouter Salle", event->{
			Popup pop = new Popup("Ajouter Un Salle");
			Form form = new Form(name, null);
			form.add(new Select("Batiment"));
			form.add(new Spinner("Numero"));
			pop.add(form);
			pop.open();
		});
		add(Btn_Salle);
	}

}
