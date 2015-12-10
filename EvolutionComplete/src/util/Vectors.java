package util;

import javax_.vecmath.Vector2d;

public class Vectors {
	/**
	 * In-place rotation of v by theta
	 * @param v
	 * @param theta
	 */
	public static void rotate(Vector2d v, double theta) {
		double s = Math.sin(theta);
		double c = Math.cos(theta);
		v.set(c*v.x - s*v.y, s*v.x + c*v.y);
	}
		
}
