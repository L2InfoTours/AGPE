package JFX.mote;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Mote
 *
 */
public class MathUtils{
	/** Convert Radiant to Degrees
	 * @param r
	 * @return
	 */
	public static double RadtoDeg(double r) {
		return r*180/Math.PI;
	}
	/** get the Distance between (0,0) and (@param x,@param y)
	 * @return double of distance
	 */
	public static double distance(double x, double y) {
		return Math.sqrt(x*x+y*y);
	}
	/** 
	 * get the max value from 
	 * @param a integer list
	 * @return the max value
	 */
	public static Number max(Number ...interger) {
		Number i = interger[0];
		for(Number n : interger) {
			i = n.doubleValue()>i.doubleValue()?n:i;
		}
		return i;
	}
	/**
	 * @return Convert @param Radiant to Value Between 0 and @param int
	 */
	public static double RadInRange(double rad, int i) {
		return rad*i/Math.PI;
	}
}
