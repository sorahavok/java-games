package tetris.piece;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import tetris.control.Input;
import tetris.factories.ColorFactory;

/**
 * Left, Up, Right, Down
 * 
 * @author sfinkel
 */
public class Piece implements Serializable {
	private static Logger log = Logger.getGlobal();
	
	private transient int currentRotation, xLoc, yLoc;
	private transient Color pieceColor;
	
	private int dimension, color;
	private String name;
	private List<Block> blocks;

	public Piece(List<Block> aBlocks, int c, int dimension) {
		blocks = aBlocks;
		color = c;
		this.dimension = dimension;
		init();
	}

	public void init(){
		pieceColor = ColorFactory.getColor(color);
		for(Block b : blocks){
			b.init();
		}
		log.config(name + " Piece Created");
	}
	
	public void rotate(int direction) { // +1 CC, -1 CCW
		currentRotation = (currentRotation + direction + 4) % 4; // Java does Modulo keeping signs so force it positive
		for (Block b : blocks) {
			b.setRotate(currentRotation);
		}
	}

	public void draw(Graphics g, int scale) {
		draw(g, scale, xLoc, yLoc, 0, 0);
	}

	public void draw(Graphics g, int scale, int xOffset, int yOffset) {
		draw(g, scale, xOffset, yOffset, 0, 0);
	}

	public void drawSameOffsets(Graphics g, int offset, int scale, int pixelOffset) {
		draw(g, scale, offset, offset, pixelOffset, pixelOffset);
	}
	
	public void draw(Graphics g, int scale, int xOffset, int yOffset, int xPixelOffset, int yPixelOffset) {
		for (Block b : blocks) {
			b.draw(g, pieceColor, scale, xOffset, yOffset, xPixelOffset, yPixelOffset);
		}
	}

	public void setLoc(int x, int y) {
		xLoc = x;
		yLoc = y;
	}

	public List<Point> getMoveArray(Input direction) {
		List<Point> retPoints = new ArrayList<>(blocks.size());

		for (Block b : blocks) {
			Point p = new Point(b.getXOffset() + xLoc, b.getYOffset() + yLoc);
			switch (direction) {
			case UP:
				p.translate(0, -1);
				break;
			case DOWN:
				p.translate(0, 1);
				break;
			case LEFT:
				p.translate(-1, 0);
				break;
			case RIGHT:
				p.translate(1, 0);
				break;
			case HERE:
				break; // no need to translate just keep it here
			default:
				log.warning("Invalid Direction: " + direction);
				break;
			}
			retPoints.add(p);
		}

		return retPoints;
	}

	public void move(Input dir) {
		switch (dir) {
		case LEFT:
			xLoc--;
			break;
		case RIGHT:
			xLoc++;
			break;
		case UP:
			yLoc--;
			break;
		case DOWN:
			yLoc++;
			break;
		default:
			log.warning("Invalid Direction: " + dir);
			break;
		}
	}

	public void moveVert(int y) {
		yLoc += y;
	}

	public void moveHorz(int x) {
		xLoc += x;
	}

	public List<Point> getRorateArray(int direction) {
		List<Point> retPoints = new ArrayList<>(blocks.size());
		rotate(direction); // rotate to get new locations

		for (Block b : blocks) {
			retPoints.add(new Point(b.getXOffset() + xLoc, b.getYOffset() + yLoc));
		}

		rotate(-direction); // rotate back
		return retPoints;
	}

	public int getColor() {
		return color;
	}
	
	public void setColor(int c){
		color = c;
		pieceColor = ColorFactory.getColor(c);
	}

	public static Piece createGridPiece(int dim) {
		List<Block> aBlocks = new ArrayList<>(dim*dim);
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				aBlocks.add(new Block(i, j));
			}
		}
		return new Piece(aBlocks, 0, dim);
	}
	
	public int getDimension(){
		return dimension;
	}
}