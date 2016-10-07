import javax.swing.ImageIcon;

public class Needle extends Objects{
	private int direction;
	public Needle(int intX, int intY, int direction) {
		setX(intX);
		setY(intY);
		switch (direction){
			case 0: imagePath = getClass().getResource("NeedleUp.png"); break;
			case 1: imagePath = getClass().getResource("NeedleRight.png"); break;
			case 2: imagePath = getClass().getResource("NeedleDown.png"); break;
			case 3: imagePath = getClass().getResource("NeedleLeft.png"); break;
		}
		imageIcon = new ImageIcon(imagePath);
		Image = imageIcon.getImage();
		Speed = 40;
		setPoint(100);
		setHealth(100);
		this.direction = direction;
	}
				
	public void imageReverse()
	{
		
	}
	
	public void move(int height, int width)
	{			
		if (direction == 1) //RIGHT
		{
			if (getX() + Speed + getImageWidth() <= width)
	    	{
	    		setX(getX() + Speed);
	    	}
	    	else
	    		setX(width - getImageWidth());
		}
		
		if(direction == 2) //DOWN
		{
	    	if (getY() + Speed + getImageHeight() <= height)
	    	{
	    		setY(getY() + Speed);
	    	}
	    	else
	    		setY(height - getImageHeight());
		}
		
		if(direction == 3) //LEFT
		{
	    	if (getX() - Speed >= 0)
	    	{
	    		setX(getX() - Speed);
	    	}
	    	else
	    		setX(0);

		}
		
		if(direction == 0) //UP
		{
	    	if (getY() - Speed >= 0)
	    	{
	    		setY(getY() - Speed);
	    	}
	    	else
	    		setY(0);
		}
	}

}
