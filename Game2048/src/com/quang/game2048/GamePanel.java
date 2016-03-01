package com.quang.game2048;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private final int widthAreaGame = 280;
	private final static String Font_name = "Arial";
	private Image background;
	private TileManager tileManager = new TileManager();
	private boolean ok = false;
	
	public GamePanel() {
		setBackground(Color.CYAN);
		setLayout(null);
		initImage();
		tileManager.initArr();
		moveTile();

	}

	public void initImage() {
		Random rd = new Random();
		background = new ImageIcon(getClass().getResource(
				"/IMG/bg_0" + (rd.nextInt(6) + 1) + ".jpg")).getImage();

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		antialiasing(g2d);

		g2d.drawImage(background, 0, 0, null);
		drawRects(g2d);
		drawStrings(g2d);
		tileManager.drawAllTile(g2d);

		if (tileManager.isGameOver() == true)
			drawGameOver(g2d);
		
		if(ok==true){
			tileManager.drawAllTile(g2d);
		}

	}

	public void antialiasing(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);
	}

	public void drawRects(Graphics2D g) {
		g.setColor(new Color(233, 150, 122));
		g.fillRoundRect(230 + widthAreaGame, 50, 100, 70, 15, 15);
		g.fillRoundRect(340 + widthAreaGame, 50, 100, 70, 15, 15);
	}

	public void drawStrings(Graphics2D g) {
		Font f = new Font(Font_name, Font.BOLD, 80);
		g.setFont(f);
		g.setColor(new Color(233, 150, 122));
		g.drawString("2048", widthAreaGame, 100);

		g.setColor(Color.white);
		g.setFont(new Font(Font_name, Font.PLAIN, 18));

		g.drawString("Score", 255 + widthAreaGame, 70);
		g.drawString("" + displayScore(), 260 + 280, 100);
		g.drawString("High Score", 345 + widthAreaGame, 70);

	}
	
	
	public void drawGameOver(Graphics2D g) {
		g.setColor(new Color(255, 255, 255, 30));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(78, 139, 202));
		g.setFont(new Font("Arial", Font.BOLD, 48));
		g.drawString("Game over!", 375, 370);
		g.drawString("You lose!", 390, 420);
		g.setFont(new Font("Arial", Font.BOLD, 35));
		g.drawString("Press ESC to play again", 305, 500);
	}

	public void moveTile() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				switch (e.getKeyCode()) {

				case KeyEvent.VK_RIGHT:
					tileManager.moveRight();
					tileManager.addTile();
					break;

				case KeyEvent.VK_LEFT:
					tileManager.moveLeft();
					tileManager.addTile();
					break;

				case KeyEvent.VK_UP:
					tileManager.moveUp();
					tileManager.addTile();
					break;

				case KeyEvent.VK_DOWN:
					tileManager.moveDown();
					tileManager.addTile();
					break;
				}
				
				
				repaint();

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					ok = true;
					tileManager.initArr();
				}

			}
		});
		setFocusable(true);
	}
	
	public int displayScore(){
		int score =0;
		return score =+ (tileManager.getScoreDown()+tileManager.getScoreLeft()+tileManager.getScoreRight()+tileManager.getScoreUp());
	}
}
