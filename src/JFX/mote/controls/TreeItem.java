package JFX.mote.controls;

import java.util.ArrayList;
import java.util.stream.Stream;

import javafx.scene.input.MouseEvent;

public class TreeItem {
	/**
	 * 
	 */
	private ArrayList<TreeItem> content = new ArrayList<TreeItem>();
	private String text;
	private boolean state;
	private int[] bound = new int[5];
	private TreeItem c;
	private String Parent;
	public TreeItem(String string,String parent ) {
		text = string;
		Parent = parent;
	}
	public String getText() {
		return text;
	}
	public boolean isopen() {
		return state;
	}
	public void open() {
		state = true;
		//bound[4] = content.size()*bound[3];
	}
	public void close() {
		state = false;
		//bound[4] = 0;
	}
	public boolean add(String e) {
		return content.add(new TreeItem(e,this.text));
	}
	public void setBound(int x, int y, int w, int h) {
		bound[0] = x;
		bound[1] = y;
		bound[2] = w+x;
		bound[3] = h;
		bound[4] = 0;
	}
	public boolean isClick(MouseEvent e) {
		boolean clickthis = (e.getX()>bound[0] && e.getX()<(bound[2])) && //test sur X
				(e.getY()>bound[1] && e.getY()<(bound[3]+bound[4]+bound[1])) 	//test sur Y
				;
		c = null;
		if(size()>0) {
			 c = content.stream()
					.reduce(null,(p,o)->{
						if(o.size()>0) {
							if(o.isClick(e)) {
								return o.getClickChild(e);
							}
						}else {
							if(o.isClick(e)) {
								return o;
							}				
						}
						return p;
					});
		}
		return clickthis||c!=null;
				
	}
	@Override
	public String toString() {
		return getText()+(size()>0?super.toString():"");
	}
	public int size() {
		return content.size();
	}
	public Stream<TreeItem> stream() {
		return content.stream();
	}
	public ArrayList<TreeItem> getContent() {
		return content;
	}
	public TreeItem getClickChild(MouseEvent x) {
		return c==null?this:c;
	}
	public int lenght() {
		int a = 1;
		for(TreeItem i : content) {
			a+=i.lenght();
		}
		return a;
	}
	/**
	 * @return the parent
	 */
	public String getParent() {
		return Parent;
	}
}
