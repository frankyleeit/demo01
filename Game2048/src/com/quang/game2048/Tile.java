package com.quang.game2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import javax.swing.*;

import java.awt.*;

public class Tile {
	private int value;
	private static final int TILES_MARGIN = 10;
	private static final int TILE_SIZE = 100;
	Color c1 = Color.white;
	Color c2 = Color.blue;
	private int x, y;
	
	private FontMetrics fMetrics;
	private BufferedImage img = new BufferedImage(1, 1,
			BufferedImage.TYPE_INT_RGB);

	public Tile(int x, int y, int value) {
		super();
		this.x = x;
		this.y = y;
		this.value = value;
		
	}

	
	public void drawTile(Graphics2D g) {
		if (value == 0) {
			g.setColor(new Color(0xcdc1b4));
			g.fillRoundRect(x + 280, y + 210, TILE_SIZE, TILE_SIZE, 25, 25);
		} else {
			g.setColor(getBackground(value));
			g.fillRoundRect(x + 280, y + 210, TILE_SIZE, TILE_SIZE, 25, 25);

			fMetrics = img.getGraphics().getFontMetrics();
			String s = String.valueOf(value);
			final int w = fMetrics.stringWidth(s);
			final int size = value < 100 ? 50 : value < 1000 ? 40 : 30;

			Font f = new Font("Arial", Font.BOLD, size);
			g.setFont(f);

			g.setColor(getForeground(value));
			g.drawString(s, x - w + 320, y + 275);
		}
		
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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

	public Color getForeground(int value) {
		return value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2);

	}

	public boolean compareTile(Tile tile) {
		if (value == tile.getValue())
			return true;
		return false;
	}

	public void powValue() {
		value = value * 2;
	}

	public void setValue(Tile tile) {
		value = tile.getValue();
	}

	public boolean isEmpty() {
		return value == 0;
	}

	public Color getBackground(int value) {
		if (value > 2048)
			return Color.black;
		else
			switch (value) {
			case 2:
				return new Color(0xede0c8);
			case 4:
				return new Color(0xede0c8);
			case 8:
				return new Color(0xf2b179);
			case 16:
				return new Color(0xf59563);
			case 32:
				return new Color(0xf67c5f);
			case 64:
				return new Color(0xf65e3b);
			case 128:
				return new Color(0xedcf72);
			case 256:
				return new Color(0xedcc61);
			case 512:
				return new Color(0xedc850);
			case 1024:
				return new Color(0xedc53f);
			case 2048:
				return new Color(0xedc22e);
			}

		return new Color(0xcdc1b4);
	}

}
