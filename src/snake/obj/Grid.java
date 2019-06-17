package snake.obj;

import java.util.Random;

import snake.Main;
import snake.cartesian.Point;

public class Grid {
	
	private Food food;
	
	public Grid() {
		food = new Food(new Point(Main.WIDTH/2 - 10, Main.HEIGHT/2 - 10));
	}
	
	public void spawnFood() {
		food.setPoint(generateRandomPoint());
	}
	
	private Point generateRandomPoint() {
		Random random = new Random();
		Point temp = food.getPoint();
		
		Point point = null;
		
		do {
			int x = random.nextInt(Main.WIDTH);
			int y = random.nextInt(Main.HEIGHT);
			
			x = Math.round(x / Main.SIZE_OF_ONE_SQUARE) * Main.SIZE_OF_ONE_SQUARE;
			y = Math.round(y / Main.SIZE_OF_ONE_SQUARE) * Main.SIZE_OF_ONE_SQUARE;
			
			point = new Point(x, y);
		
		} while (point.compare(temp));
		
		return point;
	}
	
	public Food getFood() {
		return this.food;
	}
}
