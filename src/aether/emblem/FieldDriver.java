package aether.emblem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JPanel;

public class FieldDriver extends JPanel implements MouseMotionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private int APPLET_WIDTH = Generator.appletWidth, APPLET_HEIGHT = Generator.appletHeight;
	private Field field = Generator.Map;
	private int PicSize = APPLET_HEIGHT / field.getSize();
	private Image image;
	public Point currentPoint;
	public int imageX, imageY, MoveToX, MoveToY, YMouseMove, moveRange, turn = 0, battle = 1;
	public int objectOnField, XMouseMove;
	public static int gridImageY = 0, gridImageX = 0;
	private boolean selected = false, selected2 = false, attack = false;
	private List<Character> CharacterTurn = Generator.ListBlue;
	private List<Character> CharacterWait = Generator.ListRed;
	private Character toAttack, Defender;

	public FieldDriver(Image i) {
		image = i;
		addMouseListener(this);
		addMouseMotionListener(this);
		FieldDriver.class.getResource("Red_Square.png");
		// pointList = new ArrayList();

		setPreferredSize(new Dimension(APPLET_WIDTH, APPLET_HEIGHT));
		setBackground(Color.black);
		// (Title), Hit Points, Attack, Defense, Hit, Weapon, X-Loc, Y-Loc, Team

		Generator.addCharacter(new HeroChar("Hero", 100, 25, 8, 10, Generator.Sword, 1, 1, 2));
		Generator.addCharacter(new AxeChar("Fighter", 100, 37, 55, 12, Generator.Axe, 2, 2, 2));
		Generator.addCharacter(new SwordChar("Myrmidon", 65, 25, 10, 20, Generator.Sword, 3, 3, 2));
		Generator.addCharacter(new LanceChar("Pikeman", 80, 20, 20, 15, Generator.Lance, 4, 4, 2));
		Generator.addCharacter(new HeroChar("Hero", 100, 25, 8, 10, Generator.Sword, 6, 6, 1));
		Generator.addCharacter(new AxeChar("Fighter", 100, 37, 55, 12, Generator.Axe, 7, 7, 1));
		Generator.addCharacter(new SwordChar("Myrmidon", 65, 25, 10, 20, Generator.Sword, 8, 8, 1));
		Generator.addCharacter(new LanceChar("Pikeman", 80, 20, 20, 15, Generator.Lance, 9, 9, 1));
		
		Generator.addCharacter(new HeroChar("Blank", 100, 25, 8, 10, Generator.Sword, 0, field.water(), 1));
		Generator.addCharacter(new HeroChar("Blank", 100, 25, 8, 10, Generator.Sword, 0, field.water(), 2));
	}

	@Override
	public void paint(Graphics page) {
		drawField(page);
		if(objectOnField > 0)
			movementSquares(page, objectOnField - 1);
		page.drawImage(image, imageX, imageY, this);
		if(selected == false && selected2 == true) {
			moveChracter(CharacterTurn.get(objectOnField - 1));
		}
		if(toAttack != null && Defender == null && objectOnField == 0) {
			AttackSquares(page, toAttack);
		}
		if(attack) {
			attackChracter(toAttack, Defender);
			toAttack = null;
			Defender = null;
			attack = false;
		}
	}

	public void drawField(Graphics page) {
		for(int Hight = 0; Hight < field.getSize(); Hight++) {
			for(int Width = 0; Width < field.getSize(); Width++) { // draws the field
				if(field.getNum(Width, Hight, 0) == 0) {
					page.drawImage(Generator.grassImg, Width * PicSize, Hight * PicSize, PicSize,
							PicSize, this);
				} else if(field.getNum(Width, Hight, 0) == 1) {
					page.drawImage(Generator.wallImg, Width * PicSize, Hight * PicSize, PicSize,
							PicSize, this);
				} else if(field.getNum(Width, Hight, 0) == 2) {
					page.drawImage(Generator.waterImg, Width * PicSize, Hight * PicSize, PicSize,
							PicSize, this);
				} else if(field.getNum(Width, Hight, 0) == 3) {
					page.drawImage(Generator.bridgeImg, Width * PicSize, Hight * PicSize, PicSize,
							PicSize, this);
				}
			}
		}

		for(Character character : Generator.ListRed) {
			page.drawImage(character.getImage(), character.getXLocation() * PicSize,
					character.getYLocation() * PicSize, PicSize, PicSize, this);
		}
		for(Character character : Generator.ListBlue) {
			page.drawImage(character.getImage(), character.getXLocation() * PicSize,
					character.getYLocation() * PicSize, PicSize, PicSize, this);
		}
	}

	// Mouse Properties
	@Override
	public void mouseMoved(MouseEvent event) {
		int TestGridX = this.Grid(imageX), TestGridY = this.Grid(imageY);
		int imgCenterx = PicSize / 2;
		int imgCentery = PicSize / 2;
		imageX = event.getX() - imgCenterx;
		imageY = event.getY() - imgCentery;
		gridImageX = this.Grid(imageX + imgCenterx);
		gridImageY = this.Grid(imageY + imgCentery);
		if(gridImageX != TestGridX || gridImageY != TestGridY) {
			imageX = this.Ungrid(gridImageX);
			imageY = this.Ungrid(gridImageY);
			repaint();
			Generator.InfoScreen.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent event) {
		currentPoint = event.getPoint();
		int x = this.Grid(currentPoint.x);
		int y = this.Grid(currentPoint.y);
		if(field.getNum(x, y, (2 + battle)) - 1 >= 0 && toAttack != null) {
			int Range = toAttack.getRange();
			int AtkLocX = toAttack.getXLocation();
			int AtkLocY = toAttack.getYLocation();
			System.out.print("\nRange: " + Range + "\nAtkLocX: " + AtkLocX + "\nAtkLocY: "
					+ AtkLocY + "\nMouseX: " + x + "\nMouseY: " + y + "\nX-Y="
					+ (Math.abs(AtkLocX - x) + Math.abs(y - AtkLocY)));

			if(AtkLocX + x >= 0 && AtkLocX + x < field.getSize() && AtkLocY + y >= 0
					&& AtkLocY + y < field.getSize()) {
				if(Math.abs(AtkLocX - x) + Math.abs(y - AtkLocY) <= Range) {
					Defender = CharacterWait.get(field.getNum(x, y, (2 + battle)) - 1);
					attack = true;
				}
			}
		}
		boolean test = true;
		for(int AllMoved = 0; AllMoved < CharacterTurn.size() - 1; AllMoved++) {
			if(CharacterTurn.get(AllMoved).getMoved() == false) {
				test = false;
			}
		}
		if(test == true) {
			for(int AllMoved = 0; AllMoved < CharacterTurn.size(); AllMoved++) {
				CharacterTurn.get(AllMoved).setMoved(false);
			}
			if(CharacterTurn == Generator.getBlueList()) {
				CharacterTurn = Generator.ListRed;
				CharacterWait = Generator.ListBlue;
				turn = 1;
				battle = 0;
			} else {
				CharacterTurn = Generator.ListBlue;
				CharacterWait = Generator.ListRed;
				turn = 0;
				battle = 1;
			}
		}
		
		if(selected) {
			MoveToX = x;
			MoveToY = y;
			selected2 = true;
		} else {
			objectOnField = field.getNum(x, y, (2 + turn));
		}
		
		if(objectOnField > 0 && selected == false) {
			if(CharacterTurn.get(objectOnField - 1).getMoved() == false) {
				XMouseMove = x;
				YMouseMove = y;
				selected = true;
			}
		} else {
			selected = false;
		}

		imageX = this.Ungrid(gridImageX);
		imageY = this.Ungrid(gridImageY);
		repaint();
	}

	public void moveChracter(Character toMove) {
		moveRange = toMove.getMovement();
		int XMotion = (MoveToX - XMouseMove);
		int YMotion = (MoveToY - YMouseMove);
		int RelocateX = (XMotion) + toMove.getXLocation();
		int RelocateY = (YMotion) + toMove.getYLocation();

		field.setNum(toMove.getXLocation(), toMove.getYLocation(), (2 + turn), 0);
		if(RelocateX >= 0 && RelocateX < field.getSize() && RelocateY >= 0
				&& RelocateY < field.getSize())
			if(moveRange >= XMotion + YMotion
					&& -moveRange <= -XMotion + YMotion
					&& -moveRange <= XMotion - YMotion
					&& moveRange >= -XMotion - YMotion
					&& field.getNum(toMove.getXLocation() + XMotion, toMove.getYLocation()
							+ YMotion, 0) != 2
					&& field.getNum(toMove.getXLocation() + XMotion, toMove.getYLocation()
							+ YMotion, 2) == 0
					&& field.getNum(toMove.getXLocation() + XMotion, toMove.getYLocation()
							+ YMotion, 3) == 0) {
				toMove.setXLocation(RelocateX);
				toMove.setYLocation(RelocateY);
				toMove.setSelected(false);
				toMove.setMoved(true);
				objectOnField = 0;
				field.setNum(toMove.getXLocation(), toMove.getYLocation(), (2 + turn),
						toMove.getListPosition());
				toAttack = toMove;
				repaint();
			}
		field.setNum(toMove.getXLocation(), toMove.getYLocation(), (2 + turn),
				toMove.getListPosition());
		selected2 = false;

	}

	public void attackChracter(Character attacker, Character defender) {
		Battle.fight(attacker, defender);
	}

	public void AttackSquares(Graphics page, Character attacker) {
		int attackRange = attacker.getRange() + 1;
		for(int xAxis = -attacker.getRange(); xAxis < attackRange; xAxis++)
			for(int yAxis = -attacker.getRange(); yAxis < attackRange; yAxis++) {
				if(attacker.getXLocation() + xAxis >= 0
						&& attacker.getXLocation() + xAxis < field.getSize()
						&& attacker.getYLocation() + yAxis >= 0
						&& attacker.getYLocation() + yAxis < field.getSize())
					if(attackRange > xAxis + yAxis && -attackRange < -xAxis + yAxis
							&& -attackRange < xAxis - yAxis && attackRange > -xAxis - yAxis
							&& xAxis * yAxis + xAxis + yAxis != 0) {
						page.drawImage(Generator.Red_SquareImg, (attacker.getXLocation() + xAxis)
								* PicSize, (attacker.getYLocation() + yAxis) * PicSize, PicSize,
								PicSize, this);
					}
			}
		InfoPannel.Attacker = attacker;
	}

	public void movementSquares(Graphics page, int x) {
		// toAttack=null;
		// Defender=null;
		moveRange = CharacterTurn.get(x).getMovement();
		for(int xPossMove = -moveRange; xPossMove < moveRange + 1; xPossMove++)
			for(int yPossMove = -moveRange; yPossMove < moveRange + 1; yPossMove++) {
				if(CharacterTurn.get(x).getXLocation() + xPossMove >= 0
						&& CharacterTurn.get(x).getXLocation() + xPossMove < field.getSize()
						&& CharacterTurn.get(x).getYLocation() + yPossMove >= 0
						&& CharacterTurn.get(x).getYLocation() + yPossMove < field.getSize())
					if(moveRange >= xPossMove + yPossMove
							&& -moveRange <= -xPossMove + yPossMove
							&& -moveRange <= xPossMove - yPossMove
							&& moveRange >= -xPossMove - yPossMove
							&& field.getNum(CharacterTurn.get(x).getXLocation() + xPossMove,
									CharacterTurn.get(x).getYLocation() + yPossMove, 0) != 2
							&& field.getNum(CharacterTurn.get(x).getXLocation() + xPossMove,
									CharacterTurn.get(x).getYLocation() + yPossMove, 2) == 0
							&& field.getNum(CharacterTurn.get(x).getXLocation() + xPossMove,
									CharacterTurn.get(x).getYLocation() + yPossMove, 3) == 0
							&& CharacterTurn.get(x).getMoved() == false
							|| Math.abs(xPossMove) + Math.abs(yPossMove) == 0)
						page.drawImage(Generator.Green_SquareImg,
								(CharacterTurn.get(x).getXLocation() + xPossMove) * PicSize,
								(CharacterTurn.get(x).getYLocation() + yPossMove) * PicSize, PicSize,
								PicSize, this);
			}
	}

	// Gets Grid location
	public int Grid(int Point) {
		return Point / PicSize;
	}

	private int Ungrid(int Point) {
		return Point * PicSize;
	}

	public int getPicSize() {
		return PicSize;
	}

	public int getGridImageX() {
		return gridImageX;
	}

	public int getGridImageY() {
		return gridImageY;
	}

	// Provide empty definitions for unused event methods.
	@Override public void mouseDragged(MouseEvent event) {}
	@Override public void mouseClicked(MouseEvent event) {}
	@Override public void mouseReleased(MouseEvent event) {}
	@Override public void mouseEntered(MouseEvent event) {}
	@Override public void mouseExited(MouseEvent event) {}
}