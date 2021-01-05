package gui.page;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import JFX.mote.Position;
import JFX.mote.controls.DatePicker;
import JFX.mote.controls.TimetableElement;
import JFX.mote.controls.Tree;
import JFX.mote.controls.TreeItem;
import JFX.mote.layout.Panel;
import JFX.mote.layout.PanelManager;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

public class MainApp extends Panel<BorderPane>{
	public MainApp(String name) {
		super(new BorderPane(),name);
		side = new Side();
		calendar = new Calendar();
	}
	private Calendar calendar;
	private Side side;
	private Function<List<TreeItem>,List<List<TimeExamElement>>> Listen = e->{
		List<TimeExamElement> diary = Arrays.asList(
				new TimeExamElement("F 021","Français",LocalDateTime.of(2021, 01, 01, 12, 30),LocalTime.of(1, 30),null)
				);
		return Arrays.asList(diary);
	};
	public Calendar getCalendar() {
		return calendar;
	}
	public Side getSide() {
		return side;
	}
	public void init() {
		side.position = Position.Left;
		add(side);
		calendar.position = Position.Center;
		add(calendar);
		//calendar.setLookup(8,12,3,1);
		DatePicker dp = (DatePicker) PanelManager.get("datepicker");
		dp.setOnChange(event->{
			calendar.setLookup(dp.getValue());
		});
		System.out.println(side.getChilds().get(1));
		Tree t = (Tree) side.getChilds().get(1);
		t.setOnChange(event->{
			List<TreeItem> list = t.getValue();
			calendar.setDiaries(Listen.apply(list));
		});
	}
	public void setListener(Function<List<TreeItem>,List<List<TimeExamElement>>> event){
		this.Listen = event;
	}
}