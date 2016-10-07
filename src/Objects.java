import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;


public class Objects {
	/*
	 * Simple Object class to store the object's general properties.
	 */
	private int x;
	private int y;
	public int Speed;
	private int point;
	private int health;
	URL imagePath;
	ImageIcon imageIcon;
	Image Image; // Reserve memory for Image heroImage; // get ImageIcon imageIcon and store it in Image Image

	Objects() {
		;
	} // default constructor

	public Image getImage()
	{
		return Image;	
	}

	public int getImageHeight()
	{
		return Image.getHeight(null);	
	}
	
	public int getImageWidth()
	{
		return Image.getWidth(null);
	}

	public int getX() { // accessor/GET method for data
		return x;
	}

	public void setX(int x2) { // mutator/SET method for data
		x = x2;
	}

	public int getY() {
		return y;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int intPoint) {
		point=intPoint;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int intHealth) {
		health=intHealth;
	}

	public void setY(int y2) {
		y = y2;
	}

	public boolean collisionCheck(Objects Obj)
	{
		if (((Obj.getX() + Obj.getImageWidth()) < getX()) || (Obj.getX() > (getX() + getImageWidth())))
		{
			return false;
		}
		else if (((Obj.getY() + Obj.getImageHeight()) < getY()) || (Obj.getY() > (getY() + getImageHeight())))
		{
			return false;
		}
		else return true;
	}

	public int alignmentCheck(Objects Obj)
	{
		if (((Obj.getX() + Obj.getImageWidth()/2) < (getX()+getImageWidth())) && ((Obj.getX() + Obj.getImageWidth()/2) > getX()))
		{
			if (Obj.getY()<getY())
				return 0;
			else
				return 2;
			
		}
		else if (((Obj.getY() + Obj.getImageHeight()/2) < (getY() + getImageWidth())) && ((Obj.getY() + Obj.getImageHeight()/2) > getY()))
		{
			if (Obj.getX()<getX())
				return 3;
			else
				return 1;			
		}
		else return -1;
	}

}