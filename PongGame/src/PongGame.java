import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

/**
 * This PongGame main class
 * 
 * @author Mohammed Batarfi
 */

public class PongGame extends JFrame implements KeyListener, Runnable {

	Paddle human, computer;
	Thread thrd;
	Ball ball;
	Score score;

	int screenW = 1000;
	int screenH = 700;

	BufferedImage drawBuffer;

	Clip clip;
	AudioInputStream inputStream;

	BufferedImage image = null;

	void loadImage() {
		try {
			image = ImageIO.read(new File("bg1.jpg"));
		} catch (IOException e) {

		}
	}

	public PongGame() {
		// Set the screen
		setSize(screenW, screenH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Creating objects
		human = new Paddle(15, 330, 10, 100, screenW, screenH);
		computer = new Paddle(970, 330, 10, 100, screenW, screenH);
		ball = new Ball(493, 343, 8, 5, 15, 15, screenW, screenH);
		score = new Score();

		drawBuffer = new BufferedImage(screenW, screenH, BufferedImage.TYPE_INT_RGB);
		loadImage();

		// Key listener
		addKeyListener(this);
		thrd = new Thread(this);
		thrd.start();

	}

	public static void main(String[] args) {
		PongGame g1 = new PongGame();
		g1.setVisible(true);

	}

	/**
	 * This method repaints the screen
	 */
	public void repaint() {
		Graphics g = drawBuffer.getGraphics();
		paint(g);
		getGraphics().drawImage(drawBuffer, 0, 0, null);
	}

	/**
	 * This method paint the screen with the objects initiated
	 */
	public void paint(Graphics g) {

		g.drawImage(image, 0, 0, screenW, screenH, null);
		g.setColor(Color.WHITE);
		g.drawLine(500, 0, 500, 800);

		human.doDraw(g);
		computer.doDraw(g);
		ball.doDraw(g);
		score.draw(g);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	boolean upPressed = false, downPressed = false;

	/**
	 * Setting Up and Down keys to move the paddle (when pressed)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = true;
		}

	}

	/**
	 * Setting Up and Down keys to stop moving the paddle (when released)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = false;
		}

	}

	/**
	 * This method runs the game 
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(25);
			} catch (Exception ex) {

			}
			// Update ball position
			ball.update();
			computer.update(ball.getY() + ball.getW() / 2);

			if (upPressed) {
				human.movePaddle(-5);
			} else if (downPressed) {
				human.movePaddle(5);
			}

			// Human Paddle
			if (ball.getY() < human.getY() + human.getH() && ball.getY() + ball.getH() > human.getY()) {
				if (ball.getX() < human.getX() + human.getW()) {
					ball.setX(human.getX() + human.getW());
					ball.setXdir(-ball.getXdir());

					try {
						clip = AudioSystem.getClip();
						inputStream = AudioSystem.getAudioInputStream(new File("click.wav"));
						clip.open(inputStream);
						clip.start();
					} catch (Exception ex) {
					}

				}
			} else if (ball.getX() < 0) {
				score.setRight(score.getRight() + 1);
				ball.reset(screenW / 2, screenH / 2, -8, 5, 15, 15);

			}

			// Computer Paddle
			if (ball.getY() < computer.getY() + computer.getH() && ball.getY() + ball.getH() > computer.getY()) {
				if (ball.getX() + ball.getW() > computer.getX()) {
					ball.setX(computer.getX() - ball.getW());
					ball.setXdir(-ball.getXdir());

					try {
						clip = AudioSystem.getClip();
						inputStream = AudioSystem.getAudioInputStream(new File("click.wav"));
						clip.open(inputStream);
						clip.start();
					} catch (Exception ex) {
					}

				}
			} else if (ball.getX() + ball.getW() > screenW) {
				score.setLeft(score.getLeft() + 1);
				ball.reset(screenW / 2, screenH / 2, 8, 5, 15, 15);

			}

			repaint();
		}

	}

}
