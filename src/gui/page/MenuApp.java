package gui.page;

import java.util.List;
import java.util.stream.Collectors;

import JFX.mote.App;
import JFX.mote.Element;
import JFX.mote.Position;
import JFX.mote.components.Text;
import JFX.mote.controls.Button;
import JFX.mote.layout.Flex;
import JFX.mote.layout.Panel;
import javafx.scene.layout.BorderPane;

public class MenuApp extends Panel<BorderPane> {


	private Flex list;

	public MenuApp(String name, String username) {
		super(new BorderPane(),	name);
		Text title = new Text("Bienvenu "+username+" ,");
		title.position = Position.Top;
		add(title);
		list = new Flex();
		list.position = Position.Center;
		add(list);
		App.setReturnVisible(true);
	}
	public void setApp(List<String> ls){
		List<Element> lb = ls.stream().map(x->{
			return new Button(x, event->{openLayout(x);});
		}).collect(Collectors.toList());
		list.setContent(lb);
	}
}
