package tetris.piece;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import tetris.Configuration;

public class Block implements Serializable {

	public final static int LEFT = 0, UP = 1, RIGHT = 2, DOWN = 3;
	public final static int CLOCKWISE = 1, COUNTER_CLOCKWISE = -1;
	private static Image img;

	private transient int[] xOrientations, yOrientations;

	private transient int rotation, colorValue;
	private int x,y;
	
	public Block(int offsetX, int offsetY) {
		x = offsetX;
		y = offsetY;
		init();
	}

	public void init() {
		xOrientations = new int[] { x, y, -x, -y };
		yOrientations = new int[] { y, -x, -y, x };
		img = getImage();
		colorValue = 0;
	}

	private Image getImage() {
		if (img != null)
			return img;
		// Read from a file
		try {
			File file = new File(Configuration.BLOCK_FILE);
			return ImageIO.read(file);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setRotate(int newRotation) {
		rotation = newRotation;
	}

	public void draw(Graphics g, Color color, int scale) {
		drawBlock(g, color, scale, x, y, 0, 0);
	}

	public static void draw(Graphics g, Color color, int scale, int xLoc, int yLoc) {
		drawBlock(g, color, scale, xLoc, yLoc, 0, 0);
	}

	public void draw(Graphics g, Color c, int scale, int xLoc, int yLoc, int xPixelOffset, int yPixelOffset) {
		drawBlock(g, c, scale, xLoc + getXOffset(), yLoc + getYOffset(), xPixelOffset, yPixelOffset);
	}

	public static void drawBlock(Graphics g, Color c, int scale, int xLoc, int yLoc, int xPixelOffset, int yPixelOffset) {
		int offsetX = xLoc * scale + xPixelOffset;
		int offsetY = yLoc * scale + yPixelOffset;

		g.setColor(Color.LIGHT_GRAY); // background
		g.fillRect(offsetX + 1, offsetY + 1, scale - 2, scale - 2);

		g.setColor(c);
		g.fillRect(offsetX + 1, offsetY + 1, scale - 2, scale - 2); // Inner

		g.drawImage(img, offsetX, offsetY, scale, scale, null);
	}

	public int getXOffset() {
		return xOrientations[rotation];
	}

	public int getYOffset() {
		return yOrientations[rotation];
	}

	public void setColor(int value) {
		colorValue = value;
	}

	public int getColorValue() {
		return colorValue;
	}
}