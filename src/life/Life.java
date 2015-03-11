package life;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

import life.board.*;
import life.controller.*;
import life.generator.*;
import life.ruleset.*;

import com.google.common.collect.ImmutableMap;

public class Life extends JPanel {

	private static final int TILE_SIZE = 10;
	private static final int FPS = 10;

	private static final int FRAME_WIDTH = 1920;
	private static final int FRAME_HEIGHT = 1080;

	private final Controller controller;

	public Life(final Controller controller) {
		this.controller = controller;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		controller.render(g);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Life");

		Map<Integer, BlockState> rulesMap = ImmutableMap.of(
				2, BlockState.STAY,
				3, BlockState.LIVE
				);
		
		RuleSet rules = new StandardRules(rulesMap);
		
		BoardGenerator gen = new RandomBoardGenerator(FRAME_HEIGHT/TILE_SIZE, FRAME_WIDTH/TILE_SIZE);
		
		BoardHandler boardHandler = new BoardHandler(rules, gen.getBoard());
		
		Board board = new SquareBoard(boardHandler, TILE_SIZE);
		
		Controller controller = new AlwaysPlay(frame, board, FPS);

		Life life = new Life(controller);
		
		life.addMouseListener(controller);
		life.addMouseWheelListener(controller);
		
		frame.add(life);

		setUpFrame(frame);
		controller.run();
	}

	private static void setUpFrame(JFrame f) {
		f.setUndecorated(true);
		f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
