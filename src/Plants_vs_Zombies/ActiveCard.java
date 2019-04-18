package Plants_vs_Zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class ActiveCard extends JPanel implements MouseListener {
	private Image image;
	private ActionListener actionListener;
	
	public ActiveCard(Image image) {
		setSize(96, 96);
		this.image = image;
		addMouseListener(this);
	}
	
	public void setAction(ActionListener actionListener) {
		this.actionListener = actionListener;
	}
	
	@Override
	protected void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		graph.drawImage(image, 0, 0, null);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(actionListener != null) {
			actionListener.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
