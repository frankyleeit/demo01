package com.quang.game2048;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class GUI extends JFrame {
	private int w_Screen;
	private int h_Screen;
	public static final long serialVersionUID = 1L;
	public static final int WIDTH_Frame = 1000, HEIGHT_Frame = 700;
	
	
	private GamePanel gamePanel;
	public GUI(){
		super("2048");
		initGUI();
		gamePanel= new GamePanel();
		add(gamePanel);
		
	}
	
	public void getScreenSize(){
		w_Screen = Toolkit.getDefaultToolkit().getScreenSize().width;
		h_Screen = Toolkit.getDefaultToolkit().getScreenSize().height;
	}
	
	public void initGUI(){
		getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new CardLayout());
		setBounds((w_Screen - WIDTH_Frame)/2, 0, WIDTH_Frame, HEIGHT_Frame);
	}

	
}
