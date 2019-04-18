package Plants_vs_Zombies;

import java.awt.*;

public class FreezerPea extends Pea {
	public FreezerPea(GamePanel parent, int lane, int startX) {
		super(parent, lane, startX);
	}
	
	@Override
	public void setFeature() {
		Rectangle plantRec = new Rectangle(getPosX(), 130 + getMyLane() * 120, 28, 28);
		for (int i = 0; i < gamePanel.getLaneZombies().get(getMyLane()).size(); i++) {
			Zombie zombie = gamePanel.getLaneZombies().get(getMyLane()).get(i);
			Rectangle zombieRec = new Rectangle(zombie.getPosX(), 109 + getMyLane() * 120, 400, 120);
			if(plantRec.intersects(zombieRec)) {
				zombie.setHealth(zombie.getHealth() - 300);
				zombie.Slow();
				boolean isDied = false;
				if(zombie.getHealth() <= 0) {
					System.out.println("ZOMBIE IS DEAD");
					gamePanel.getLaneZombies().get(getMyLane()).remove(i);
					GamePanel.setProggres(10);
					gamePanel.setScore(10);
					isDied = true;
					//Zombie death affect
				}
				gamePanel.getLanePea().get(getMyLane()).remove(this);
				if(isDied) {
					break;
				}
			}
			setPosX(getPosX() + 15);
		}
	}
}
