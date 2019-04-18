package Plants_vs_Zombies;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FreezerPeaShooter extends Plant {
	private Timer shootTimer;
		
	public FreezerPeaShooter(GamePanel parent, int x, int y) {
		super(parent, x, y);
		shootTimer = new Timer(2000, (ActionEvent e) -> {
			if (getGamePanel().getLaneZombies().get(y).size() > 0) {
				getGamePanel().getLanePea().get(y).add(new FreezerPea(getGamePanel(), y, 103 + this.getX() * 100));
			}
		});
		shootTimer.start();
	}
	
	@Override
	public void Stop() {
		shootTimer.stop();
	}
}
