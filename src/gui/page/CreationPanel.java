package gui.page;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import JFX.mote.controls.Select;
import JFX.mote.controls.TimePicker;
import JFX.mote.controls.Tree;
import JFX.mote.controls.TreeItem;
import JFX.mote.layout.Popup;

public class CreationPanel extends Popup{
	private Select topic;
	private Tree eleveTree;
	private TimePicker time;
	public CreationPanel() {
		super("Ajouter un Examen");
		topic = new Select();
		time = new TimePicker();
		eleveTree = new Tree();
		add(topic);
		add(time);
		add(eleveTree);
		Tree a = new Tree();
		TreeItem b = a.createSection("L2 Math");
		b.add("Jean");
		b.add("Batiste");
		b.add("Cory");
		TreeItem c = a.createSection("L2 Info");
		c.add("Avrelle");
		c.add("Dorien");
		c.add("Elza");
		add(a);

		open();
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
