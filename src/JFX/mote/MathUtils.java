package JFX.mote;

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
	public static void main(String[] args) {
		System.out.println(RadtoDeg(cosh(45,45)));
	}
}
