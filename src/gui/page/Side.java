package gui.page;


import JFX.mote.App;
import JFX.mote.Position;
import JFX.mote.components.Text;
import JFX.mote.components.Title;
import JFX.mote.controls.DatePicker;
import JFX.mote.controls.TextField;
import JFX.mote.controls.Tree;
import JFX.mote.controls.TreeItem;
import JFX.mote.layout.Flex;
import JFX.mote.layout.Panel;
import JFX.mote.layout.Popup;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import liaisonappliBDopta.SQLBase;

public class Side extends Panel<BorderPane>{
	public Side() {
		super(new BorderPane());
	}
	public Side(String name) {
		super(new BorderPane(),name);
	}
	public void init() {
		layout.setMinSize(256, App.height-32);
		setMaxSize(256, App.height-48);
		setMinSize(256,App.height-48);
		setBackground(Color.grayRgb(0x22));
		setColor(Color.grayRgb(0xee));
		setPadding(new Insets(16));
		Title t = new Title("Filtre");
		t.position = Position.Top;
		add(t);	
		Tree ttl = new Tree();
		TreeItem salle = ttl.createSection("Salle");
		SQLBase.getSalle().forEach(x->{
			salle.add(x);
		});
		TreeItem promo = ttl.createSection("promo");
		SQLBase.getPromo().forEach(x->{
			promo.add(x);
		});
		ttl.position = Position.Center;
		ttl.setFontSize(16);
		ttl.setSize(256,256);
		add(ttl);
		ttl.position = Position.Center;
		ttl.setFontSize(16);
		ttl.setSize(256,256);
		add(ttl);
		DatePicker calendar = new DatePicker("datepicker");
		calendar.position = Position.Bottom;
		add(calendar);
		
	}
}
