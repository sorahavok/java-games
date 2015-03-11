package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.*;

import tetris.control.Input;
import tetris.factories.ColorFactory;
import tetris.piece.Piece;
import tetris.piece.Block;

public class GameBoard {

	private final List<Block[]> gameBoard;
	private final int height, width;

	private static Logger LOG = Logger.getGlobal();

	public GameBoard(int aWidth, int aHeight) {
		height = aHeight;
		width = aWidth;
		gameBoard = IntStream.range(0, height)
							 .mapToObj(h -> createBlockRow(h))
							 .collect(Collectors.toList());
	}

	private Block[] createBlockRow(int h) {
		Block[] blocks = new Block[width];
		for (int w = 0; w < width; w++) {
			blocks[w] = new Block(w, h);
		}
		return blocks;
	}

	public void setSquare(int x, int y, int value) {
		gameBoard.get(y)[x].setColor(value);
	}

	public void paint(Graphics g, int scale) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color c = ColorFactory.getColor(getValue(x, y));
				Block.draw(g, c, scale, x, y);
			}
		}
	}

	private Block getBlock(int x, int y) {
		return gameBoard.get(y)[x];
	}

	public int checkAndRemoveRows() {
		List<Integer> rowsToRemove = new ArrayList<>(0);
		for (int h = 0; h < height; h++) {
			int tot = 1;
			for (int w = 0; w < width; w++) {
				tot *= getBlock(w, h).getColorValue();
			}
			if (tot > 0)
				rowsToRemove.add(h);
		}
		removeRows(rowsToRemove);

		return (int) (100 * (Math.pow(2, rowsToRemove.size()) - 1));

	}

	private void removeRows(List<Integer> rowsToRemove) {
		for (int i : rowsToRemove) {
			LOG.info("Row " + i + " Removed");
			gameBoard.remove(i);
			gameBoard.add(0, createBlockRow(0));
		}
	}

	public void removeInactivePeices(List<Piece> remove) {
		for (Piece p : remove) {
			for (Point point : p.getMoveArray(Input.HERE)) {
				setSquare(point.x, point.y, p.getColor());
			}
		}
	}

	public int getValue(int x, int y) {
		return getBlock(x, y).getColorValue();
	}

	public boolean validateLocation(List<Point> move) {
		for (Point p : move) {
			if (p.x < 0 || p.x >= width || p.y >= height)
				return false;
			if (p.y >= 0 && getValue(p.x, p.y) != 0) {
				return false;
			}
		}
		return true;
	}
}
