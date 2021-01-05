package gui.page;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import JFX.mote.components.Text;
import JFX.mote.controls.Select;
import JFX.mote.controls.Spinner;
import JFX.mote.controls.TextField;
import JFX.mote.controls.TimePicker;
import JFX.mote.controls.Tree;
import JFX.mote.controls.TreeItem;
import JFX.mote.layout.Form;
import JFX.mote.layout.Popup;
import javafx.scene.paint.Color;
import liaisonappliBDopta.SQLBase;

public class CreationPanel extends Form{
	private Select materiel;
	private Select topic;
	private Spinner duree ;
	private Tree eleveTree;
	private TimePicker time;
	private TextField nom;
	private Text errormsg;
	public CreationPanel() {
		super("Ajouter un examen",null);
		nom = new TextField("Identifiant");
		topic = new Select();
		duree = new Spinner();
		time = new TimePicker();
		eleveTree = new Tree();
		materiel = new Select();
		add(nom);
		topic.setList(SQLBase.getTopics());
		add(topic);
		materiel.setList(SQLBase.getMateriel());
		add(materiel);
		add(time);
		add(duree);
		SQLBase.getPromo().forEach(x->{
			TreeItem sec = eleveTree.createSection(x);
			SQLBase.getElevesIn(x).forEach(y->{
				sec.add(y);
			});
		});
		add(eleveTree);
		setSubmitAction(event->{
			
		});
	}
	public String getMateriel() {
		return materiel.getText();
	}
	public String getTopic() {
		return topic.getText();
	}
	public Integer getDuree() {
		return duree.getValue();
	}
	public List<TreeItem> getEleveTree() {
		return eleveTree.getValue();
	}
	public LocalTime getTime() {
		return time.getValue();
	}
	public Text getErrormsg() {
		return errormsg;
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
	public void setErrorMessage(String value) {
		if(errormsg==null) {
			errormsg = new Text(value);
			errormsg.setColor(Color.RED);
			add(errormsg);
		}else {
			errormsg.setText(value);
		}
	}
}
