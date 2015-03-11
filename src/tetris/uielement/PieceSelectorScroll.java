package tetris.uielement;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tetris.Configuration;
import tetris.factories.PieceFactory;
import tetris.piece.Piece;

public class PieceSelectorScroll extends JScrollPane implements ItemListener {

	private static final int NUM_BLOCKS_SHOWING = 6, SCROLLBAR_WIDTH = 10, SCROLLBAR_PADDING = 3;
	private final JPanel blocksPanel;

	public PieceSelectorScroll() {
		blocksPanel = new JPanel();
		blocksPanel.setLayout(new BoxLayout(blocksPanel, BoxLayout.Y_AXIS));
		List<Piece> allPieces = PieceFactory.getAllPieces();
		for (Piece sp : allPieces) {
			blocksPanel.add(new PieceSelectionPane(sp));
		}
		verticalScrollBar.setPreferredSize(new Dimension(SCROLLBAR_WIDTH, 0));
		verticalScrollBar.setUnitIncrement(PieceSelectionPane.PANE_WITH_BORDER / 3);
		resizeAndValidateScroll();
		super.setViewportView(blocksPanel);
	}

	public void updateScrollPane(Piece piecesToAdd) {
		Configuration.getInstance().registerConfigPiece(piecesToAdd);
		blocksPanel.add(new PieceSelectionPane(piecesToAdd));
		resizeAndValidateScroll();
	}

	public void resizeAndValidateScroll() {
		blocksPanel.setPreferredSize(new Dimension(PieceSelectionPane.PANE_WITH_BORDER,
				PieceSelectionPane.PANE_WITH_BORDER * PieceFactory.getAllPieces().size()));

		setPreferredSize(new Dimension(PieceSelectionPane.PANE_WITH_BORDER + SCROLLBAR_WIDTH + SCROLLBAR_PADDING,
				PieceSelectionPane.PANE_WITH_BORDER * NUM_BLOCKS_SHOWING + SCROLLBAR_PADDING));

		blocksPanel.validate();
		validate();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		PieceSelectionPane pane = (PieceSelectionPane) e.getItem();
		pane.getPiece().toString();
	}

}
