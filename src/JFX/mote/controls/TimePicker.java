package JFX.mote.controls;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import JFX.mote.Component;
import JFX.mote.MathUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TimePicker extends Component {
	Canvas table;
	private GraphicsContext ctx;
	private double tan = 0;
	private LocalTime value = LocalTime.now();
	private boolean sHour = false;
	private List<String> Hours = Arrays.asList(
			"21","22","23","0","1","2","3","4","5","6","7","8","9","10",
			"11","12","13","14","15","16","17","18","19","20");
	private List<String> Minutes = Arrays.asList(
			"45","50","55","00","05","10","15","20","25","30","35","40");
	private boolean start = false;
	private int currentValue;
	private String name;
	private Label text;
	public TimePicker(String string) {
		super();
		name = string;
	}
	@Override
	public void init() {
		VBox pane = new VBox();
		text = new Label(name);
		text.setTextFill(textColor);
		table = new Canvas();
		width = height = (int) MathUtils.max(width, height, 128);
		table.setOnMouseMoved(event->{
			double x = event.getX()-width/2;
			double y = event.getY()-height/2;
			double H = Math.atan2(y,x);
			tan = H;
			if(start ) {
				update();				
			}
		});
		table.setOnMouseClicked(event->{
			if(!start) {
				sHour = true;
				start = true;
				update();
			}else{
				if(sHour) {
					value = LocalTime.of(currentValue, 00);
					sHour = false;
					update();
				}else {
					value = LocalTime.of(value.getHour(), currentValue);
					close();
				}
			}		
		});
		setPrefSize(width, height);
		setMinSize(width, height);
		pane.getChildren().addAll(text,table);
		add(pane);
		ctx = table.getGraphicsContext2D();
		table.setWidth(width);
		table.setHeight(height);
		close();
	}
	private void close() {
		tan = value.getHour()/12*Math.PI;
		ctx.clearRect(0, 0, width, height);
		ctx.setFill(Color.GHOSTWHITE);
		ctx.fillOval(0, 0, width, height);
		ctx.setFill(colored);
		int f = width/5;
		ctx.setFont(new Font(f));
		ctx.fillText(value.toString().substring(0, 5),(width/2)-f*1.5,(height/2)-f/2);
		start = false;
	}
	protected void update() {
		table.setWidth(width);
		table.setHeight(height);
		ctx.clearRect(0, 0, width, height);
		ctx.setFill(Color.GHOSTWHITE);
		ctx.fillOval(0, 0, width, height);
		double x;
		double y;
		int c = sHour?24:12;
		int round = (width+height)/(c);
		int l = (int) (-round*(c/(Math.PI*1.5)));
		for(int e = 0;e<c;e++) {
			int p = (int) Math.round((tan/(Math.PI)+1)*c/2);
			p = p>=c?0:p;
			x = l*Math.cos(((e)*Math.PI)/c*2);
			y = l*Math.sin(((e)*Math.PI)/c*2);
			x = width/2+x;
			y = height/2+y;
			String text = (sHour?Hours:Minutes).get(e);
			if(p==e) {
				ctx.setStroke(colored);
				ctx.beginPath();
				ctx.moveTo(width/2, height/2);
				ctx.lineTo(x, y);
				ctx.stroke();
				ctx.setFont(new Font(-l/3));
				ctx.setFill(colored);
				ctx.fillText(sHour?text+value.toString().substring(2, 5):value.toString().substring(0, 3)+text,(width/2)+l/2,(height/2)-l/5);
				ctx.setFont(new Font(round));
				currentValue = Integer.parseInt(text);
			}else {
				ctx.setFill(white);
			}
			ctx.fillOval(x-round/2,y-round/2, round, round);
			ctx.setFill(ctx.getFill()==white?colored:white);
			ctx.fillText(text,x-round/2,y+round/2);
		}
	}
	private static final Color colored = Color.rgb(0x22, 0xAA, 0xFF);
	private static final Color white = Color.rgb(0xee,0xee,0xee);
	/**
	 * @return the value
	 */
	public LocalTime getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(LocalTime value) {
		this.value = value;
	}
	@Override
	protected void updateStyle() {
		text.setTextFill(textColor);
	}

}
