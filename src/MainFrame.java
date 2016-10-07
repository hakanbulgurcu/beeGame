import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6418267975977569096L;
	MainPanel mPanel;
	int panelHeight = 320 + 30 + 8; //30 üst menü 8 alt menü geniþliði
	int panelWidth = 480 + 8 + 8;  //8 sað menü 8 sol menü geniþliði
	boolean ctrlCheck = false;

	public MainFrame() 
	{
		setTitle("Ahmet's Game");
		mPanel = new MainPanel(panelHeight, panelWidth);
		setSize(panelWidth, panelHeight);				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
		add(mPanel); // add MainPanel JPanel to JFrame
		setVisible(true); // show class
		new Timer(50, titleUpdate).start();
	}

	public static void main(String[] args) {
		new MainFrame();
	}
	
	
	Action titleUpdate = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Hero myHero = mPanel.myHero;
			setTitle("Ahmet's Game --> Health "+myHero.getHealth()+" Point "+myHero.getPoint());
			repaint();
		}
	};

}

