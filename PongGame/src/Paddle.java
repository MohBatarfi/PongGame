import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Paddle
{
	int x, y, w, h, screenW, screenH;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param screenW
	 * @param screenH
	 */
	public Paddle (int x, int y, int w, int h, int screenW, int screenH)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.screenW = screenW;
		this.screenH = screenH;
		loadImage();
	}
	
	BufferedImage image = null;
	void loadImage()
	{
		try
		{
			image = ImageIO.read(new File("p1.jpeg"));
		}
		catch (IOException e)
		{
			
		}
	}
	
	/**
	 * This function draw Paddle Image
	 * @param g
	 */
	public void doDraw(Graphics g)
	{
		g.drawImage(image,x,y,w,h,null);
	}

	/**
	 * This function moves the paddle
	 * @param dy
	 */
	public void movePaddle(int dy)
	{
		y += dy;
		
		if (y < 22) y = 22;
		if (y + h > screenH) y = screenH - h;
		
	}
	
	Random rnd = new Random();
	
	/**
	 * This function updates the computer paddle
	 * @param ballMidY
	 */
	void update (int ballMidY)
	{
		int midY = y + h /2;
		int rn = 1 - (rnd.nextInt() % 3);
		
		if (ballMidY > midY + 3)
		{
			movePaddle (3 + rn);
		}
		if (ballMidY < midY - 3)
		{
			movePaddle (-3 + rn);
		}
		
	}

	/**
	 * setters and getters
	 * @return
	 */
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getW()
	{
		return w;
	}

	public void setW(int w)
	{
		this.w = w;
	}

	public int getH()
	{
		return h;
	}

	public void setH(int h)
	{
		this.h = h;
	}

	public int getScreenW()
	{
		return screenW;
	}

	public void setScreenW(int screenW)
	{
		this.screenW = screenW;
	}

	public int getScreenH()
	{
		return screenH;
	}

	public void setScreenH(int screenH)
	{
		this.screenH = screenH;
	}
}
