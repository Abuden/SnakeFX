package snake.cartesian;

import snake.interfaces.Vec2D;

public class Velocity implements Vec2D {

	private int x;
	private int y;
	
	public Velocity() {
		x = y = 0;
	}
	
	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void translate(int translateAmountX, int translateAmountY) {
		this.x = translateAmountX;
		this.y = translateAmountY;
	}
}
