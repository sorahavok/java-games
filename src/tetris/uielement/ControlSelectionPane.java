package tetris.uielement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

public class ControlSelectionPane extends SelectionPane implements KeyListener {

	private JLabel controlLabel;
	private int keyCode; 
	private String controlName;
	
	public ControlSelectionPane(String aControlName, int value) {
		super();
		controlName = aControlName;
		keyCode = value;
		
		controlLabel = new JLabel(KeyEvent.getKeyText(value));
		
		add(new JLabel(controlName));
		add(controlLabel);
		addKeyListener(this);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyCode = e.getKeyCode();
		String keyChar = KeyEvent.getKeyText(keyCode);
		controlLabel.setText(keyChar);
		updateFocus(false);
	}
	
	public int getKeyCode(){
		return keyCode;
	}
	
	public String getControl(){
		return controlName;
	}

	@Override public void keyReleased(KeyEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
}
