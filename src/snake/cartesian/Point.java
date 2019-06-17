package snake.cartesian;

import snake.interfaces.Vec2D;

public class Point implements Vec2D {
	private int x;
	private	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void translate(int translateAmountX, int translateAmountY) {
		this.x += translateAmountX;
		this.y += translateAmountY;
	}
	
	public void newPoint(int newX, int newY) {
		this.x = newX;
		this.y = newY;
	}
	
	public Point getPoint() {
		return new Point(this.x, this.y);
	}
	
	public boolean compare(Point comp) {
		return ((x == comp.getX()) && (y == comp.getY()));
	}
}
