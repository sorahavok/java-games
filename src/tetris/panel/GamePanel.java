package tetris.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;

import tetris.Configuration;
import tetris.GameBoard;
import tetris.TetrisFrame;
import tetris.control.ControllerInput;
import tetris.control.Input;
import tetris.factories.PieceFactory;
import tetris.piece.Block;
import tetris.piece.Piece;

public class GamePanel extends AbsPanel implements KeyListener {

	// STATICS
	private static final Logger LOG = Logger.getGlobal();
	private static final long serialVersionUID = 1L;
	private static final int FPS = 60, NUM_BLOCKS_HIGH = 25, NUM_BLOCKS_WIDE = 10, AUTO_MOVE_DOWN_DEFAULT = 50;

	// Finals
	private final Dimension dimension;
	private final ControllerInput controller;
	private final List<Piece> pieces;
	private final GameBoard gameBoard;
	private final JLabel scoreLabel;
	private final int scale;

	// State
	private boolean running, gameOver;
	private int autoMoveDownCounter, maxAutoMoveDown, score;

	public GamePanel() {
		Configuration config = Configuration.getInstance();
		// Set Finals
		pieces = new ArrayList<>();
		gameBoard = new GameBoard(NUM_BLOCKS_WIDE, NUM_BLOCKS_HIGH);
		scoreLabel = new JLabel("Score: " + score);
		controller = config.getController();
		scale = config.getHeight() / (NUM_BLOCKS_HIGH + 1);
		LOG.config("Scale set to: " + scale);
		dimension = new Dimension(NUM_BLOCKS_WIDE * scale, NUM_BLOCKS_HIGH * scale);

		autoMoveDownCounter = 0;
		maxAutoMoveDown = AUTO_MOVE_DOWN_DEFAULT;
		score = 0;
		add(scoreLabel);
		addBackButton();
		setWindowProperties();
	}

	private void addBackButton() {
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TetrisFrame.getInstance().setPanel(HOME_PANEL);
			}
		});
		add(back);
	}

	private void setWindowProperties() {
		setPreferredSize(dimension);
		setBackground(Color.WHITE);

		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, dimension.width, dimension.height);

		gameBoard.paint(g, scale);

		for (Piece p : pieces) {
			p.draw(g, scale);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		LOG.finest("keyPressed: " + keyCode);

		Input input = controller.getInput(keyCode);

		if (input == Input.EXIT)
			TetrisFrame.exitApplication();

		if (input == Input.PAUSE) {
			running = !running;
			return;
		}
		maxAutoMoveDown = AUTO_MOVE_DOWN_DEFAULT;
		for (Piece p : pieces) {
			switch (input) {
			case LEFT:
			case RIGHT:
				attemptMove(p, input);
				break;
			case UP:
				maxAutoMoveDown = 0;
				break;
			case DOWN:
				moveDown();
				break;
			case ROTATE_CW:
				attemptRotate(p, Block.CLOCKWISE);
				break;
			case ROTATE_CCW:
				attemptRotate(p, Block.COUNTER_CLOCKWISE);
				break;
			default:
				break;
			}
		}
	}

	private void attemptRotate(Piece p, int rotationDirection) {
		List<Point> rotateArray = p.getRorateArray(rotationDirection);
		if (gameBoard.validateLocation(rotateArray))
			p.rotate(rotationDirection);
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	private boolean attemptMove(Piece p, Input dir) {
		boolean validMove = validMove(p, dir);
		if (validMove)
			p.move(dir);
		return validMove;
	}

	private boolean validMove(Piece p, Input dir) {
		List<Point> move = p.getMoveArray(dir);
		return gameBoard.validateLocation(move);
	}

	@Override
	public void run() {
		LOG.entering("GamePanel", "run()");
		createNewPiece();
		running = true;
		gameOver = false;
		try {
			while (!gameOver) {
				while (running) {
					update();
					render();
					Thread.sleep(1000 / FPS);
				}
				Thread.sleep(1000 / FPS);
			}
			LOG.info("GAME OVER!");

		}
		catch (InterruptedException e) {
			LOG.info("Game Panel Interrupted");
		}
		LOG.exiting("GamePanel", "run()");
	}

	private void render() {
		repaint();
	}

	private void update() {
		autoMoveDownCounter++;
		if (autoMoveDownCounter >= maxAutoMoveDown) {
			moveDown();
			autoMoveDownCounter = 0;
		}
	}

	private void moveDown() {
		List<Piece> remove = new ArrayList<>(0);

		for (Piece p : pieces) {
			boolean moveSuceeded = attemptMove(p, Input.DOWN);
			if (!moveSuceeded) {
				maxAutoMoveDown = AUTO_MOVE_DOWN_DEFAULT; // reset to default to counteract up move
				remove.add(p);
			}
		}
		if (remove.size() == 0)
			return;

		gameBoard.removeInactivePeices(remove);
		score += gameBoard.checkAndRemoveRows();
		scoreLabel.setText("Score: " + score);
		for (Piece p : remove) {
			pieces.remove(p);
			createNewPiece();
		}
	}

	private void createNewPiece() {
		Piece randomPiece = PieceFactory.getRandomPiece();
		randomPiece.setLoc(NUM_BLOCKS_WIDE / 2, -randomPiece.getDimension() / 3);
		if (!validMove(randomPiece, Input.HERE)) {
			gameOver = true;
			running = false;
		}
		pieces.add(randomPiece);
	}
}