package Plants_vs_Zombies;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PeaShooter extends Plant {
	private Timer shootTimer;
	
	public PeaShooter(GamePanel parent, int x, int y) {
		super(parent, x, y);
		shootTimer = new Timer(2000, (ActionEvent e) -> {
			if (getGamePanel().getLaneZombies().get(y).size() > 0) {
				getGamePanel().getLanePea().get(y).add(new Pea(getGamePanel(), y, 65 + this.getX() * 88));
			}
		});
		shootTimer.start();
	}
	
	@Override
	public void Stop() {
		shootTimer.stop();
	}
}
