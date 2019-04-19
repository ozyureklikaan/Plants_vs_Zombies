package Plants_vs_Zombies;

import java.awt.*;

public class Pea {
	private int posX;
	protected GamePanel gamePanel;
	private int myLane;
	
	public Pea(GamePanel parent, int lane, int startX) {
		this.gamePanel = parent;
		this.myLane = lane;
		posX = startX + 280;
	}
	
	public void setFeature() {
		Rectangle plantRec = new Rectangle(posX, 40 + myLane * 110, 12, 80);
		for (int i = 0; i < gamePanel.getLaneZombies().get(myLane).size(); i++) {
			Zombie zombie = gamePanel.getLaneZombies().get(myLane).get(i);
			Rectangle zombieRec = new Rectangle(zombie.getPosX(), 40 + myLane * 110, 70, 80);
			if(plantRec.intersects(zombieRec)) {
				zombie.setHealth(zombie.getHealth() - 300);
				if(zombie.getHealth() <= 0) {
					System.out.println("ZOMBIE DIED");
					GamePanel.setProggres(10);
					gamePanel.setScore(10);
					////
					gamePanel.getlaneDeathZombies().get(myLane).add(zombie);
					gamePanel.getLaneZombies().get(myLane).remove(i);
					zombie.setDeath();
					////
				}
				gamePanel.getLanePea().get(myLane).remove(this);
			}
		}
		posX += 15;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getMyLane() {
		return myLane;
	}
	
	public void setMyLane(int myLane) {
		this.myLane = myLane;
	}
}
