package gui.page;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import JFX.mote.components.Text;
import JFX.mote.controls.Select;
import JFX.mote.controls.TimePicker;
import JFX.mote.controls.TimetableElement;
import JFX.mote.controls.Tree;
import JFX.mote.controls.TreeItem;
import JFX.mote.layout.Popup;

public class TimeExamElement extends TimetableElement {

	private String room;
	private String topic;
	
	public TimeExamElement(String room,String topic, LocalDateTime date, LocalTime duration, List<Object> members) {
		super(room+"\n"+topic, date,duration);
		this.room = room;
		this.topic = topic;
	}
	public void createPanel(){
		Popup pop = new Popup("Ajouter un Examen");
		CreationPanel cp = new CreationPanel();
		// Some Code
		cp.setSubmitAction(event->{
			
			// Some Code			
			pop.close();
		});
		cp.open();
		pop.add(cp);
		pop.open();
	}
	@Override
	public void open() {
		Popup a = new Popup(getName());
		a.add(new Text(getDate().format(DateTimeFormatter.ofPattern("hh:mm dd/MM/YY"))));
		a.add(new Text(getDur().format(DateTimeFormatter.ofPattern("hh:mm"))));
		a.open();
	}
}
