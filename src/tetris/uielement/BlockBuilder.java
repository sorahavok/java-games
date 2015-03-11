package tetris.uielement;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.swing.JPanel;

import tetris.piece.Block;
import tetris.piece.Piece;

public class BlockBuilder extends JPanel implements MouseListener {

	private static final Logger log = Logger.getGlobal();

	private static final int SIZE = 300;

	private int dimensions, scale, colorIndex;
	private Piece grid, newPiece;
	private Map<Integer, Block> blocks;

	public BlockBuilder(int aDimensions, int color) {
		colorIndex = color;
		setDimensions(aDimensions);
		addMouseListener(this);
		blocks = new HashMap<>(dimensions * dimensions);
		newPiece = new Piece(new ArrayList<Block>(), colorIndex, dimensions);
	}

	private void setDimensions(int aDimensions) {
		dimensions = aDimensions;
		scale = SIZE / dimensions;
		grid = Piece.createGridPiece(dimensions);
		setPreferredSize(new Dimension(SIZE, SIZE));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		grid.draw(g, scale);
		newPiece.draw(g, scale);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX() / scale;
		int y = e.getY() / scale;
		int key = x * 10 + y; // easy to Mod back apart
		log.severe(x + "," + y + "[" + key + "]");

		if (blocks.get(key) == null) {
			Block block = new Block(x, y);
			blocks.put(key, block);
		} else {
			blocks.remove(key);
		}
		newPiece = new Piece(new ArrayList<>(blocks.values()), colorIndex, dimensions);

		repaint();
	}

	public void setColor(int selectedIndex) {
		colorIndex = selectedIndex;
		newPiece.setColor(colorIndex);
		repaint();
	}

	public void setGirdSize(int selectedIndex) {
		setDimensions(selectedIndex + 3); // index + smallest dimension 0 -> 3x3, 1 -> 4x4...
		grid = Piece.createGridPiece(dimensions);
		repaint();
	}

	public Piece getPiece() {
		List<Block> tempList = new ArrayList<>(blocks.size());
		for (Entry<Integer, Block> e : blocks.entrySet()) {
			int x = e.getKey() / 10;
			int y = e.getKey() % 10;
			if (x >= dimensions || y >= dimensions)
				continue;
			int center = (dimensions - 1) / 2;
			tempList.add(new Block(x - center, y - center));
		}
		return new Piece(tempList, colorIndex, dimensions);
	}
	
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
}
