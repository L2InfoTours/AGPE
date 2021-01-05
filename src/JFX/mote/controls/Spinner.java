package JFX.mote.controls;

import JFX.mote.Component;
import JFX.mote.Element;
import JFX.mote.layout.Flex;
import JFX.mote.layout.ListLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import JFX.mote.App;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class Spinner extends Component{
	private Label text;
	private javafx.scene.control.TextField input;
	private EventHandler<? super KeyEvent> onchange = event->{};
	private int value;
	private String name;
	public Spinner(String name,int value,EventHandler<? super KeyEvent> onchange) {
		this(name,value);
		this.setOnchange(onchange);
	}
	public Spinner(String name,int value) {
		super();
		this.name = name;
		this.value = value;
	}
	public Spinner(int value) {
		this("",value);
	}
	public Spinner() {
		this("",0);
	}
	@Override
	public void init() {
		VBox pane = new VBox();
		text = new Label(name);
		text.setPadding(new Insets(16));
		text.setAlignment(Pos.CENTER_LEFT);
			
		HBox field = new HBox();
		
		VBox textfield = new VBox();
		
		input = new javafx.scene.control.TextField();
		input.setAlignment(Pos.CENTER_LEFT);
		input.setMinSize(592, 32);
		input.setMaxSize(592, 32);
		input.setStyle("-fx-background-radius:20 0 0 20;-fx-background-color:#fff;");
		
		String pathT = "M 25,5 L40,30 L 10,30";
		SVGPath imgT = new SVGPath();
		imgT.setContent(pathT);
		
		imgT.setScaleX(.25);
		imgT.setScaleY(.25);
		imgT.setFill(Color.grayRgb(0x22));
		 
		StackPane btnT = new StackPane();
		btnT.getChildren().add(imgT);
		btnT.setOnMouseClicked(event->{
			setValue(value+1);
		});
		
		String pathB = "M 25,35 L40,5 L 10,5";
		SVGPath imgB = new SVGPath();
		imgB.setContent(pathB);
		
		imgB.setScaleX(.25);
		imgB.setScaleY(.25);
		imgB.setFill(Color.grayRgb(0x22));
		 
		StackPane btnB = new StackPane();
		btnB.getChildren().add(imgB);
		btnB.setOnMouseClicked(event->{
			setValue(value-1);
		});
		textfield.setPadding(new Insets(2));
		input.setOnAction(event->{
	        try {
	            String str = input.getText();
	            if (str == null || str.length() == 0) {
	                return;
	            }
	            int parsedNumber = Integer.parseInt(str);
	            setValue(parsedNumber);
	        } catch (Exception ex) {
	        	input.setText(value+"");
	        }
		});
		
		
		field.setAlignment(Pos.CENTER);
		field.setStyle("-fx-background-radius:20 20 20 20;-fx-background-color:#fff;"
				+"-fx-border-color:#222;-fx-border-width:1;-fx-border-radius:20;");
		VBox btn = new VBox();
		btn.getChildren().addAll(btnT,btnB);
		btn.setPadding(new Insets(2,8,2,2));
		field.getChildren().addAll(input,btn);
		textfield.getChildren().addAll(field);
		
		pane.setMinSize(624, 32);
		pane.setMaxSize(624, 160);
		pane.getChildren().addAll(text,textfield);
		
		setAlignment(Pos.CENTER);
		setPadding(new Insets(0,0,32,0));
		setMinSize(624, 64);
		setMaxSize(App.width, 160);
		add(pane);
	}
	public void setValue(int value) {
		this.value = value;
		input.setText(value+"");
	}
	public EventHandler<? super KeyEvent> getOnchange() {
		return onchange;
	}
	public void setOnchange(EventHandler<? super KeyEvent> onchange) {
		this.onchange = onchange;
	}
	public int getValue() {
		return value;
	}
}
