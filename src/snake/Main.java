package snake;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import snake.cartesian.Direction;
import snake.gui.GUIController;
import snake.obj.Grid;
import snake.obj.Snake;

public class Main extends Application {

	public static final int WIDTH = 200;
	public static final int HEIGHT = 200;
	public static final int SIZE_OF_ONE_SQUARE = 10;
	
	private static final long TASK_PERIOD_IN_MS = 100;
	private static final long TASK_DELAY_IN_MS = 100;
	
	private GraphicsContext gc;
	private Group root;
	private Canvas canvas;
	private Scene scene;
	
	private Snake snake;
	private Grid grid;
	
	private AnimationTimer animationTimer;
	private Timer timer;
	private TimerTask task;
	
	private boolean gamePaused = true;
	
	@Override
	public void start(Stage stage) throws Exception {
	
		grid = new Grid();
		snake = new Snake();
		
		canvas = new Canvas(WIDTH, HEIGHT);
		gc = canvas.getGraphicsContext2D();
		
		root = new Group();
		root.getChildren().add(canvas);
		
		scene = new Scene(root);
		
		GUIController.updateGUI(snake, grid.getFood(), gc);
		
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.P) {
				if (gamePaused) {
					task = createTask(task);
					timer = new Timer();
					timer.scheduleAtFixedRate(task, TASK_DELAY_IN_MS, TASK_PERIOD_IN_MS);
					gamePaused = false;
				}
				else { 
					timer.cancel();
					gamePaused = true;
				}
			} else if (e.getCode() == KeyCode.UP) {
				snake.move(Direction.UP);
			} else if (e.getCode() == KeyCode.LEFT) {
				snake.move(Direction.LEFT);
			} else if (e.getCode() == KeyCode.DOWN) {
				snake.move(Direction.DOWN);
			} else if (e.getCode() == KeyCode.RIGHT) {
				snake.move(Direction.RIGHT);
			}
			
			System.out.println("Detected keypress");
		});
		
		stage.setTitle("Snake");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
		animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				GUIController.updateGUI(snake, grid.getFood(), gc);
			}
		};
		
		animationTimer.start();
		task = createTask(task);
		timer = new Timer();
		timer.scheduleAtFixedRate(task, TASK_DELAY_IN_MS, TASK_PERIOD_IN_MS);
	}
	
	private TimerTask createTask(TimerTask task) {
		task = new TimerTask() {

			@Override
			public void run() {
				snake.updateState();
				
				if (snake.isSnakeOutOfBounds()) {
					stopGame();
				} else if (snake.isFoodAte(grid.getFood().getPoint())) {
					snake.growSnake();
					grid.spawnFood();
				}  else if (snake.detectSnakeCollision()) {
					stopGame();
				}
			}
			
		};
		
		return task;
	}
	
	private void stopGame() {
		timer.cancel();
		animationTimer.stop();
		timer = null;
		reset();
	}
	
	private void reset() {
		snake = new Snake();
		grid = new Grid();
		GUIController.updateGUI(snake, grid.getFood(), gc);
		
		task = createTask(task);
		timer = new Timer();
		timer.scheduleAtFixedRate(task, TASK_DELAY_IN_MS, TASK_PERIOD_IN_MS);
		animationTimer.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

