package JFX.mote;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class MathUtils{

	public static double cosh(double x, double y) {
		double d = MathUtils.distance(x,y);
		double cos = Math.acos(x/d);
		double sin = Math.asin(y/d);
		double rad = (cos+sin)/2;
		
		return rad;
	}
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
	public static void main(String[] args) {
		System.out.println(cosh(5,4));
		System.out.println(max(1,2,3));
	}
	public static double RadInRange(double rad, int i) {
		return rad*i/Math.PI;
	}
}
