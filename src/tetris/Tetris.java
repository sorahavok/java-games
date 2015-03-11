package tetris;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import tetris.panel.AbsPanel;

public class Tetris {

	public static void main(String[] args){
		Level logLevel = Level.WARNING;
		
		Handler h = new ConsoleHandler();
		h.setLevel(logLevel);
		
		Logger globalLogger = Logger.getGlobal();
		globalLogger.setLevel(logLevel);
		
		globalLogger.addHandler(h);
		
		TetrisFrame window = TetrisFrame.getInstance();
		window.setPanel(AbsPanel.HOME_PANEL);
		window.setVisible(true);
	}
}
