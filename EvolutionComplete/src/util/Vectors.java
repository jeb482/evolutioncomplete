package util;

import javax_.vecmath.Vector2d;
import javax_.vecmath.Vector3d;

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
	
	/**
	 * Gives the cross product of two Vector2d objects in implicit 3-space
	 * 
	 * @param u
	 * @param v
	 * @return W
	 */
	public static Vector3d cross(Vector2d u, Vector2d v) {
		Vector3d u3 = new Vector3d(u.x, u.y, 0);
		Vector3d v3 = new Vector3d(v.x, v.y, 0);
		Vector3d out = new Vector3d();
		
		out.cross(u3, v3);
		return out;
	}
	
	public static double sinTheta(Vector2d u, Vector2d v) {
		Vector2d normalU = new Vector2d(u);
		Vector2d normalV = new Vector2d(v);
		normalU.normalize();
		normalV.normalize();
		return cross(normalU,normalV).z;
	}
	
	public static double signCross(Vector2d u, Vector2d v) {
		return Math.signum(cross(u,v).z);
	}
		
}
