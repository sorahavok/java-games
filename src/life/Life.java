package life;

import java.awt.*;
import java.io.*;
import java.util.Map;

import javax.swing.*;

import util.menu.BasicGameFrame;
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
	private static final String IMAGE_FLIE_PATH = "res/life/tileImages/";

	private final Controller controller;

	public Life(final Controller controller) {
		this.controller = controller;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		controller.render(g);
	}

	public static void main(String[] args) throws IOException {
		BasicGameFrame frame = new BasicGameFrame("Life", FRAME_HEIGHT, FRAME_WIDTH, true);

//		Good rules for a HexBoard
//		Map<Integer, BlockState> rulesMap = ImmutableMap.of(
//				2, BlockState.STAY,
//				3, BlockState.LIVE,
//				5, BlockState.LIVE,
//				6, BlockState.LIVE
//				);

//		Good rules for a Triangle4Board
//		Map<Integer, BlockState> rulesMap = ImmutableMap.of(
//				0, BlockState.STAY,
//				2, BlockState.LIVE,
//				3, BlockState.LIVE
//				);
		
//		Good rules for a SquareBoard
		Map<Integer, BlockState> rulesMap = ImmutableMap.of(
				2, BlockState.STAY,
				3, BlockState.LIVE
				);
		
		RuleSet rules = new StandardRules(rulesMap);
		
		BoardGenerator gen = new RandomBoardGenerator(FRAME_HEIGHT/TILE_SIZE, FRAME_WIDTH/TILE_SIZE);
//		BoardGenerator gen = new StaticBoardGenerator();
//		BoardGenerator gen = new FileBoardGenerator("Pulsar");
		
		BoardHandler boardHandler = new BoardHandler(rules, gen.getBoard());
		
		Board board = new SquareBoard(boardHandler, TILE_SIZE);
//		Board board = new Triangle4Board(boardHandler, TILE_SIZE);
		
//		Image full = ImageIO.read(new File(IMAGE_FLIE_PATH + "FilledHex.png"));
//		Image empty = ImageIO.read(new File(IMAGE_FLIE_PATH + "EmptyHex.png"));
//		Board board = new HexBoard(boardHandler, TILE_SIZE, full, empty, frame);
		
//		Controller controller = new AlwaysPlay(f, board, FPS);
		Controller controller = new PlayPauseController(frame, board, FPS);

		Life life = new Life(controller);
		
		life.addMouseListener(controller);
		life.addMouseWheelListener(controller);
		
		frame.add(life);
		frame.start();
		controller.run();
	}
}
