package snake.gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import snake.Main;
import snake.cartesian.Point;
import snake.obj.Food;
import snake.obj.Snake;

public class GUIController {
	
	public static void updateGUI(Snake snake, Food food, GraphicsContext gc) {
		colorGrid(gc);
		colorFood(food, gc);
		colorSnake(snake, gc);
	}
	
	public static void colorGrid(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		for (int x=0; x<Main.HEIGHT; x += Main.SIZE_OF_ONE_SQUARE) {
			gc.strokeLine(x, 0, x, x + Main.WIDTH);
		}
			
		for (int y=0; y<Main.WIDTH; y += Main.SIZE_OF_ONE_SQUARE) {
			gc.strokeLine(0, y, y + Main.HEIGHT, y);
		}
	}
	
	private static void colorFood(Food food, GraphicsContext gc) {
		gc.setFill(Color.RED);
		colorPoint(food.getPoint(), gc);
	}
	
	private static void colorSnake(Snake snake, GraphicsContext gc) {
		gc.setFill(Color.SANDYBROWN);
		for (int i=0 ;i<snake.size(); i++) {
			colorPoint(snake.get(i), gc);
		}
	}
	
	private static void colorPoint(Point point, GraphicsContext gc) {
		gc.fillRect(point.getX(), point.getY(), Main.SIZE_OF_ONE_SQUARE, Main.SIZE_OF_ONE_SQUARE);
	}
}
