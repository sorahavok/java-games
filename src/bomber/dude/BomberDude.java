package bomber.dude;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class BomberDude extends BasicGame {

	private Player p1;
	private List<Bomb> bombs;
	private List<Block> blocks;

	static AppGameContainer app;

	public BomberDude() {
		super("Bomber Dude");
	}

	public static void main(String args[]) throws SlickException {
		System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "\\libs\\slick");
		app = new AppGameContainer(new BomberDude());
		app.setDisplayMode(800, 600, false);
		app.setTargetFrameRate(60);
		app.start();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		p1 = new Player(new Vector2f(100,100));
		bombs = new ArrayList<Bomb>();
		blocks = new ArrayList<Block>();
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		for (Bomb bmb : bombs)
			bmb.render();
		for (Block blk : blocks)
			blk.render();
		p1.render();
	}	

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		for (Bomb bmb : bombs)
			bmb.update();
		for (Block blk : blocks)
			blk.update();
		p1.update(container);

	}
}
