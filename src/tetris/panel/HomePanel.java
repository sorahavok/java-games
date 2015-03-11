package tetris.panel;

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
		
		play.addActionListener(e -> TetrisFrame.getInstance().setPanel(GAME_PANEL));
		config.addActionListener(e -> TetrisFrame.getInstance().setPanel(CONFIG_PANEL));
		blockMaker.addActionListener(e -> TetrisFrame.getInstance().setPanel(BLOCK_MAKER_PANEL));
		exit.addActionListener(e -> TetrisFrame.exitApplication());
	}
}
