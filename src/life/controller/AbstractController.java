package life.controller;

import java.awt.Graphics;

import javax.swing.JPanel;

import life.board.Board;
import util.menu.BasicGameFrame;

public abstract class AbstractController extends JPanel implements Controller {

	protected final BasicGameFrame frame;
	protected final Board board;
	
	protected int fps;
	protected boolean running;
	protected boolean keepAlive;
	
	public AbstractController(final BasicGameFrame frame, final Board board, final boolean running, final int fps) {
		this.frame = frame;
		this.board = board;
		this.running = running;
		this.fps = fps;
		this.keepAlive = true;
	}
	
	@Override
	public void run() {
		while(keepAlive) {
			try {
				while (running) {
					Thread.sleep(1000 / fps);
					render(frame.getGraphics());
					board.update();
				}
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Game Panel Interrupted");
			}
		}
		System.out.println("Exiting");
		System.exit(0);
	}

	@Override
	public void render(Graphics g) {
		board.render(g);
	}

	@Override
	public void exit() {
		running = false;
		keepAlive = false;
	}
}
