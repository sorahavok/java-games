package bomber.dude;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Player {

	private static final int SPEED = 2;
	private static final String resPath = "res/bomber-dude/";
	
	private final Vector2f pos;
	private final Image img;
	private final Animation upAnimation;
	private final Animation downAnimation;
	private final Animation rightAnimation;
	private final Animation leftAnimation;
	
	private static final int UP = 1;
	private static final int LEFT = 2;
	private static final int DOWN = 3;
	private static final int RIGHT = 4;
	
	private int dir;
	
	public Player(Vector2f postion) throws SlickException{
		pos = postion;
		img = new Image(resPath+"bomberman.png");
		upAnimation = new Animation(new SpriteSheet(new Image(resPath+"bman_up.png"), 22, 32), 200);
		downAnimation = new Animation(new SpriteSheet(new Image(resPath+"bman_down.png"), 22, 32), 200);
		rightAnimation = new Animation(new SpriteSheet(new Image(resPath+"bman_right.png"), 22, 32), 200);
		leftAnimation = new Animation(new SpriteSheet(new Image(resPath+"bman_left.png"), 22, 32), 200);
	}
	
	public void update(GameContainer container){
		dir = 0;		
		handleInput(container);
		if(dir==UP){pos.y -= SPEED;}
		if(dir==LEFT){pos.x -= SPEED;}
		if(dir==DOWN){pos.y += SPEED;}
		if(dir==RIGHT){pos.x += SPEED;}
	}

	public void render(){
		if(dir==UP){ upAnimation.draw(pos.x, pos.y); }
		else if(dir==LEFT){ leftAnimation.draw(pos.x, pos.y); }
		else if(dir==DOWN){ downAnimation.draw(pos.x, pos.y); }
		else if(dir==RIGHT){ rightAnimation.draw(pos.x, pos.y); }
		else { img.draw(pos.x, pos.y); }
	}

	public void handleInput(GameContainer container){
		Input in = container.getInput();
		if(in.isKeyDown(Input.KEY_W)){dir = UP;}
		else if(in.isKeyDown(Input.KEY_A)){dir = LEFT;}
		else if(in.isKeyDown(Input.KEY_S)){dir = DOWN;}
		else if(in.isKeyDown(Input.KEY_D)){dir = RIGHT;}
	}

	public void dropBomb() {}
}
