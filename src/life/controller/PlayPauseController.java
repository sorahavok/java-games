package life.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;

import life.board.Board;

public class PlayPauseController extends AbstractController {

	public PlayPauseController(final JFrame frame, final Board board, final int fps) {
		super(frame, board, true, fps);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		fps = Math.max(fps + e.getWheelRotation(), 0);
		System.out.println("FPS = " + fps);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if(e.getButton() == MouseEvent.BUTTON1) {
			this.running = !this.running;
		}
	}
}
