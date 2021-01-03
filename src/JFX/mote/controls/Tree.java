package JFX.mote.controls;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import JFX.mote.App;
import JFX.mote.Component;
import JFX.mote.MathUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Tree extends Component{
	
	private Canvas canvas;
	private GraphicsContext ctx;
	private List<TreeItem> list;
	private int ryl;
	private List<TreeItem> value = new ArrayList<TreeItem>();
	public Tree() {
		canvas = new Canvas();
		ctx = canvas.getGraphicsContext2D();
		width = (int) (width==0?App.width*.5:width);
		height = (int) (height==0?App.height*.5:height);
		list = new ArrayList<TreeItem>();
		setPrefSize(width,height);
		setMinSize(256,256);
		canvas.setWidth(width);
		canvas.setHeight(height);
		add(canvas);
		canvas.setOnMouseClicked(x->{
			forEachClickEvent(x);
		});
	}
	@Override
	public void init() {
		loaded = true;
		update();
	}
	private void update() {
		if(loaded) {
			
			canvas.setWidth(width);
			canvas.setHeight(height);
			//ctx.clearRect(0, 0, width, height);
			ctx.setFill(Color.grayRgb(0xee));
			ctx.fillRect(0, 0, width, height);
			fontSize = height==0?App.height:height;
			int nrow = list.size()+1;
			fontSize/=nrow*2;
			int y = 0;
			int x = (int) fontSize;
			ctx.setFont(new Font(fontSize));
			ctx.setFill(Color.grayRgb(0x22));
			for(TreeItem ti : list) {
				y = y+1;
				drawLine(ti, x, y);
				y += ryl;
				ryl = 0;
			}
		}
	}
	private void drawLine(String i,int x,int y,boolean select) {
		int w = (int) (width<fontSize?i.length()*fontSize:width);
		if(select) {
			ctx.setFill(Color.rgb(0x22,0xAA,0xFF));
			ctx.fillRect(x+fontSize, fontSize*(y-1),w, (int) fontSize);			
		}
		ctx.setFill(Color.grayRgb(0x22));
		ctx.fillText(i, x+fontSize, fontSize*y);
	}
	private void drawLine(TreeItem ti,int x,int y) {
		x = (int) (ti.size()==0?x:x+fontSize);
		int w = (int) (width<fontSize?ti.getText().length()*fontSize:width);
		System.out.println(value);
		drawLine(ti.getText(), x, y,containsValue(ti));
		ctx.beginPath();
		if(ti.size()!=0) {
			if(ti.isopen()) {
				ryl = 0;
				for(TreeItem i : ti.getContent()) {
					ryl = ryl+1;
					drawLine(i, x, y+ryl);
				}
				ctx.moveTo(x+(fontSize*.65), (y-1)*fontSize+(fontSize*.7));
				ctx.lineTo(x+(fontSize*.4), (y-1)*fontSize+(fontSize*.2));
				ctx.lineTo(x+(fontSize*.9), (y-1)*fontSize+(fontSize*.2));
			}else {
				ctx.moveTo(x+(fontSize*.2), (y-1)*fontSize+(fontSize*.65));
				ctx.lineTo(x+(fontSize*.7), (y-1)*fontSize+(fontSize*.4));
				ctx.lineTo(x+(fontSize*.7), (y-1)*fontSize+(fontSize*.9));
			}
		}
		ctx.fill();
		ti.setBound(x, (int) ((y-1)*fontSize), w, (int) fontSize);
	}
	private void forEachClickEvent(MouseEvent x) {
		TreeItem c = list.stream()
		.reduce(null,(p,o)->{
			if(o.size()>0) {
				if(o.isClick(x)) {
					return o.getClickChild(x);
				}
			}else {
				if(o.isClick(x)) {
					return o;
				}				
			}
			return p;
		});
		System.out.println(c);
		if(c!=null) {
			if(c.size()!=0) {
				if(c.isopen()) {
					c.close();
				}else {
					c.open();
				}
			}else {
				switchValue(c);
			}
		}
		update();
	}
	private void addValue(TreeItem c) {
		value.add(c);
	}
	private void removeValue(TreeItem c) {
		value.remove(c);
	}
	public boolean containsValue(TreeItem ti) {
		return value.stream().filter(v->v==ti).count()>0;
	}
	private void switchValue(TreeItem c) {
		if(containsValue(c)) {
			removeValue(c);
		}else {
			addValue(c);
		}
	}
	public List<TreeItem> getValue() {
		return value;
	}
	public void add(String string) {
		TreeItem a = new TreeItem(string);
		add(a);
	}
	public void add(TreeItem TI) {
		list.add(TI);
		update();
	}
	public TreeItem createSection(String string) {
		TreeItem a = new TreeItem(string);
		add(a);
		return a;
	}
	
}
