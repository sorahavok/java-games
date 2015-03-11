package tetris.uielement;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public abstract class SelectionPane extends JPanel implements MouseListener, FocusListener {

	public SelectionPane() {
		addMouseListener(this);
		addFocusListener(this);
		setBackground(Color.white);
	}


	protected void toggleFocus() {
		updateFocus(!hasFocus());
	}

	protected void updateFocus(boolean bool) {
		setFocusable(bool);
		requestFocusInWindow();
	}

	@Override
	public void focusGained(FocusEvent e) {
		updateFocus(true);
		setBackground(new Color(170, 170, 255));
	}

	@Override
	public void focusLost(FocusEvent e) {
		updateFocus(false);
		setBackground(Color.white);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		toggleFocus();
	}

	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
}
