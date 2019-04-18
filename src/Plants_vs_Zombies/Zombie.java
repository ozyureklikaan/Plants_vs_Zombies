package Plants_vs_Zombies;

import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionListener;

public class Zombie {
	private int health = 1000;
	private int speed = 1;
	private int slow = 0;
	private GamePanel gamePanel;
	
	private int posX = 1000;
	private int myLane;
	private boolean isWalking = true;
	
	public Zombie(GamePanel parent, int lane) {
		this.gamePanel = parent;
		myLane = lane;
		System.out.println(myLane);
	}
	
	public void setFeature() {
		if(isWalking) {
			boolean isCollision = false;
			Collision collisioned = null;
			for(int i= myLane * 7; i<(myLane + 1) * 7; i++ ) {
				if(gamePanel.getCollisions()[i].plant != null && gamePanel.getCollisions()[i].theCollider(posX)) {
					isCollision = true;
					collisioned = gamePanel.getCollisions()[i];
				}
			}
			if(!isCollision) {
				if(slow > 0) {
					if(slow % 2 == 0) {
						posX--;
					}
					slow--;
				} else {
					posX -= 1;
				}
			} else {
				collisioned.plant.setHealth(collisioned.plant.getHealth() - 10);
				if(collisioned.plant.getHealth() < 0) {
					collisioned.PlantRemove();
				}
			}
			if(posX < 250) {
				GamePanel.isGameOver = true;
				isWalking = false;
				JOptionPane.showMessageDialog(gamePanel, "ZOMBIES ATE YOUR BRAIN" + '\n' + "Starting the level again");
				GameWindow.gameWindow.dispose();
				GameWindow.gameWindow = new GameWindow();
			}
		}	
	}
	
	public void Slow() {
		slow = 1000;
	}
	
	public static Zombie getZombie(String type, GamePanel parent, int lane ) {
		Zombie zombie = new Zombie(parent, lane);
		switch(type) {
		case "NormalZombie":
			zombie = new NormalZombie(parent, lane);
			break;
		case "ConeHeadedZombie":
			zombie = new ConeHeadedZombie(parent, lane);
			break;
		}
		return zombie;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
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
	
	public boolean isWalking() {
		return isWalking;
	}
	
	public void setWalking(boolean walking) {
		isWalking = walking;
	}
	
	public int getSlow() {
		return slow;
	}
	
	public void setSlow(int slow) {
		this.slow = slow;
	}
}
