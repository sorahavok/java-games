package tetris.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import tetris.TetrisFrame;

public abstract class AbsPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final String GAME_PANEL = "GamePanel";
	public static final String CONFIG_PANEL = "ConfigPanel";
	public static final String HOME_PANEL = "HomePanel";
	public static final String BLOCK_MAKER_PANEL = "BlockMakerPanel";

	@Override
	public void run() {}

	protected JButton returnHomeButton() {
		JButton back = new JButton("Back");
		back.addActionListener( event -> TetrisFrame.getInstance().setPanel(HOME_PANEL) );
		return back;
	}
}
