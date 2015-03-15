package util.menu;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JFrame;

public class BasicGameFrame {
	
	private final JFrame frame;
	
	/**
	 * Wrapper class for a {@link JFrame} which provides some basic defaults.
	 * @param title name for the window
	 * @param height of the window
	 * @param width of the window
	 * @param fullscreen will remove standard OS frame around the window
	 */
	public BasicGameFrame(String title, int height, int width, boolean fullscreen) {
		frame = new JFrame(title);
		frame.setUndecorated(fullscreen);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Graphics getGraphics() {
		return frame.getGraphics();
	}
	
	public void start() {
		frame.setVisible(true);
	}

	public void add(Component component) {
		frame.add(component);		
	}
}
