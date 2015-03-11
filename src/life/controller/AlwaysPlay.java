package life.controller;

import javax.swing.JFrame;

import life.board.Board;

public class AlwaysPlay extends AbstractController {

	public AlwaysPlay(final JFrame frame, final Board board, final int fps) {
		super(frame, board, true, fps);
	}
}
