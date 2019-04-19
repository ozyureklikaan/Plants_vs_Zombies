package Plants_vs_Zombies;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	static GameWindow gameWindow;
	
	ArrayList<ActiveCard> activeCards;
	
	JLabel sun;
	JLabel score;
	JLabel score2;
	
	enum PlantType {
		None,
		Sunflower,
		PeaShooter,
		FreezerPeaShooter
	}
	
	public GameWindow(String stageNumber) {
		setSize(1006, 631);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null);
		
		sun = new JLabel("SUN");
		Font fontSun = new Font("Monospace", Font.BOLD, 20);
		sun.setFont(fontSun);
		sun.setForeground(Color.WHITE);
		sun.setLocation(30, 555);
		sun.setSize(60, 20);
		
		score = new JLabel("SCORE");
		Font fontScore = new Font("Monospace", Font.BOLD, 20);
		score.setFont(fontScore);
		score.setForeground(Color.WHITE);
		score.setLocation(940, 20);
		score.setSize(60, 20);
		
		score2 = new JLabel("SCORE");
		score2.setFont(fontScore);
		score2.setForeground(Color.WHITE);
		score2.setLocation(850, 20);
		score2.setSize(90, 20);
		
		GamePanel gamePanel = new GamePanel(sun, score, score2);
		gamePanel.setLocation(0, 0);
		getLayeredPane().add(gamePanel, new Integer(0));
		
		activeCards = new ArrayList<ActiveCard>();
		
		ActiveCard sunflower = new ActiveCard(new ImageIcon(this.getClass().getResource("images/active_sunflower.png")).getImage());
		sunflower.setLocation(2, 8);
		sunflower.setAction((ActionEvent e) -> {
			gamePanel.setActiveCardPlanting(PlantType.Sunflower);
		});
		getLayeredPane().add(sunflower, new Integer(3));
		activeCards.add(sunflower);
		
		ActiveCard peaShooter = new ActiveCard(new ImageIcon(this.getClass().getResource("images/active_peashooter.png")).getImage());
		peaShooter.setLocation(2, 110);
		peaShooter.setAction((ActionEvent e) -> {
			gamePanel.setActiveCardPlanting(PlantType.PeaShooter);
		});
		getLayeredPane().add(peaShooter, new Integer(3));
		activeCards.add(peaShooter);
		
		ActiveCard freezerPeaShooter = new ActiveCard(new ImageIcon(this.getClass().getResource("images/active_beetroot.png")).getImage());
		freezerPeaShooter.setLocation(2, 212);
		freezerPeaShooter.setAction((ActionEvent e) -> {
			gamePanel.setActiveCardPlanting(PlantType.FreezerPeaShooter);
		});
		getLayeredPane().add(freezerPeaShooter, new Integer(3));
		activeCards.add(freezerPeaShooter);
		
		getLayeredPane().add(sun, new Integer(2));
		getLayeredPane().add(score, new Integer(2));
		getLayeredPane().add(score2, new Integer(2));
		
		setResizable(false);
		setVisible(true);
		
		setStage(stageNumber);
	}
	
	public void ClearLayeredPane() {
		for (ActiveCard activeCard : activeCards) {
			getLayeredPane().remove(activeCard);
		}
		getLayeredPane().remove(sun);
		getLayeredPane().remove(score2);
	}
	
	public GameWindow(boolean bool) {
		FirstPage firstPage = new FirstPage();
		firstPage.setLocation(0, 0);
		setSize(1116, 602);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getLayeredPane().add(firstPage, new Integer(0));
		firstPage.repaint();
		setResizable(false);
		setVisible(true);
		
		setStage("1");
	}
	
	private void setStage(String stageNumber) {
		StageData.Write(stageNumber);
	}
	
	public static void Begin() {
		gameWindow.dispose();
		gameWindow = new GameWindow("1");
	}
	
	public static void main(String[] args) {
		gameWindow = new GameWindow(true);
	}
}
