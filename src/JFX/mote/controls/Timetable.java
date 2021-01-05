package JFX.mote.controls;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import JFX.mote.App;
import JFX.mote.Component;
import JFX.mote.Element;
import JFX.mote.layout.Flex;
import gui.page.CreationPanel;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * @author Mote
 *
 * @param <T extends TimetableElement>
 */
public class Timetable<T extends TimetableElement> extends Flex implements Runnable {
	private TemporalField WEEK_OF_YEAR = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
	List<T> TimeElements;
	public void setType(int type) {
		this.type = type;
		update();
	}
	LocalDate lookup;
	private int lookupDays = 7;
	private int lookupHours = 12;
	private int offestHours = 8;
	private int offestDays = 1;
	public LocalDate getLookup() {
		return lookup;
	}
	public void setLookup(LocalDate lookup) {
		this.lookup = lookup;
		update();
	}
	public int getLookupDays() {
		return lookupDays;
	}
	public void setLookupDays(int lookupDays) {
		this.lookupDays = lookupDays;
	}
	public int getLookupHours() {
		return lookupHours;
	}
	public void setLookupHours(int lookupHours) {
		this.lookupHours = lookupHours;
	}
	public int getOffestHours() {
		return offestHours;
	}
	public void setOffestHours(int offestHours) {
		this.offestHours = offestHours;
	}
	List<List<Element>> content = new ArrayList<List<Element>>();
	Canvas table;
	private int divWidth;
	private int divHeight;
	private int mode;//actual only one mode exist but other mode are comming soon
	private GraphicsContext ctx;
	private Thread threader;
	/**
	 * @param List<T extends TimetableElement>
	 * <br>
	 * @see List
	 * <br>
	 * @see TimetableElement
	 */
	public Timetable(List<T> diary) {
		super();
		TimeElements = diary;
		lookup = LocalDate.now();
		table = new Canvas();
		ctx = table.getGraphicsContext2D();
		table.setOnMouseClicked(x->{
			forEachClickEvent(x);
		});
		height = width = 512;
		width += 128;
		bestFit();
		table.setWidth(width);
		table.setHeight(height);
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(table);
		autosize();
		add(scroll);
		
	}
	@Override
	public void setSize(double width, double height) {
		this.height = (int) height;
		this.width = (int) width;
		bestFit();
		super.setSize(this.width, height);
		table.setWidth(this.width);
		table.setHeight(height);
		update();
	}

	/**
	 * upadte the timetable canvas
	 */
	protected void update() {
		if(loaded) {
			if(type == Flex.Row) {
				updateRow();
			}else {
				updateColumn();
			}
		}
	}

