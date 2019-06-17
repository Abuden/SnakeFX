package snake.obj;

import snake.Main;
import snake.cartesian.Direction;
import snake.cartesian.Point;
import snake.cartesian.Velocity;
import snake.util.SinglyLinkedList;

public class Snake extends SinglyLinkedList<Point> {
	private Velocity velocity;
	
	public Snake () {
		velocity = new Velocity();
		addFirst(new Point(Main.WIDTH/2, Main.HEIGHT/2));
	}
	
	public void growSnake() {
		addFirst(new Point(first().getX(), first().getY()));
		
		System.out.println(size());
	}

	public void move(Direction direction) {
		switch(direction) {
			case UP:
				if (velocity.getY() != 10)
					velocity.translate(0, -10);
				break;
			case LEFT:
				if (velocity.getX() != 10)
					velocity.translate(-10, 0);
				break;
			case DOWN:
				if (velocity.getY() != -10)
					velocity.translate(0, 10);
				break;
			case RIGHT:
				if (velocity.getX() != -10)
					velocity.translate(10, 0);
				break;
		}
	}
	
	public void updateState() {
		if (size () > 1) {
			removeLast();
			addFirst(new Point(first().getX(), first().getY()));
		}
		
		first().translate(velocity.getX(), velocity.getY());
	}
	
	public boolean isSnakeOutOfBounds() {
		return  ((	velocity.getX() == 10 && first().getX() == Main.WIDTH)
				|| (velocity.getX() == -10 && first().getX() == -10)
				|| (velocity.getY() == 10 && first().getY() == Main.HEIGHT)
				|| (velocity.getY() == -10 && first().getY() == -10)); 
	}
	
	public boolean detectSnakeCollision() {
		for (int i=1; i<size(); i++)
			if (isIllegalState(i))
				return true;
		
		return false;
	}
	
	private boolean isIllegalState(int indexOfBody) {
		return (first().compare(get(indexOfBody)));
	}
	
	public boolean isFoodAte(Point foodPoint) {
		return (first().compare(foodPoint));
	}
}

