package Plants_vs_Zombies;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class Collision extends JPanel implements MouseListener {
	private ActionListener actionListener;
	public Plant plant;
	
	public Collision() {
		setOpaque(false);
		addMouseListener(this);
		setSize(70, 80);
	}
	
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
	public void PlantRemove() {
		plant.Stop();
		plant = null;
	}
	
	public boolean theCollider(int loc) {
		return (loc > getLocation().x && (loc < getLocation().x + 100));
	}
	
	public void setAction(ActionListener actionListener) {
		this.actionListener = actionListener;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(actionListener != null) {
			actionListener.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
		}
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
