package JFX.mote.controls;

import JFX.mote.Component;
import JFX.mote.Element;
import JFX.mote.layout.Flex;
import JFX.mote.layout.ListLine;

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

public class Select extends Component{
	private Label text;
	private javafx.scene.control.TextField input;
	private EventHandler<? super KeyEvent> onchange = event->{};
	private String value;
	private List<String> list;
	private boolean visible;
	private Flex flex;
	public Select(String value,List<String> ls,EventHandler<? super KeyEvent> onchange) {
		this(value,ls);
		this.setOnchange(onchange);
	}
	public Select(String value,List<String> ls) {
		super();
		this.value = value;
		list = ls;
		flex = new Flex();
	}
	public Select(List<String> ls) {
		this("",ls);
	}
	@Override
	public void init() {
		VBox pane = new VBox();
		text = new Label(value);
		text.setPadding(new Insets(16));
		text.setAlignment(Pos.CENTER_LEFT);
			
		HBox field = new HBox();
		
		VBox textfield = new VBox();
		
		input = new javafx.scene.control.TextField();
		input.setAlignment(Pos.CENTER_LEFT);
		input.setMinSize(592, 32);
		input.setMaxSize(592, 32);
		input.setStyle("-fx-background-radius:20 0 0 20;-fx-background-color:#fff;");
		
		String path = "M 25,5 L40,30 L 10,30";
		SVGPath img = new SVGPath();
		img.setContent(path);
		
		img.setScaleX(.5);
		img.setScaleY(.5);
		img.setFill(Color.grayRgb(0x22));
		 
		StackPane btn = new StackPane();
		btn.setPadding(new Insets(2,8,2,2));
		btn.getChildren().add(img);
		btn.setOnMouseClicked(event->{
			visible = !visible;
			updateSelect();
		});
		flex.init();
		flex.setFollow(true);
		flex.setSize(width, height);
		flex.setMinFontSize(24);
		textfield.setPadding(new Insets(2));
		input.setOnKeyPressed(event->{
			value = input.getText()+event.getText();
			System.out.println(value);
			updateSelect();
		});
		
		
		field.setAlignment(Pos.CENTER);
		field.setStyle("-fx-background-radius:20 20 20 20;-fx-background-color:#fff;"
				+"-fx-border-color:#222;-fx-border-width:1;-fx-border-radius:20;");
		field.getChildren().addAll(input,btn);
		textfield.getChildren().addAll(field,flex);
		
		pane.setMinSize(624, 32);
		pane.setMaxSize(624, 160);
		pane.getChildren().addAll(text,textfield);
		
		setAlignment(Pos.CENTER);
		setPadding(new Insets(0,0,32,0));
		setMinSize(624, 64);
		setMaxSize(App.width, 160);
		add(pane);
		updateSelect();
	}
	private void updateSelect() {
		flex.setVisible(visible);
		//flex.clear();
		if(visible) {
			List<Element> a = list.stream()
					.filter(x->{
						return x.toUpperCase().contains(value.toUpperCase());
					})
					.map(x->{
						return new ListLine(x,e->{
							System.out.println(e);
							Object o = e.getSource();
							if(o instanceof ListLine) {
								ListLine ll = (ListLine) o;
								this.setValue(ll.getText());
							}
						});
					})
					.collect(Collectors.toList());
			flex.setContent(a);
			flex.setSize(width,24*a.size());
			flex.setMinHeight(24*(a.size()+1));
		}else {
			flex.setSize(width,24);
		}
	}
	private void setValue(String value) {
		this.value = value;
		input.setText(value);
		updateSelect();
	}
	public EventHandler<? super KeyEvent> getOnchange() {
		return onchange;
	}
	public void setOnchange(EventHandler<? super KeyEvent> onchange) {
		this.onchange = onchange;
	}
	public String getText() {
		return input.getText();
	}
}
