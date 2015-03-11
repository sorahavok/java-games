package TetrisClone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameField extends Panel implements Runnable {
	private Image[] imgs = new Image[12];
	private Color[] colors = { genColor(Color.yellow), genColor(Color.red), genColor(Color.green),
			genColor(Color.cyan), genColor(Color.orange), genColor(Color.blue),
			genColor(Color.magenta), genColor(Color.gray) };
	private volatile boolean running = true;
	private Thread animator;
	private static int numTiles = 10;
	private Piece piece = null, next = new Piece(); // gives black between blocks
	private int Score = 0, FPS = 60, AutoMoveDown = 0, playX = 300, tileSize = playX / numTiles,
			offsetSize = 2;
	public static int[][] map = new int[2 * numTiles + 1][numTiles + 2];

	public GameField() {
		createField();
		animator = new Thread(this);
		animator.start();
	}

	private void createField() {
		setMap();
		setGraphics();
	}

	private void setMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (j == 0 || j == map[i].length - 1 || i == map.length - 1) {
					map[i][j] = 29;
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(imgs[11], 0, 0, this);
		drawMap(g);
		drawScore(g);
		if (next != null)
			drawNext(g);
	}

	private void drawNext(Graphics g) {
		Piece.Block[] temp = next.blocks;
		int nextWidth = next.getWidth(tileSize);
		int nextHeight = next.getHeight(tileSize);
		// print("width = "+width );print("height = "+height );
		int offsetX = 380 + 90 - (nextWidth / 2), offsetY = 50 + 90 - 15 - (nextHeight / 2);
		for (int i = 0; i < next.blocks.length; i++) {
			g.drawImage(imgs[10], temp[i].X * tileSize + offsetX, temp[i].Y * tileSize + offsetY,
					tileSize, tileSize, this);
			g.setColor(colors[next.type - 1]);
			g.fillRect(temp[i].X * tileSize + offsetX, temp[i].Y * tileSize + offsetY, tileSize
					- offsetSize, tileSize - offsetSize);
		}
	}

	private void drawScore(Graphics g) {
		for (int i = 0; i <= Math.log10(Score); i++)
			g.drawImage(imgs[(int) (Score / (Math.pow(10, i))) % 10], 535 - 25 * i, 295, 25, 50,
					this);
	}

	public void drawMap(Graphics g) {
		int offsetX = 13, offsetY = 153;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0 && !(j == 0 || j == map[0].length - 1 || i == map.length - 1))
				// bottom part keeps it from drawing the outside invisible wall.
				{
					g.drawImage(imgs[10], j * tileSize + offsetX, i * tileSize + offsetY, tileSize
							- offsetSize, tileSize - offsetSize, this);
					g.setColor(colors[map[i][j] % 10 - 1]);
					g.fillRect(j * tileSize + offsetX, i * tileSize + offsetY, tileSize
							- offsetSize, tileSize - offsetSize);
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// super.keyPressed(e);
		if (piece != null)
			piece.move(e.getKeyCode());
	}

	@Override
	public void run() {
		while (running) {
			update();
			render();
			try {
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private void render() {
		repaint();
	}

	private void update() {
		if (piece != null) {

			AutoMoveDown++;
			if (AutoMoveDown >= 20) {
				Score += 10;
				if (!piece.autoDown())
					checkForLines();
				AutoMoveDown = 0;
			}
		} else {
			piece = next;
			next = new Piece();
			piece.updateMap(piece.type);
		}
	}

	private void checkForLines() {
		piece = null;
		int numLines = 0;
		boolean full;
		// height
		for (int mapHeight = map.length - 2; mapHeight > 0; mapHeight--) {
			full = true;
			for (int mapWidth = 1; mapWidth < map[0].length - 1; mapWidth++)
				if (map[mapHeight][mapWidth] == 0) {
					full = false;
				}
					
			if (full) {
				numLines++;
				Score += 1000 * numLines;
				for (int k = mapHeight; k > 0; k--)
					map[k] = map[k - 1].clone();
				mapHeight++;
			}
		}
	}

	private void setGraphics() {
		for (int i = 0; i < 10; i++) {
			imgs[i] = kit.getImage("res/TetrisClone/" + i + ".png");
		}
		imgs[10] = kit.getImage("res/TetrisClone/block.png");
		imgs[11] = kit.getImage("res/TetrisClone/BG.png");
	}

	public Color genColor(Color c) {
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), 200);
	}

	public int rand(int x) {
		return (int) (Math.random() * x);
	}

	@Override
	public void MousePressed(MouseEvent e) {
		createField();
		repaint();
	}
}