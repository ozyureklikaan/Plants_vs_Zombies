package Plants_vs_Zombies;

public class Plant {
	private int health = 200;
	
	private int x;
	private int y;
	
	private GamePanel gamePanel;
	
	public Plant(GamePanel parent, int x, int y) {
		this.x = x;
		this.y = y;
		gamePanel = parent;
	}
	
	public void Stop() {
		if (this.getClass().getTypeName().equals("Plants_vs_Zombies.Sunflower")) {
			((Sunflower)this).LoopStopper();
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
}
