package tetris.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tetris.TetrisFrame;

public class HomePanel extends AbsPanel {
	private static final long serialVersionUID = 1L;

	public HomePanel() {
		JButton play = new JButton("Play");
		JButton config = new JButton("Config");
		JButton blockMaker = new JButton("Edit Blocks");
		JButton exit = new JButton("Exit");

		add(play);
		add(config);
		add(blockMaker);
		add(exit);

		
		play.addActionListener(new SwapPanels(GAME_PANEL));
		config.addActionListener(new SwapPanels(CONFIG_PANEL));
		blockMaker.addActionListener(new SwapPanels(BLOCK_MAKER_PANEL));
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TetrisFrame.exitApplication();
			}
		});
	}
	
	private class SwapPanels implements ActionListener{
		
		private final String destination;
		
		public SwapPanels(String aDestination) {
			destination = aDestination;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			TetrisFrame.getInstance().setPanel(destination);			
		}
	}
}
