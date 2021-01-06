package JFX.mote;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class MathUtils{
	public static double RadtoDeg(double r) {
		return r*180/Math.PI;
	}
	private static double distance(double x, double y) {
		return Math.sqrt(x*x+y*y);
	}
	public static Number max(Number ...interger) {
		Number i = interger[0];
		for(Number n : interger) {
			i = n.doubleValue()>i.doubleValue()?n:i;
		}
		return i;
	}
	public static double RadInRange(double rad, int i) {
		return rad*i/Math.PI;
	}
}
