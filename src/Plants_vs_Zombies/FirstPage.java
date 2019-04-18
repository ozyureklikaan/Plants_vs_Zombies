package Plants_vs_Zombies;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class FirstPage extends JPanel {
	private Image firstImage;
	private Image startImage;
	
	private javax.swing.JPanel firstJPanel;

	private JButton btn;
	
	public FirstPage() {
		initComponents();
		setSize(1116,602);
		firstImage = new ImageIcon(this.getClass().getResource("images/first_screen.jpg")).getImage();

		Icon gif = new ImageIcon(this.getClass().getResource("images/click_to_start.gif"));
		btn = new JButton(null, gif);
		btn.setBorder(null);
		btn.setSize(455, 140);
		btn.setLocation(500, 435);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event) {
                firstJPanelMouseClicked(event);
            }
        });
		Font fontBtn = new Font("Monospace", Font.BOLD, 20);
		btn.setFont(fontBtn);
		this.add(btn);
	}
	
	public void startClick() {
		initComponents();
		setSize(492,144);
		startImage = new ImageIcon(this.getClass().getResource("images/click_to_start.gif")).getImage();
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(firstImage, 0, 0, null);
        g.drawImage(startImage, 0, 0, null);
    }
	
    private void initComponents() {
		firstJPanel = new javax.swing.JPanel();
		setPreferredSize(new java.awt.Dimension(1116,602));
		firstJPanel.setOpaque(false);
		firstJPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event) {
                firstJPanelMouseClicked(event);
            }
        });
		
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(firstJPanel);
		firstJPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 387, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 116, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(523, Short.MAX_VALUE)
                                .addComponent(firstJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(firstJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(547, Short.MAX_VALUE))
        );
	}
	private void firstJPanelMouseClicked(java.awt.event.MouseEvent event) {
		GameWindow.Begin();
	}
}
