package gui.page;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import JFX.mote.controls.Select;
import JFX.mote.controls.TimePicker;
import JFX.mote.controls.Tree;
import JFX.mote.controls.TreeItem;
import JFX.mote.layout.Form;
import JFX.mote.layout.Popup;

public class CreationPanel extends Form{
	private Select topic;
	private Tree eleveTree;
	private TimePicker time;
	public CreationPanel() {
		super("Ajouter un examen",null);
		topic = new Select();
		time = new TimePicker();
		eleveTree = new Tree();
		add(topic);
		add(time);
		add(eleveTree);
	}
	public void setPromo(HashMap<String,List<Object>> promos){
		eleveTree.clear();
		promos.forEach((key,value)->{
			TreeItem b = eleveTree.createSection(key);
			value.forEach(index->{
				b.add(index.toString());
			});
		});
	}
	public void setTopics(List<String> topics) {
		topic.setList(topics);
	}
	public void setTime(LocalTime time) {
		this.time.setValue(time);
	}
}
