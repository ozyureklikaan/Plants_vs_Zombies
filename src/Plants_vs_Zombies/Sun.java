package Plants_vs_Zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class Sun extends JPanel implements MouseListener {
	private GamePanel gamePanel;
	private Image sunImage;
	
	private int myX;
	private int myY;
	private int endY;
	
	private int suicide = 200;
	
	public Sun(GamePanel parent, int startX, int startY, int endY) {
		this.gamePanel = parent;
		this.endY = endY;
		setSize(50,48);
		setOpaque(false);
		myX = startX;
		myY = startY;
		setLocation(myX,myY);
		sunImage = new ImageIcon(this.getClass().getResource("images/sun.gif")).getImage();
		addMouseListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		graph.drawImage(sunImage, 0, 0, null);
	}
	
	public void setFeature() {
		if(myY < endY) {
			myY += 4;
		} else {
			suicide--;
			if(suicide < 0) {
				RemoveSun();
			}
		}
		setLocation(myX, myY);
	}
	
	public void RemoveSun() {
		gamePanel.remove(this);
		gamePanel.getActiveSuns().remove(this);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		gamePanel.setSunCoin(gamePanel.getSunCoin() + 25);
		gamePanel.remove(this);
		gamePanel.getActiveSuns().remove(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
