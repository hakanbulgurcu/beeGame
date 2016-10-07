import javax.swing.ImageIcon;

public class Hero extends Objects{

	public Hero() {
		setX(215);
		setY(0);
		imagePath = getClass().getResource("Bee.png");
		imageIcon = new ImageIcon(imagePath);
		Image = imageIcon.getImage();
		Speed = 10;
		setPoint(0);
		setHealth(1000);
	}
				
	public void moveRight(int width)
	{
    	if (getX() + Speed + getImageWidth() <= width)
    	{
    		setX(getX() + Speed);
    	}
    	else
    		setX(width - getImageWidth());
	}

	public void moveDown(int height)
	{
    	if (getY() + Speed + getImageHeight() <= height)
    	{
    		setY(getY() + Speed);
    	}
    	else
    		setY(height - getImageHeight());
	}

	public void moveUp()
	{
    	if (getY() - Speed >= 0)
    	{
    		setY(getY() - Speed);
    	}
    	else
    		setY(0);
	}

	public void moveLeft()
	{
    	if (getX() - Speed >= 0)
    	{
    		setX(getX() - Speed);
    	}
    	else
    		setX(0);
	}

}
