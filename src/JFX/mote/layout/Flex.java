package JFX.mote.layout;

import java.util.ArrayList;
import java.util.List;

import JFX.mote.App;
import JFX.mote.Component;
import JFX.mote.Element;
import JFX.mote.controls.TreeItem;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Flex extends Component {
	private ScrollPane scroll;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		if(table!=null) {
			table.getChildren().clear();
		}
		this.type = type;
		if(type == Flex.Row) {
			table = new HBox();
		}else {
			table = new VBox();		
		}
		update();
	}
	Pane table;
	protected List<Element> content = new ArrayList<Element>();
	public List<Element> getContent() {
		return content;
	}
	public void setContent(List<Element> content) {
		this.content = content;
		update();
	}
	public static final int Row = 1;
	public static final int Column = 0;
	protected int type;
	private boolean follow = false;
	private int minfontsize = 0;
	public boolean isFollow() {
		return follow;
	}
	public void setFollow(boolean follow) {
		this.follow = follow;
	}
	public Flex(int type) {
		this.setType(type);
	}
	public Flex() {
		this(0);
	}
	public void setMinFontSize(int fontSize) {
		minfontsize = fontSize;
	}
	protected void update() {
		if(loaded) {
			if(type == Flex.Row) {
				fontSize = width==0?App.width:width;
				int nrow = content.size();
				fontSize/=nrow+3;				
			}else {
				fontSize = height==0?App.height:height;
				int nrow = content.size()+1;
				fontSize/=nrow*2;	
			}
			fontSize = fontSize<minfontsize?minfontsize:fontSize;
			table.getChildren().clear();
			BackgroundFill bg = new BackgroundFill(backgroundColor, null, null);
			table.setBackground(new Background(bg));
			content.forEach(line->{
				((Component) line).heredite(this);
				((Component) line).setAlignment(Pos.CENTER);
				table.getChildren().add(((Component) line).toNode());
			});
		//	autosize();
			//table.autosize();
		}
	}
	public void add(String string) {
		content.add(new ListLine(string));
		update();
	}
	public void add(Component componnent) {
		content.add(componnent);
		update();
	}
	public void add(String string,EventHandler<? super MouseEvent> onclick) {
		content.add(new ListLine(string,onclick));
		update();
	}
	@Override
	public void init() {
		scroll = new ScrollPane();
		BackgroundFill bg = new BackgroundFill(Color.TRANSPARENT, null, null);
		scroll.setBackground(new Background(bg));
		getChildren().add(scroll);
		scroll.setContent(table);
		loaded = true;
		update();
	}
	@Override
	protected void updateStyle() {
		super.updateStyle();
	}
	@Override
	public void setSize(double width, double height) {
		super.setSize(width, height);
		setPrefSize(width, height);
		scroll.setPrefSize(width, height);
		if(follow) {
			scroll.setFitToWidth(true);
			scroll.setFitToHeight(true);

		}
	}
	@Override
	public void heredite(Component component) {
		super.heredite(component);
	}
	public void clear() {
		content.clear();
		table.getChildren().clear();
	}
}
