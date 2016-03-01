package com.quang.game2048;

import java.awt.Graphics2D;
import java.util.Random;

public class TileManager {
	private static final int TILES_MARGIN = 10;
	private static final int TILE_SIZE = 100;
	private Tile arrTile[][];
	private Tile tileAdd;
	private boolean canMove = false;
	private int scoreUp, scoreDown, scoreLeft, scoreRight;
	
	private boolean ok = false;

	public TileManager() {
		this.scoreUp = 0;
		this.scoreDown =0;
		this.scoreLeft =0;
		this.scoreRight =0;
	}

	public void initArr() {
		arrTile = new Tile[4][4];
		int value = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				arrTile[i][j] = new Tile(offsetCoors(i), offsetCoors(j), value);
			}
		}
		addTile();
		addTile();

	}
	
	public void drawAllTile(Graphics2D g) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				arrTile[i][j].drawTile(g);
			}
		}
	}

	public void addTile() {

		Random random = new Random();
		int x, y;
		x = random.nextInt(4);
		y = random.nextInt(4);

		if (arrTile[x][y].getValue() == 0) {
			arrTile[x][y].setValue(2);
		} else
			addTile();

	}

	private int offsetCoors(int arg) {
		return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;

	}

	public void moveUp() {
		for (int x = 0; x < 4; x++) {
			for (int y = 1; y < 4; y++) {
				if (!arrTile[x][y].isEmpty() && arrTile[x][y - 1].isEmpty()) {
					int m = x, n = y - 1; // x1
					for (int k = y - 1; k >= 0; k--) {
						if (arrTile[x][k].isEmpty()) {
							m = x; // x2
							n = k;
						} else
							break;
					}

					if (n == 0) {
						arrTile[m][n].setValue(arrTile[x][y]);
					} else if (arrTile[m][n - 1].compareTile(arrTile[x][y])) {
						arrTile[m][n - 1].powValue();
						scoreUp += arrTile[m][n - 1].getValue();
					}

					else {
						arrTile[m][n].setValue(arrTile[x][y]);
					}
					arrTile[x][y].setValue(0);
				}

				else if (!arrTile[x][y].isEmpty()
						&& arrTile[x][y].compareTile(arrTile[x][y - 1])) {
					arrTile[x][y].setValue(0);
					arrTile[x][y - 1].powValue();
					 scoreUp =+ arrTile[x][y - 1].getValue();
				}
			}
		}
	}

	public void moveLeft() {
		for (int x = 1; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (!arrTile[x][y].isEmpty() && arrTile[x - 1][y].isEmpty()) {
					int m = x - 1, n = y;
					for (int k = x - 1; k >= 0; k--) {
						if (arrTile[k][y].isEmpty()) {
							n = y;
							m = k;
						} else
							break;
					}

					if (m == 0) {
						arrTile[m][n].setValue(arrTile[x][y]);
					} else if (arrTile[m - 1][n].compareTile(arrTile[x][y])) {
						arrTile[m - 1][n].powValue();
						 scoreLeft += arrTile[m-1][n].getValue();
					}

					else {
						arrTile[m][n].setValue(arrTile[x][y]);
					}
					arrTile[x][y].setValue(0);
				}

				else if (!arrTile[x][y].isEmpty()
						&& arrTile[x][y].compareTile(arrTile[x - 1][y])) {
					arrTile[x][y].setValue(0);
					arrTile[x - 1][y].powValue();
					scoreLeft =+ arrTile[x - 1][y].getValue();
				}
			}
		}
	}

	public void moveRight() {
		for (int x = 2; x >= 0; x--) {
			for (int y = 0; y < 4; y++) {
				if (!arrTile[x][y].isEmpty() && arrTile[x + 1][y].isEmpty()) {
					int m = x + 1, n = y; // x1
					for (int k = x + 1; k <= 3; k++) {
						if (arrTile[k][y].isEmpty()) {
							n = y; 
							m = k;
						} else
							break;
					}
					if (m == 3) {
						arrTile[m][n].setValue(arrTile[x][y]);
					} else if (arrTile[m + 1][n].compareTile(arrTile[x][y])) {
						arrTile[m + 1][n].powValue();
						scoreRight =+ arrTile[m + 1][n].getValue();
					}

					else {
						arrTile[m][n].setValue(arrTile[x][y]);
					}
					arrTile[x][y].setValue(0);
				}

				else if (!arrTile[x][y].isEmpty()
						&& arrTile[x][y].compareTile(arrTile[x + 1][y])) {
					arrTile[x][y].setValue(0);
					arrTile[x + 1][y].powValue();
					scoreRight =+ arrTile[x + 1][y].getValue();
				}
			}
		}
	}

	public void moveDown() {
		for (int x = 0; x < 4; x++) {
			for (int y = 2; y >= 0; y--) {
				if (!arrTile[x][y].isEmpty() && arrTile[x][y + 1].isEmpty()) {
					int m = x, n = y + 1; 
					for (int k = y + 1; k <= 3; k++) {
						if (arrTile[x][k].isEmpty()) {
							m = x; 
							n = k;
						} else
							break;
					}

					if (n == 3) {
						arrTile[m][n].setValue(arrTile[x][y]);
					} else if (arrTile[m][n + 1].compareTile(arrTile[x][y])) {
						arrTile[m][n + 1].powValue();

						scoreDown =+ arrTile[m][n + 1].getValue();
					}

					else {
						arrTile[m][n].setValue(arrTile[x][y]);
					}
					arrTile[x][y].setValue(0);
				}

				else if (!arrTile[x][y].isEmpty()
						&& arrTile[x][y].compareTile(arrTile[x][y + 1])) {
					arrTile[x][y].setValue(0);
					arrTile[x][y + 1].powValue();
					scoreDown =+ arrTile[x][y + 1].getValue();
				}

			}
		}
	}

	public boolean isMovePossible() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 3; y++) {
				int yy = y + 1;
				if (arrTile[x][y].getValue() == arrTile[x][yy].getValue()) {
					return true;
				}
			}
		}
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 3; x++) {
				int xx = x + 1;
				if (arrTile[x][y].getValue() == arrTile[xx][y].getValue()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int getScoreUp() {
		return scoreUp;
	}

	public int getScoreDown() {
		return scoreDown;
	}

	public int getScoreLeft() {
		return scoreLeft;
	}

	public int getScoreRight() {
		return scoreRight;
	}

	public boolean isFull() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (arrTile[i][j].isEmpty())
					return false;
			}
		}
		return true;
	}

	public boolean isGameOver() {
		return isFull() && !isMovePossible();
	}

}