	/**
	 * upadte the timetable canvas in Row mode
	 */
	private void updateRow() {
		if(ctx != null) {
			table.setWidth(height);
			table.setHeight(width);
			ctx.clearRect(0, 0, height, width);
			ctx.setFill(Color.WHITESMOKE);
			ctx.fillRect(0, 0, height, width);
			divWidth = (int) (width/(lookupDays+1.1));
			divHeight = height/(lookupHours*2+1);
			int d = 16;
			for(DayOfWeek x : DayOfWeek.values()){
				if(x.getValue()>offestDays-1 && x.getValue()<lookupDays+offestDays) {		
					String day = x.getDisplayName(TextStyle.FULL, Locale.getDefault());
					day = day.substring(0, 1).toUpperCase()+day.substring(1) ;
					ctx.setFill(Color.grayRgb(96));
					d+=divWidth;
					ctx.fillText(day, 16,d);
					ctx.setFill(Color.grayRgb(178));
					ctx.fillRect(0,x.getValue()*divWidth, height,2);
				}
			}
			ctx.fillRect(0, divHeight, width, 2);
			ctx.setFill(Color.grayRgb(119));
			for(int h = 0;h<lookupHours*2;h++) {
				String hr = h%2==0?(h/2+offestHours)+":00":((h-1)/2+offestHours)+":30";
				if(h%2==0)
					ctx.fillRect((h+1)*5*16,16, 1, height+divHeight);
				ctx.fillText(hr, (h+1)*5*16,16);
			}
		}
	}
	/**
	 * upadte the timetable canvas in  Column Mode
	 */
	private void updateColumn() {
		if(ctx != null) {
			table.setWidth(width);
			table.setHeight(height);
			ctx.clearRect(0, 0, width, height);
			ctx.setFill(Color.WHITESMOKE);
			ctx.fillRect(0, 0, width, height);
			divWidth = (int) (width/(lookupDays+1.1));
			divHeight = height/(lookupHours*2+1);
			int d = 16;
			for(DayOfWeek x : DayOfWeek.values()){
				if(x.getValue()>offestDays-1 && x.getValue()<lookupDays+offestDays) {		
					String day = x.getDisplayName(TextStyle.FULL, Locale.getDefault());
					day = day.substring(0, 1).toUpperCase()+day.substring(1) ;
					ctx.setFill(Color.grayRgb(96));
					d+=divWidth;
					ctx.fillText(day, d,16);
					ctx.setFill(Color.grayRgb(178));
					ctx.fillRect(x.getValue()*divWidth, 0, 2, height);
				}
			}
			ctx.fillRect(0, divHeight, width, 2);
			ctx.setFill(Color.grayRgb(119));
			for(int h = 0;h<lookupHours*2;h++) {
				String hr = h%2==0?(h/2+offestHours)+":00":((h-1)/2+offestHours)+":30";
				if(h%2==0)
					ctx.fillRect(divWidth, (h+2)*divHeight, width-divWidth, 1);
				ctx.fillText(hr, 8,(h+2.5)*divHeight);
			}
			int week = lookup.get(WEEK_OF_YEAR);
			//System.out.println("week"+week);
			TimeElements.forEach(te ->{
				if(
						te.isWeek(week) 
						) {
					drawCase(te);
				}
			});
			ctx.setFill(Color.RED);
			LocalDate date = LocalDate.now();
			LocalDateTime time = LocalDateTime.now();
			double y = ((time.getHour()+1)*2)+((time.getMinute()+0.0)/30);
			y = (y-(offestHours*2))*divHeight;
			ctx.fillRect(date.getDayOfWeek().getValue()*divWidth, y-2, divWidth, 2);
		}
	}
	private void drawCase(TimetableElement el) {
		if(
				el.getDay()>offestDays-1 && el.getDay()<lookupDays+offestDays
				&&
				el.getHour()<lookupHours+offestHours && 
					((el.getHour()+el.getDur().getHour())*60)
					+
					el.getMinute()+el.getDur().getMinute() 
					>lookupHours
				) {	
			int Wc = divWidth;
			int Hd = divHeight;
			int x = (el.getDay()-offestDays+1)*Wc;
			int y = (int) (((((el.getHour()+1)*2)+((el.getMinute()+0.0)/30))-(offestHours*2))*Hd);
			x += 2;
			Wc -= 2;
			LocalTime time = el.getDur();
			Hd *=  ((time.getHour())*2)+((time.getMinute()+0.0)/30);
			int padding = 5; 
			int font = (int) ctx.getFont().getSize();
			ctx.setFill(Color.WHITE);
			ctx.fillRect(x, y, Wc, Hd);
			el.setBound(x, y, Wc, Hd);
			ctx.setFill(Color.rgb(34,176, 255));
			ctx.fillRect(x, y, 2, Hd);
			String text = el.getName();
			int lL = (int) (Wc/ctx.getFont().getSize());
			text = Stream.of(text.split("\n"))
					.map(t->t.length()>lL?t.substring(0,lL)+"\n...":t)
					.reduce("", (p,o)->{
						return p.length()>0?p+"\n"+o:o;
					})
					;
			
			ctx.fillText(text, x+padding, y+padding+font);
		}
	}
	private void forEachClickEvent(MouseEvent x) {
		TimetableElement c = TimeElements.stream().reduce(null,(p,o)->{
			if(o.isClick(x)) {
				return o;
			}
			return p;
		});
		if(c!=null) {
			c.open();
		}else {
			try {
				TimeElements.get(0).createPanel();				
			}catch(IndexOutOfBoundsException e){
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	};
	@Override
	protected void updateStyle() {
		super.updateStyle();
		//table.autosize();
		autosize();
	}
	@Override
	public void init() {
		loaded = true;

		update();
		if(looping) {
			threader = App.threadManger.add(this);
			threader.start();
		}
	}
	private boolean looping = false;
	/**
	 * start the updating when the Component has added on a Panel.
	*/
	public void start(){
		looping = true;
	}
	/**
	 * stop the updating when the Component has added on a Panel.
	*/
	public void stop(){
		looping = false;
	}
	@Override
	public void run() {
		try {
			while(looping) {
				this.update();
				Thread.sleep(1000);
			}
		}catch(Exception e) {
			e.printStackTrace();
			threader.interrupt();
			looping=false;
		}
	}
	public void setLookup(int startHour, int nHours, int startDay, int nDays) {
		setOffestHours(startHour);
		setLookupHours(nHours);
		setOffestDays(startDay);
		setLookupDays(nDays);
		bestFit();
		update();
	}
	private void bestFit() {
		//w = 612 h = 512 
		// w/7 h/1
		// 86<w<204 
		width = 68*(lookupDays+1)+136;
		width = width<86?86:width>640?640:width;
		setWidth(width);
		table.setWidth(width);
		table.prefWidth(width);
		ctx = table.getGraphicsContext2D();
		System.out.println(width);
	}
	/**
	 * @return the offestDays
	 */
	public int getOffestDays() {
		return offestDays;
	}
	/**
	 * @param offestDays the offestDays to set
	 */
	public void setOffestDays(int offestDays) {
		this.offestDays = offestDays;
	}
}
