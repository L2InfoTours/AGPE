package JFX.mote.controls;

import JFX.mote.App;
import JFX.mote.Component;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TextField extends Component{
	private Label text;
	private javafx.scene.control.TextField input;
	private EventHandler<? super KeyEvent> onchange = event->{};
	private String value;
	private String type;
	protected VBox pane;
	public TextField(String value,EventHandler<? super KeyEvent> onchange) {
		this(value);
		this.setOnchange(onchange);
	}
	public TextField(String value) {
		super();
		this.value = value;
	}
	@Override
	public void init() {
		pane = new VBox();
		text = new Label(value);
		text.setPadding(new Insets(32));
		text.setAlignment(Pos.CENTER_LEFT);
		text.setTextFill(textColor);
		
		StackPane textfield = new StackPane();
		
		input = new javafx.scene.control.TextField();
		input.setAlignment(Pos.CENTER_LEFT);
		input.setMinSize(624, 32);
		input.setMaxSize(624, 32);
		input.setStyle("-fx-background-radius:20 20 20 20;-fx-background-color:#fff;");
		input.setOnKeyPressed(this.onchange);
		
		textfield.setAlignment(Pos.CENTER);
		textfield.setStyle("-fx-background-radius:20 20 20 20;-fx-background-color:#fff;"
				+"-fx-border-color:#222;-fx-border-width:1;-fx-border-radius:20;");
		textfield.getChildren().add(input);
		textfield.setPadding(new Insets(2));
		
		pane.setMinSize(624, 32);
		pane.setMaxSize(624, 160);
		pane.getChildren().addAll(text,textfield);

		setAlignment(Pos.CENTER);
		setPadding(new Insets(0,0,32,0));
		setMinSize(624, 32);
		setMaxSize(App.width, 160);
		
		add(pane);
	}
	public EventHandler<? super KeyEvent> getOnchange() {
		return onchange;
	}
	public void setOnchange(EventHandler<? super KeyEvent> onchange) {
		this.onchange = onchange;
		if(loaded) {
			input.setOnKeyPressed(this.onchange);
		}
	}
	public String getText() {
		return input.getText();
	}
	public final static String Email = "email"; 
	public void setType(String string) {
		this.type = string;
	}
}
