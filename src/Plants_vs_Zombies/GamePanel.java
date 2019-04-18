package Plants_vs_Zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class GamePanel extends JLayeredPane implements MouseMotionListener {
	private Image backgroundImage;
	private Image peaShooterImage;
	private Image freezerPeaShooterImage;
	private Image sunFlowerImage;
	private Image peaImage;
	private Image freezerPeaImage;
	private Image normalZombieImage;
//	private Image normalZombieDyingImage;
	private Image coneHeadedZombieImage;
//	private Image coneHeadedZombieDyingImage;
	private Image gameOverImage;
	private Collision[] collisions;
	
	private ArrayList<ArrayList<Zombie>> laneZombies;
	private ArrayList<ArrayList<Pea>> lanePeas;
	private ArrayList<Sun> activeSunflower;
	
	private Timer redRawTimer;
	private Timer progressionTimer;
	private Timer sunCoinProducer;
	private Timer incomingZombieProducer;
	
	private int producedZombies;
	
	private JLabel sunCoinBoard;
	private JLabel scoreBoard;
	private JLabel scoreBoard2;
	
	private GameWindow.PlantType activeCardPlanting = GameWindow.PlantType.None;
	
	@SuppressWarnings("unused")
	private int mouseX, mouseY;
	
	private int sunCoin;
	private int score;
	
	public static boolean isGameOver;
	static int progress = 0;
	
	public GamePanel(JLabel sunCoinBoard, JLabel scoreBoard, JLabel scoreBoard2) {
		setSize(1000, 602);
		setLayout(null);
		addMouseMotionListener(this);
		this.sunCoinBoard = sunCoinBoard;
		setSunCoin(150);
		this.scoreBoard = scoreBoard;
		setScore(0);
		this.scoreBoard2 = scoreBoard2;
		this.scoreBoard2.setText("SCORE: ");
		isGameOver = false;
		producedZombies = 0;
		
		backgroundImage = new ImageIcon(this.getClass().getResource("images/backyard.jpg")).getImage();
		gameOverImage = new ImageIcon(this.getClass().getResource("images/gameOver.jpg")).getImage();
		
		peaShooterImage = new ImageIcon(this.getClass().getResource("images/pea_shooter.gif")).getImage();
		freezerPeaShooterImage = new ImageIcon(this.getClass().getResource("images/beetroot.gif")).getImage();
		sunFlowerImage = new ImageIcon(this.getClass().getResource("images/sun_flower.gif")).getImage();
		peaImage = new ImageIcon(this.getClass().getResource("images/pea.png")).getImage();
		freezerPeaImage = new ImageIcon(this.getClass().getResource("images/beetbullet.png")).getImage();
		
		normalZombieImage = new ImageIcon(this.getClass().getResource("images/zombie_normal.gif")).getImage();
		coneHeadedZombieImage = new ImageIcon(this.getClass().getResource("images/zombie_football.gif")).getImage();
//		normalZombieDyingImage = new ImageIcon(this.getClass().getResource("images/zombie_normal_dying.gif")).getImage();
//		coneHeadedZombieDyingImage = new ImageIcon(this.getClass().getResource("images/zombie_football_dying.gif")).getImage();
		
		laneZombies = new ArrayList<>();
		laneZombies.add(new ArrayList<>());
		laneZombies.add(new ArrayList<>());
		laneZombies.add(new ArrayList<>());
		laneZombies.add(new ArrayList<>());
		laneZombies.add(new ArrayList<>());
		
		lanePeas = new ArrayList<>();
		lanePeas.add(new ArrayList<>());
		lanePeas.add(new ArrayList<>());
		lanePeas.add(new ArrayList<>());
		lanePeas.add(new ArrayList<>());
		lanePeas.add(new ArrayList<>());
		
		collisions = new Collision[35];
		for (int i = 0; i < collisions.length; i++) {
			Collision coll = new Collision();
			coll.setLocation(290 + (i % 7) * 88, 40 + (i / 7) * 110);
			coll.setAction(new PlantActionListener((i % 7), (i / 7)));
			collisions[i] = coll;
			add(coll, new Integer(0));
		}
		
		activeSunflower = new ArrayList<>();
		
		redRawTimer = new Timer(25, (ActionEvent e) -> repaint());
		redRawTimer.start();
		
		progressionTimer = new Timer(60, (ActionEvent e) -> setFeature());
		progressionTimer.start();
		
		sunCoinProducer = new Timer(5000, (ActionEvent e) -> {
			Random rand = new Random();
			Sun sun = new Sun(this, rand.nextInt(800) + 100, 0, rand.nextInt(300) + 200);
			activeSunflower.add(sun);
			add(sun, new Integer(1));
		});
		sunCoinProducer.start();
		
		incomingZombieProducer();
	}
	
	private void incomingZombieProducer() {
		incomingZombieProducer = new Timer(7000, (ActionEvent e) -> {
			Random rand = new Random();
			StageData stage = new StageData();
			@SuppressWarnings("static-access")
			String[] Stage = stage.STAGE_CONTENT[Integer.parseInt(stage.STAGE_NUMBER) - 1];
			@SuppressWarnings("static-access")
			int[][] StageValue = stage.STAGE_VALUE[Integer.parseInt(stage.STAGE_NUMBER) - 1];
			int lane = rand.nextInt(5);
			int type = rand.nextInt(100);
			Zombie zombie = null;
			for (int i = 0; i < StageValue.length; i++) {
				if (type >= StageValue[i][0] && type <= StageValue[i][1]) {
					zombie = Zombie.getZombie(Stage[i], GamePanel.this, lane);
				}
			}
			laneZombies.get(lane).add(zombie);
			producedZombies++;
		});
		incomingZombieProducer.start();
	}
	
	public int getSunCoin() {
		return sunCoin;
	}
	
	public void setSunCoin(int sunCoin) {
		this.sunCoin = sunCoin;
		sunCoinBoard.setText(String.valueOf(sunCoin));
	}
	
	public void setScore(int score) {
		this.score += score;
		scoreBoard.setText(String.valueOf(this.score));
	}
	
	private void setFeature() {
		for (int i = 0; i < 5; i++) {
			for (Zombie zombie : laneZombies.get(i)) {
				zombie.setFeature();
			}
			for (int j = 0; j < lanePeas.get(i).size(); j++) {
				Pea pea = lanePeas.get(i).get(j);
				pea.setFeature();
			}
		}
		for (int i = 0; i < activeSunflower.size(); i++) {
			activeSunflower.get(i).setFeature();
		}
	}
	
	@Override
	protected void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		graph.drawImage(backgroundImage, 0, 0, null);
		for (int i = 0; i < 35; i++) {
			Collision coll = collisions[i];
			if(coll.plant != null) {
				Plant p = coll.plant;
				if(p instanceof PeaShooter) {
					graph.drawImage(peaShooterImage, 316 + (i % 7) * 85, 40 + (i / 7) * 110, null);
				}
				if(p instanceof FreezerPeaShooter) {
					graph.drawImage(freezerPeaShooterImage, 316 + (i % 7) * 85, 40 + (i / 7) * 110, null);
				}
				if(p instanceof Sunflower) {
					graph.drawImage(sunFlowerImage, 316 + (i % 7) * 85, 40 + (i / 7) * 110, null);
				}
			}
		}
		for(int i = 0; i < 5; i++) {
			try {
				for(Zombie zombie : laneZombies.get(i)) {
					if(zombie instanceof NormalZombie) {
						graph.drawImage(normalZombieImage,zombie.getPosX(), (i * 110), null);
					} else if(zombie instanceof ConeHeadedZombie) {
						graph.drawImage(coneHeadedZombieImage, zombie.getPosX(), (i * 110), null);
					}
					Random rand = new Random();
					int producerTime = rand.nextInt(2000);
					producerTime += (StageData.STAGE_NUMBER.equals("1")) ? 2000 : 500;
					wait(producerTime);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			for(int j = 0; j < lanePeas.get(i).size(); j++) {
				Pea pea = lanePeas.get(i).get(j);
				if(pea instanceof FreezerPea) {
					graph.drawImage(freezerPeaImage, pea.getPosX(), 70 + (i * 110), null);
				} else {
					graph.drawImage(peaImage,pea.getPosX(), 55 + (i * 110), null);
				}
			}
		}
		if (StageData.STAGE_NUMBER.equals("1") && producedZombies == 15) {
			LoopStopper();
		}
		if (isGameOver) {
			graph.drawImage(gameOverImage, 0, 0, null);
			GameWindow.gameWindow.ClearLayeredPane();
			LoopStopper();
			scoreBoard.setLocation(565, 520);
			scoreBoard.setSize(100, 60);
			Font font = new Font("Monospace", Font.BOLD, 50);
			scoreBoard.setFont(font);
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < getLaneZombies().get(i).size(); j++) {
					getLaneZombies().get(i).remove(j);
				}
			}
			ResetScore();
		}
	}
	
	private void ResetScore() {
		score = 0;
		progress = 0;
	}
	
	protected void LoopStopper() {
		for (int i = 0; i < activeSunflower.size(); i++) {
			activeSunflower.get(i).RemoveSun();
		}
		sunCoinProducer.stop();
		incomingZombieProducer.stop();
	}
	
	private class PlantActionListener implements ActionListener {
		int x,y;
		
		public PlantActionListener(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(activeCardPlanting == GameWindow.PlantType.Sunflower) {
				if(getSunCoin() >= 50) {
					collisions[x + y * 7].setPlant(new Sunflower(GamePanel.this, x,y));
					setSunCoin(getSunCoin() - 50);
				}
			}
			
			if(activeCardPlanting == GameWindow.PlantType.PeaShooter) {
				if(getSunCoin() >= 100) {
					collisions[x + y * 7].setPlant(new PeaShooter(GamePanel.this, x,y));
					setSunCoin(getSunCoin() - 100);
				}
			}
			
			if(activeCardPlanting == GameWindow.PlantType.FreezerPeaShooter) {
				if(getSunCoin() >= 125) {
					collisions[x + y * 7].setPlant(new FreezerPeaShooter(GamePanel.this, x,y));
					setSunCoin(getSunCoin() - 125);
				}
			}
			activeCardPlanting = GameWindow.PlantType.None;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public static void setProggres(int num) {
		progress = progress + num;
		System.out.println(progress);
		if(progress >= 150) {
			if(StageData.STAGE_NUMBER.equals("1")) {
				JOptionPane.showMessageDialog(null, "Stage Completed !!" + '\n' + "Starting Next Stage");
				GameWindow.gameWindow.dispose();
				StageData.Write("2");
				GameWindow.gameWindow = new GameWindow();
				progress = 0;
			} else if(progress >= 200) {
				JOptionPane.showMessageDialog(null, "Stage Completed !!" + '\n' + "More Stages Will Come Soon !!" + '\n' + "Resetting Data");
				StageData.Write("1");
				progress = 0;
				System.exit(0);
			}
		}
	}
	
	public GameWindow.PlantType getActiveCardPlantingh(){
		return activeCardPlanting;
	}
	
	public void setActiveCardPlanting(GameWindow.PlantType activeCardPlanting) {
		this.activeCardPlanting = activeCardPlanting;
	}
	
	public ArrayList<ArrayList<Zombie>> getLaneZombies() {
		return laneZombies;
	}
	
	public void setLaneZombies(ArrayList<ArrayList<Zombie>> laneZombies) {
		this.laneZombies = laneZombies;
	}
	
	public ArrayList<ArrayList<Pea>> getLanePea() {
		return lanePeas;
	}
	
	public void setLanePeas(ArrayList<ArrayList<Pea>> lanePeas) {
		this.lanePeas = lanePeas;
	}
	
	public ArrayList<Sun> getActiveSuns() {
		return activeSunflower;
	}
	
	public void setActiveSuns(ArrayList<Sun> activeSunflower) {
		this.activeSunflower = activeSunflower;
	}
	
	public Collision[] getCollisions() {
		return collisions;
	}
	
	public void setCollisions(Collision[] collisions) {
		this.collisions = collisions;
	}
}