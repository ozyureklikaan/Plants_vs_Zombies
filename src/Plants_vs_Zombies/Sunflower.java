package Plants_vs_Zombies;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Sunflower extends Plant {
	private Timer sunProduceTimer;
	
	public Sunflower(GamePanel parent, int x, int y) {
		super(parent, x, y);
		sunProduceTimer = new Timer(10000, (ActionEvent e) -> {
			Sun sun = new Sun(getGamePanel(), 320 + x * 85, 80 + y * 110, 10 + y * 120);
			getGamePanel().getActiveSuns().add(sun);
			getGamePanel().add(sun, new Integer(1));
		});
		sunProduceTimer.start();
	}
	
	public void LoopStopper() {
		sunProduceTimer.stop();
	}
}
