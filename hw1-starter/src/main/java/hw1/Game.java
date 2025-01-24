package hw1;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class for the Flappy Box game.
 */
public class Game {
  private FlappyBox flappyBox;
  private List<Pipe> pipes;
  private int score;
  private boolean isGameOver;

  /**
   * Public constructor to initialize the game.
   */
  public Game() {
    setUpCanvas();
    setUpGameObjects();
    score = 0;
    isGameOver = false;
  }

  // Set up the canvas for the game.
  private void setUpCanvas() {
    StdDraw.setCanvasSize(GameConstant.CANVAS_WIDTH, GameConstant.CANVAS_HEIGHT);
    StdDraw.setXscale(0, GameConstant.CANVAS_WIDTH);
    StdDraw.setYscale(0, GameConstant.CANVAS_HEIGHT);
    StdDraw.enableDoubleBuffering();
  }

  // Set up the game objects.
  private void setUpGameObjects() {
    // TODO: Implement this method
  }

  /**
   * Start the game loop.
   */
  public void runGameLoop() {
    // TODO: Implement this method
    StdDraw.close();
  }

  // Move the flappy box and the pipes.
  private void moveGameObjects() {
    // TODO: Implement this method
  }

  // Return true if the flappy box collides with any pipe.
  private boolean handleCollisions() {
    // TODO: Implement this method
    return false;
  }

  // Update the score if the flappy box passes a pipe.
  private void updateScore() {
    // TODO: Implement this method
  }

  // Draw the flappy box and the pipes.
  private void drawGameObjects() {
    // TODO: Implement this method
  }

  // Remove the pipes that are out of the canvas and add new pipes.
  private void recyclePipes() {
    // TODO: Implement this method
  }

  // Display the current score on the screen
  private void displayScore() {
    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 32));
    StdDraw.setPenColor(GameConstant.TEXT_COLOR);
    StdDraw.textLeft(30, GameConstant.CANVAS_HEIGHT - 30, "Score: " + score);
  }

  // Display the game over screen.
  private void displayGameOver() {
    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 32));
    StdDraw.setPenColor(GameConstant.TEXT_COLOR);
    StdDraw.text(GameConstant.CANVAS_WIDTH / 2.0, GameConstant.CANVAS_HEIGHT / 2.0, "Game Over!");
    StdDraw.show();
  }

  /**
   * Main method.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    Game game = new Game();
    game.runGameLoop();
  }
}
