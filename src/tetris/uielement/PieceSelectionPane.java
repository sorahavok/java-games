package tetris.uielement;

import java.awt.Dimension;
import java.awt.Graphics;

import tetris.piece.Piece;

public class PieceSelectionPane extends SelectionPane {
	
	private static final int BORDER_WIDTH = 4;
	public static final int PANE_SIZE = 100, PANE_WITH_BORDER = PANE_SIZE + BORDER_WIDTH * 2;
	private final int scale;
	private Piece piece, gridPiece;
	
	
	public PieceSelectionPane(Piece p) {
		super();
		scale = PANE_SIZE / p.getDimension();	
		setPreferredSize(new Dimension(PANE_WITH_BORDER, PANE_WITH_BORDER));
		piece = p;
		gridPiece = Piece.createGridPiece(piece.getDimension());
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(BORDER_WIDTH, BORDER_WIDTH, PANE_SIZE, PANE_SIZE);
		
		int offset = (gridPiece.getDimension() - 1) / 2;
		
		gridPiece.drawSameOffsets(g, 0, scale, BORDER_WIDTH);
		piece.drawSameOffsets(g, offset, scale, BORDER_WIDTH);
	}
	
	public Piece getPiece(){
		return piece;
	}
}
