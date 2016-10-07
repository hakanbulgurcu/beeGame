import java.util.Random;
import javax.swing.ImageIcon;

public class Collections extends Objects{
		
	public Collections(int intX , int intY) {
		Random generator = new Random();		
		switch (generator.nextInt(10)) {
        case 0: imagePath = getClass().getResource("Orkide.png");
        		setPoint(500);
        		setHealth(50);        
        		break;
        case 1:
        case 2:
        case 3: imagePath = getClass().getResource("Rose.png");
        		setPoint(300);
        		setHealth(30);        
        		break;
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9: imagePath = getClass().getResource("Blue.png");
        		setPoint(100);
        		setHealth(10);        
        		break;
		}
		
		imageIcon = new ImageIcon(imagePath);
		Image = imageIcon.getImage();
		
		if(intX > getImageWidth())
			setX(intX-getImageWidth());			
		else
			setX(intX);
		
		if(intY > getImageHeight())
			setY(intY-getImageHeight());
		else
			setY(intY);
		
	}				
		
}
