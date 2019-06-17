package snake.obj;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Score {

	private int score;
	private Label scoreLabel;
	
	Score() {
		score = 0;
		scoreLabel = new Label("Score: " + score);
		scoreLabel.setTextFill(Color.BLACK);
	}
	
	public void incrementScore() {
		this.score++;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public Label getScoreLabelObject() {
		scoreLabel.setText("Score: " + score);
		return scoreLabel;
	}
	
}
