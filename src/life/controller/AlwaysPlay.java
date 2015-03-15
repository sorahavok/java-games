package life.controller;

import util.menu.BasicGameFrame;
import life.board.Board;

public class AlwaysPlay extends AbstractController {

	public AlwaysPlay(final BasicGameFrame frame, final Board board, final int fps) {
		super(frame, board, true, fps);
	}
}
