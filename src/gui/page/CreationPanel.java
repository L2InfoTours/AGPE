package gui.page;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.naming.NamingException;

import JFX.mote.components.Text;
import JFX.mote.controls.Select;
import JFX.mote.controls.Spinner;
import JFX.mote.controls.TextField;
import JFX.mote.controls.TimePicker;
import JFX.mote.controls.Tree;
import JFX.mote.controls.TreeItem;
import JFX.mote.layout.Form;
import JFX.mote.layout.Popup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;
import liaisonappliBDopta.Etudiant;
import liaisonappliBDopta.Examen;
import liaisonappliBDopta.SQLBase;

public class CreationPanel extends Form{
	final static String Oral = "oral";
	final static String Ecrit = "écrit";
	private Select materiel;
	private Select topic;
	private Spinner duree ;
	private Tree eleveTree;
	private TimePicker time;
	private TextField nom;
	private Text errormsg;
	private Select type;
	private EventHandler<ActionEvent> onclick = x->{};
	private DatePicker date;
	public CreationPanel() {
		super("Ajouter un examen",null);
		nom = new TextField("nom");
		nom.setColor(Color.WHITESMOKE);
		topic = new Select("Matiere");
		topic.setColor(Color.WHITESMOKE);
		date = new DatePicker();
		duree = new Spinner("Durée");
		duree.setColor(Color.WHITESMOKE);
		time = new TimePicker("Heure");
		time.setColor(Color.WHITESMOKE);
		eleveTree = new Tree("Eleves");
		eleveTree.setColor(Color.WHITESMOKE);
		type = new Select("Type");
		type.setColor(Color.WHITESMOKE);
		materiel = new Select("Materiel");
		materiel.setColor(Color.WHITESMOKE);
		add(nom);
		topic.setList(SQLBase.getTopics());
		add(topic);
		materiel.setList(SQLBase.getMateriel());
		add(materiel);
		//add(date);
		//TomTadd(time);
		add(duree);
		type.setList(Arrays.asList(Oral,Ecrit));
		add(type);
		SQLBase.getPromo().forEach(x->{
			TreeItem sec = eleveTree.createSection(x);
			SQLBase.getElevesIn(x).forEach(y->{
				sec.add(y[0],y[1].toString());
			});
		});
		add(eleveTree);
		super.setSubmitAction(event->{
			System.out.println("ZERTYUI");
			/*
			LocalDate dateday = date.getValue();
			LocalTime timeday = time.getValue();
			LocalDateTime datetime =LocalDateTime.of(
					dateday.getYear(),
					dateday.getMonth(),
					dateday.getDayOfMonth(),
					timeday.getHour(),
					timeday.getMinute());*/
			try {
				Examen ex = new Examen(nom.getText(),
						topic.getValue(),
						duree.getValue(),
						type.getValue()==Oral?1001:0110,
						materiel.getValue()
						);
				
				List<Etudiant> ls = eleveTree.getValue().stream().map(x->{
					return (Etudiant) x.getObj();
				}).collect(Collectors.toList());
				ex.addListEtudiant(ls);
				System.out.println(ex);
				ex.addBD();
			} catch (Exception e) {
				e.printStackTrace();
			}
			onclick.handle(event);
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
	public void  setSubmitAction(EventHandler<ActionEvent> onclick) {
		this.onclick = onclick;
	}
}
