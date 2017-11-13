import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball {
	int x, y, w, h, xdir, ydir, screenW, screenH;

	/**
	 * Constructor 1
	 * 
	 * @param x
	 * @param y
	 * @param xdir
	 * @param ydir
	 * @param w
	 * @param h
	 */
	public Ball(int x, int y, int xdir, int ydir, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.xdir = xdir;
		this.ydir = ydir;
		loadImage();
	}

	/**
	 * Constructor 2
	 * 
	 * @param x
	 * @param y
	 * @param xdir
	 * @param ydir
	 * @param w
	 * @param h
	 * @param screenW
	 * @param screenH
	 */
	public Ball(int x, int y, int xdir, int ydir, int w, int h, int screenW, int screenH) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.xdir = xdir;
		this.ydir = ydir;
		this.screenW = screenW;
		this.screenH = screenH;
		loadImage();
	}

	public Ball() {
		loadImage();
	}

	BufferedImage image = null;

	void loadImage() {
		try {
			image = ImageIO.read(new File("ball1.png"));
		} catch (IOException e) {

		}
	}
	/**
	 * This function updates ball position
	 */
	public void update() {
		x += xdir;
		y += ydir;
		if (y < 22) {
			y = 22;
			ydir = -ydir;
		} else if (y > screenH - 15) {
			y = screenH - 15;
			ydir = -ydir;
		}

	}

	/**
	 * Draw Ball Image
	 * @param g
	 */
	public void doDraw(Graphics g) {
		g.drawImage(image, x, y, w, h, null);
	}

	/**
	 * This function resets ball position to default
	 * @param x
	 * @param y
	 * @param xdir
	 * @param ydir
	 * @param w
	 * @param h
	 */
	public void reset(int x, int y, int xdir, int ydir, int w, int h) {
		this.x = x;
		this.y = y;
		this.xdir = xdir;
		this.ydir = ydir;
		this.w = w;
		this.h = h;
	}

	/**
	 * setters and getters
	 * @return
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getXdir() {
		return xdir;
	}

	public void setXdir(int xdir) {
		this.xdir = xdir;
	}

	public int getYdir() {
		return ydir;
	}

	public void setYdir(int ydir) {
		this.ydir = ydir;
	}

	public int getScreenW() {
		return screenW;
	}

	public void setScreenW(int screenW) {
		this.screenW = screenW;
	}

	public int getScreenH() {
		return screenH;
	}

	public void setScreenH(int screenH) {
		this.screenH = screenH;
	}

}
