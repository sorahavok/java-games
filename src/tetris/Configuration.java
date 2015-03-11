package tetris;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import tetris.control.ControllerInput;
import tetris.factories.ColorFactory;
import tetris.factories.PieceFactory;
import tetris.piece.Block;
import tetris.piece.Piece;

import com.thoughtworks.xstream.XStream;

public class Configuration {

	private static final String rootLoc = "resources/";
	private static final String CONFIG_FILE = rootLoc + "config.xml";
	public static final String BLOCK_FILE = rootLoc + "imgs/squareImg.png";
	// private static final String USER_BLOCK = "User Block";
	// private static int userBlock = 0;

	private static XStream xstream;
	private static Configuration instance;

	private transient ControllerInput controller;

	private int height, width;
	private Map<String, Integer> controllerMap;
	private String[] colors;
	private List<Piece> pieces;

	// STUFF FOR FIRST TESTS
	private Configuration() {}

	public static void setInstance(Configuration i) {
		instance = i;
	}

	public static Configuration getInstance() {
		if (instance == null) {
			File config = new File(CONFIG_FILE);
			instance = (Configuration) getXmlWriter().fromXML(config);
			instance.setVars();
			// Logger.getGlobal().severe(getXmlWriter().toXML(instance));
		}
		return instance;
	}

	public void setVars() {
		registerXmlColors();
		registerXmlPieces();
		createXmlController();
	}

	private void createXmlController() {
		controller = new ControllerInput(controllerMap);
	}

	private void registerXmlPieces() {
		for (Piece ps : pieces)
			PieceFactory.registerPiece(ps);
	}

	private void registerXmlColors() {
		for (String s : colors) {
			ColorFactory.addColor(s);
		}
	}

	public void registerConfigPiece(Piece piece) {
		pieces.add(piece);
		PieceFactory.registerPiece(piece);
	}

	public ControllerInput getController() {
		return controller;
	}

	public void updateController(ControllerInput newController) {
		controller = newController;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public static XStream getXmlWriter() {
		if (xstream == null) {
			xstream = new XStream();
			xstream.alias("Block", Block.class);
			xstream.alias("Piece", Piece.class);
		}
		return xstream;
	}

	public void writeXml() {
		controllerMap = controller.getMap();

		Logger.getGlobal().fine(getXmlWriter().toXML(instance));
		try (OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(CONFIG_FILE)))) {
			getXmlWriter().toXML(instance, out);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
