import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score
{
	 int left = 0, right = 0;
	 Font fnt;
	 
	 /**
	  * Font type and size
	  */
	 public Score()
	 {
		 fnt = new Font ("Verdana", Font.ITALIC, 20);	 
	 }
	 
	 /**
	  * Reseting scores to zero
	  */
	 public void reset()
	 {
		 left = right = 0;
	 }
	 
	 /**
	  * Draw the Left and Right scores
	  * @param g
	  */
	 public void draw(Graphics g)
	 {
		 g.setColor(Color.WHITE);
		 g.setFont(fnt);
		 g.drawString("Left:" + left + "  Right:" + right, 435, 60);
	 }

	 /**
	  * setters and getters
	  * @return
	  */
	public int getLeft()
	{
		return left;
	}

	public void setLeft(int left)
	{
		this.left = left;
	}

	public int getRight()
	{
		return right;
	}

	public void setRight(int right)
	{
		this.right = right;
	}
}
