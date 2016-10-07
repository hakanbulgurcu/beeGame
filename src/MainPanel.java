import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class MainPanel extends JPanel implements KeyListener { // inherit JPanel
	/**
	 * 
	 */
	private static final long serialVersionUID = 657370957399188201L;
	private int frameHeight = 0;
	private int frameWidth = 0;
	private int maxEnemies = 1;
	private int maxFlowers = 1;
	private int maxWebs = 10;
	public int maxNeedles = 10;
	
	Hero myHero = new Hero();
	ArrayList<Enemies> myEnemies = new ArrayList<Enemies>();
	ArrayList<Collections> myFlowers = new ArrayList<Collections>();
	ArrayList<Web> myWebs = new ArrayList<Web>();
	ArrayList<Needle> myNeedles = new ArrayList<Needle>();
	
	// Constructor:-----------------------------------------------------
	public MainPanel(int width, int height) 
	{
		setFocusable(true); //Keylistener'ýn çalýþmasý için gerekli
        addKeyListener(this);
		frameWidth = width;
		frameHeight = height;
		createObjects();
		new Timer(250, enemyMoveTimer).start();
		new Timer(1000, enemyCreateTimer).start();
		new Timer(10, CollisionTimer).start();
		new Timer(200, weaponMoveTimer).start();		  
	}

	public void createObjects()
	{
		Random generator = new Random();		
		if (myEnemies.size() < maxEnemies)
		{
			if(generator.nextInt(5) == 0)
				myEnemies.add(new Enemies(frameWidth, frameHeight));
		}

		if ((myFlowers.size() < maxFlowers) && frameHeight!=0)
		{
			myFlowers.add(new Collections(generator.nextInt(frameWidth),generator.nextInt(frameHeight)));
		}

		for (int i = 0; i < myEnemies.size();i++)
		{
			if(myEnemies.get(i).alignmentCheck(myHero) != -1)
			{
				if (myWebs.size() < maxWebs)
				{
					if(generator.nextInt(3)==0)
						myWebs.add(new Web(myEnemies.get(i).getX(), myEnemies.get(i).getY(), myEnemies.get(i).alignmentCheck(myHero)));				
				}
			}
		}
		
		prepareImages();			
	}
	
	public void prepareImages()
	{
		prepareImage(myHero.getImage(), this);
				
		for (int i = 0; i < myEnemies.size() ; i++)
		{
			prepareImage(myEnemies.get(i).getImage(), this);
		}
				
		for (int i = 0; i < myFlowers.size() ; i++)
		{
			prepareImage(myFlowers.get(i).getImage(), this);
		}

		for (int i = 0; i < myWebs.size() ; i++)
		{
			prepareImage(myWebs.get(i).getImage(), this);
		}

		for (int i = 0; i < myNeedles.size() ; i++)
		{
			prepareImage(myNeedles.get(i).getImage(), this);
		}
		
		setDoubleBuffered(true);
	}
	
	// ----------------------------------------------------------------

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		//g.clearRect(0, 0, getWidth(), getHeight()); //rengi sýfýrlýyor
		
	    g2d.drawImage(myHero.getImage(), myHero.getX(), myHero.getY(), this); //Draws the hero Image at the correct X and Y co-ordinates.
		
		for (int i = 0; i < myEnemies.size() ; i++)
		{
			g2d.drawImage(myEnemies.get(i).getImage(), myEnemies.get(i).getX(), myEnemies.get(i).getY(), this); //Draws the hero Image at the correct X and Y co-ordinates.									
		}

		for (int i = 0; i < myFlowers.size() ; i++)
		{
			g2d.drawImage(myFlowers.get(i).getImage(), myFlowers.get(i).getX(), myFlowers.get(i).getY(), this); //Draws the hero Image at the correct X and Y co-ordinates.									
		}

		for (int i = 0; i < myWebs.size() ; i++)
		{
			g2d.drawImage(myWebs.get(i).getImage(), myWebs.get(i).getX(), myWebs.get(i).getY(), this); //Draws the hero Image at the correct X and Y co-ordinates.									
		}

		for (int i = 0; i < myNeedles.size() ; i++)
		{
			g2d.drawImage(myNeedles.get(i).getImage(), myNeedles.get(i).getX(), myNeedles.get(i).getY(), this); //Draws the hero Image at the correct X and Y co-ordinates.									
		}
		
		Toolkit.getDefaultToolkit().sync(); // necessary for linux users to draw  and animate image correctly
		g.dispose();
		frameHeight = this.getHeight();
		frameWidth = this.getWidth();
		this.maxFlowers= (frameHeight*frameWidth/(myHero.getImageHeight()*myHero.getImageWidth()*100)*3);
		this.maxEnemies= (frameHeight*frameWidth/(myHero.getImageHeight()*myHero.getImageWidth()*100)*2); 
		this.maxWebs= this.maxEnemies;
		this.maxNeedles = this.maxEnemies * 2;
//		System.out.println(maxFlowers+"-"+myFlowers.size()+"-"+maxEnemies);
	}

	Action enemyMoveTimer = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < myEnemies.size() ; i++)
			{
				myEnemies.get(i).move(frameHeight, frameWidth, myHero);
			}

			prepareImages();
			repaint();
		}
	};

	Action weaponMoveTimer = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Random generator = new Random();		
			for (int i = 0; i < myWebs.size() ; i++)
			{
				myWebs.get(i).move(frameHeight, frameWidth);
			}

			for (int i = 0; i < myNeedles.size() ; i++)
			{
				myNeedles.get(i).move(frameHeight, frameWidth);
				if((myNeedles.get(i).getX()+myNeedles.get(i).getImageWidth()==frameWidth)||(myNeedles.get(i).getY()+myNeedles.get(i).getImageHeight()==frameHeight)||(myNeedles.get(i).getX()==0)||(myNeedles.get(i).getY()==0))
					if(generator.nextInt(7)==0)
						myNeedles.remove(i);
			}

			prepareImages();
			repaint();
		}
	};
	
	
	Action enemyCreateTimer = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			createObjects();
			repaint();
		}
	};

	Action CollisionTimer = new AbstractAction() { 
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < myFlowers.size() ; i++)
			{
				if(myFlowers.get(i).collisionCheck(myHero))
				{
					myHero.setPoint(myHero.getPoint() + myFlowers.get(i).getPoint());
					myHero.setHealth(myHero.getHealth() + myFlowers.get(i).getHealth());
					myFlowers.remove(i);
				}
			}
			
			for (int i = 0; i < myEnemies.size() ; i++)
			{
				if(myEnemies.get(i).collisionCheck(myHero))
				{
					myHero.setPoint(myHero.getPoint() + myEnemies.get(i).getPoint());
					myHero.setHealth(myHero.getHealth() - myEnemies.get(i).getHealth());
					myEnemies.remove(i);
				}												
			}			

			for (int i = 0; i < myWebs.size() ; i++)
			{
				if(myWebs.get(i).collisionCheck(myHero))
				{
					myHero.setPoint(myHero.getPoint() + myWebs.get(i).getPoint());
					myHero.setHealth(myHero.getHealth() - myWebs.get(i).getHealth());
					myWebs.remove(i);
				}
			}

			for (int i = 0; i < myNeedles.size() ; i++)
			{
				for (int j = 0; j < myEnemies.size(); j++)
				{
					if(myNeedles.get(i).collisionCheck(myEnemies.get(j)))
					{
						myHero.setPoint(myHero.getPoint() + myEnemies.get(j).getPoint());
						myEnemies.remove(j);
					}
				}
			}

			for (int i = 0; i < myNeedles.size() ; i++)
			{
				for (int j = 0; j < myWebs.size(); j++)
				{
					if(myNeedles.get(i).collisionCheck(myWebs.get(j)))
					{
						myHero.setPoint(myHero.getPoint() + myWebs.get(j).getPoint());
						myWebs.remove(j);
					}
				}
			}

			prepareImages();
			repaint();			
		}
	};

	@Override
    public void keyPressed(KeyEvent ke) 
    {
//		System.out.println(mPanel.getHeight() + "-" + mPanel.getWidth()+ "-" + this.getHeight()+ "-" + this.getWidth() + "-" + myHero.getX()+ "-" + myHero.getY() + "-" + myHero.imagePath);
		switch (ke.getKeyCode()) {
			//if the right arrow in keyboard is pressed...
            case KeyEvent.VK_RIGHT: {
            	if (ke.isControlDown() && (maxNeedles > myNeedles.size()))
            		myNeedles.add(new Needle(myHero.getX()+myHero.getImageWidth()/2,myHero.getY()+myHero.getImageHeight()/2,1));
            	else
            		myHero.moveRight(getWidth());
            }
            break;
            //if the left arrow in keyboard is pressed...
            case KeyEvent.VK_LEFT: {
            	if (ke.isControlDown() && (maxNeedles > myNeedles.size()))
            		myNeedles.add(new Needle(myHero.getX()+myHero.getImageWidth()/2,myHero.getY()+myHero.getImageHeight()/2,3));
            	else
            		myHero.moveLeft();
            }
            break;
            //if the down arrow in keyboard is pressed...
            case KeyEvent.VK_DOWN: {
            	if (ke.isControlDown() && (maxNeedles > myNeedles.size()))
            		myNeedles.add(new Needle(myHero.getX()+myHero.getImageWidth()/2,myHero.getY()+myHero.getImageHeight()/2,2));
            	else
            		myHero.moveDown(getHeight());
            }
            break;
            //if the up arrow in keyboard is pressed...
            case KeyEvent.VK_UP: {
            	if (ke.isControlDown() && (maxNeedles > myNeedles.size()))
            		myNeedles.add(new Needle(myHero.getX()+myHero.getImageWidth()/2,myHero.getY()+myHero.getImageHeight()/2,0));
            	else
            		myHero.moveUp();
            }
            break;
        }
		System.out.println(myHero.getHealth() + "-" + myHero.getPoint());
        repaint();
    }

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
	}

}

