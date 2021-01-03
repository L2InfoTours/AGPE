package gui.page;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import JFX.mote.controls.Select;
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
		System.out.println("Open");
		Popup pop = new Popup("Ajouter un Examen");
	//	pop.add(new TextField("Nom"));
//		pop.add(new Spinner(0, 0, 0, 0));
		pop.add(new Select(Arrays.asList("Aa","Bb","Cc")));
		Tree a = new Tree();
		a.add("A1");
		a.add("A2");
		a.add("A3");
		TreeItem b = a.createSection("élèves");
		b.add("A");
		b.add("B");
		b.add("C");
		pop.add(a);
		//pop.add(new TimePicker());
		pop.open();
	}
	
}
