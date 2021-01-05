package gui.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;

import JFX.mote.Frame;
import JFX.mote.controls.Email;
import JFX.mote.controls.Tree;
import JFX.mote.controls.TreeItem;
import JFX.mote.layout.Panel;

public class TestComponent {
	private static Frame frame;
	public static void main(String[] args) {
		frame = new Frame("");
		frame.setPanel(Panel.instance("null"));
		frame.show();
		Test();
	}
	static void Test() {
		TestTree();
	}
	static void TestTree() {
		Tree t = new Tree();
		List<TreeItem> re = t.getValue();
		assertNotEquals(re,null);
	}
}
