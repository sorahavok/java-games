package tetris;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Logger;

import javax.swing.JFrame;

import tetris.factories.PanelFactory;
import tetris.panel.AbsPanel;

public class TetrisFrame extends JFrame {

	private static Logger log = Logger.getGlobal();
	
	private static TetrisFrame frame;
	
	private AbsPanel curentPanel;
	private Thread currentThread;
	
	private TetrisFrame(String title) {
		super(title);
		Configuration config = Configuration.getInstance();
		setCloseListener();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(config.getWidth(), config.getHeight()));
		pack();
	}
	
	public static TetrisFrame getInstance(){
		if(frame == null){
			frame = new TetrisFrame("Tetris");
		}		
		return frame;
	}
	
	public void setPanel(String panel){
		if(curentPanel != null){
			remove(curentPanel);
		}
		if(currentThread != null){
			currentThread.interrupt();
		}
		curentPanel = PanelFactory.getPanel(panel);
		currentThread = new Thread(curentPanel);
		add(curentPanel);
		currentThread.start();
		curentPanel.setFocusable(true);
		curentPanel.requestFocusInWindow();
		validate();
	}
	
	public static void exitApplication(){
		WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
	
	private void setCloseListener() {
		addWindowListener(new WindowListener() {
			@Override 
			public void windowClosing(WindowEvent arg0) {
				log.info("Window Closing");
//				Configuration.getInstance().writeXml();			
			}
			@Override public void windowOpened(WindowEvent arg0) {}
			@Override public void windowIconified(WindowEvent arg0) {}
			@Override public void windowDeiconified(WindowEvent arg0) {}
			@Override public void windowDeactivated(WindowEvent arg0) {}
			@Override public void windowClosed(WindowEvent arg0) {}			
			@Override public void windowActivated(WindowEvent arg0) {}
		});
	}

}
