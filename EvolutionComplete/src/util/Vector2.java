package util;

public class Vector2 {
	public float x;
	public float y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2 other) {
		this(other.x, other.y);
	}

	
	public Vector2() {
		this(0, 0);
	}

}
