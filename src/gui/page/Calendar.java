package gui.page;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

import JFX.mote.Element;
import JFX.mote.Position;
import JFX.mote.controls.Button;
import JFX.mote.controls.DatePicker;
import JFX.mote.controls.Switch;
import JFX.mote.controls.Timetable;
import JFX.mote.controls.TimetableElement;
import JFX.mote.layout.Flex;
import JFX.mote.layout.Panel;
import JFX.mote.layout.Popup;
import javafx.scene.layout.BorderPane;

public class Calendar extends Panel<BorderPane>{
	Timetable<?> table;
	public Calendar() {
		super(new BorderPane());
	}
	private Flex flex;
	private List<List<TimeExamElement>> diaries;
	public void setDiaries(List<List<TimeExamElement>> list) {
		this.diaries = list;
		updateDiaries();
	}
		public void updateDiaries() {
			if(loaded) {
				flex.clear();
				if(diaries!=null) {					
					diaries.forEach(diary->{
						Timetable<TimeExamElement> temp = new Timetable<TimeExamElement>(diary);
						flex.add(temp);
					});
				}
			}
		}
	public void init() {
		loaded = true;
		Switch viewmode = new Switch("colonne","ligne");
		flex = new Flex();
		viewmode.setOnchange(x->{
			flex.setType(x.isValue()?Flex.Column:Flex.Row);
			flex.getContent().forEach(y->{
				((Flex) y).setType(x.isValue()?Flex.Row:Flex.Column);
			});
			return true;
		});
		updateDiaries();
		flex.position = Position.Center;
		flex.setFollow(true);
		flex.setSize(640, 512);
		
		Flex tool = new Flex();
		tool.setType(Flex.Row);
		tool.position = Position.Top;
		tool.add(viewmode);
		tool.add(new Button("Ajouter",x->{
			Popup pop = new Popup("Ajouter un Examen");
			CreationPanel cp = new CreationPanel();
			cp.setSubmitAction(event->{		
				pop.close();
			});
			cp.open();
			pop.add(cp);
			pop.open();
		}));
		add(tool);
		add(flex);
		setSize(720, 640);
	}
	public void start(){
		table.start();
	}
	public void setLookup(int startHour,int nHours,int startDay,int nDays) {
		flex.getContent().forEach(x->{
			((Timetable<?>) x).setLookup(startHour,nHours,startDay,nDays);
		});
	}
	public void setLookup(LocalDate localdate) {
		flex.getContent().forEach(x->{
			((Timetable<?>) x).setLookup(localdate);
		});
	}
	
}
