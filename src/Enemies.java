import java.util.Random;
import javax.swing.ImageIcon;

public class Enemies extends Objects{
		
	public Enemies(int width, int height) {
		Random generator = new Random();
		setX(generator.nextInt(width));
		setY(generator.nextInt(height));
		imagePath = getClass().getResource("Spider.png");
		imageIcon = new ImageIcon(imagePath);
		Image = imageIcon.getImage();
		Speed = 10;
		setPoint(200);
		setHealth(100);
	}
	
					
	
	public void move(int height, int width, Objects myHero)
	{		
		Random generator = new Random();
		int direction = generator.nextInt(7);
		
		switch(generator.nextInt(3)){
		case 0: 
		if(myHero.getX()<getX())
			direction = 3;
			else
				direction = 1;break;
		case 1:
			if(myHero.getY()<getY())	
				direction = 0;
			else 
				direction = 2;break;
		}
		
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
