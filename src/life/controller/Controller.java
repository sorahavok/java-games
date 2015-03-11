package life.controller;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public interface Controller extends Runnable, MouseListener, MouseWheelListener {
	void render(Graphics g);
	void exit();
	
	@Override default void mouseClicked(MouseEvent e) {
		// Exit with right click
		if(e.getButton() == MouseEvent.BUTTON3) {
			exit();
		}		
	}
	
	@Override default void mouseEntered(MouseEvent e) {}
	@Override default void mouseExited(MouseEvent e) {}
	@Override default void mousePressed(MouseEvent e) {}
	@Override default void mouseReleased(MouseEvent e) {}
	@Override default void mouseWheelMoved(MouseWheelEvent e) {}
}
