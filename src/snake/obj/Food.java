package snake.obj;

import snake.cartesian.Point;

public class Food {
	
	private Point point;
	
	Food (Point point) {
		this.point = point;
	}
	
	public Point getPoint() {
		return this.point;
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}
	
	public int getX() {
		return this.point.getX();
	}
	
	public int getY() {
		return this.getPoint().getY();
	}
}