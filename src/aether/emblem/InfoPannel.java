package aether.emblem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InfoPannel extends JPanel

{
	public Field field = Generator.Map;
	private static final long serialVersionUID = 1L;
	private int JWidth = Generator.appletWidth;
	private int JHeight = 100;
	boolean first = true;
	static Character Attacker, Defender;
	int imageX, imageY, TestX = 0, TestY = 0;
	int blueChar, redChar, demention = (3 * JHeight) / 4, height = JHeight / 8, sizeMod = 0;

	public InfoPannel() {
		setPreferredSize(new Dimension(JWidth, JHeight));
	}

	@Override
	public void paint(Graphics page) {
		page.drawImage(Generator.BackgroundImg, 0, 0, JWidth, JHeight, this);
		if(Attacker == null) {
			if(Generator.Map.getNum(FieldDriver.gridImageX, FieldDriver.gridImageY, 2) != 0
					|| Generator.Map.getNum(FieldDriver.gridImageX, FieldDriver.gridImageY, 3) != 0) {
				CharInfo(page);
			}
		}

		if(Attacker != null) {
			CharInfo(page, Attacker);
			int DefenderLoc = Generator.Map.getNum(FieldDriver.gridImageX, FieldDriver.gridImageY, 2);
			if(DefenderLoc != 0) {
				Defender = Generator.ListBlue.get(DefenderLoc - 1);
			}
			DefenderLoc = Generator.Map.getNum(FieldDriver.gridImageX, FieldDriver.gridImageY, 3);
			if(DefenderLoc != 0) {
				Defender = Generator.ListRed.get(DefenderLoc - 1);
			}

		}
		if(Defender != null && Defender != Attacker) {
			if(Generator.Map.getNum(FieldDriver.gridImageX, FieldDriver.gridImageY, 2) != 0
					|| Generator.Map.getNum(FieldDriver.gridImageX, FieldDriver.gridImageY, 3) != 0) {
				sizeMod = 2;
				CharInfo(page, Defender);
			}
		}
		paintEnv(page);
	}

	public void paintEnv(Graphics page) {
		String Env = "";
		int CurrentTerrain = field.getNum(FieldDriver.gridImageX, FieldDriver.gridImageY, 0);
		page.setColor(Color.black);
		page.draw3DRect(Space(5) - 1, JHeight / 8 - 1, demention + 1, demention + 1, true);
		if(CurrentTerrain == 0) {
			Env = "Grass";
			page.drawImage(Generator.grassImg, Space(5), height, demention, demention, this);
		}
		if(CurrentTerrain == 1) {
			Env = "Mud";
			page.drawImage(Generator.wallImg, Space(5), height, demention, demention, this);
		}
		if(CurrentTerrain == 2) {
			Env = "Water";
			page.drawImage(Generator.waterImg, Space(5), height, demention, demention, this);
		}
		if(CurrentTerrain == 3) {
			Env = "Bridge";
			page.drawImage(Generator.bridgeImg, Space(5), height, demention, demention, this);
		}
		EnvString(page, Env);
	}

	public void CharInfo(Graphics page) {
		blueChar = Generator.Map.getNum(FieldDriver.gridImageX, FieldDriver.gridImageY, 2);
		redChar = Generator.Map.getNum(FieldDriver.gridImageX, FieldDriver.gridImageY, 3);
		if(blueChar != 0) {
			CharString(page, Generator.ListBlue.get(blueChar - 1));
		}
		if(redChar != 0) {
			CharString(page, Generator.ListRed.get(redChar - 1));
		}
	}

	public void CharInfo(Graphics page, Character Attacker) {
		CharString(page, Attacker);
	}

	public void CharString(Graphics page, Character charStats) {
		if(charStats.alive == true) {
			if(charStats.getTeam() == 1)
				page.drawImage(Generator.BlueTextBg, Space(2 + sizeMod) - 1, 24, 63, 58, this);
			if(charStats.getTeam() == 2)
				page.drawImage(Generator.RedTextBg, Space(2 + sizeMod) - 1, 24, 63, 58, this);
			page.drawImage(charStats.getFaceImage(), Space(1 + sizeMod), height, demention,
					demention, this);
			int[] stats = charStats.getStats();
			int xLoc = 1;
			page.setColor(Color.black);
			page.drawString("Hit Pts:", Space(2 + sizeMod), (20 + xLoc * 15));
			xLoc++;
			page.drawString("Attack:", Space(2 + sizeMod), (20 + xLoc * 15));
			xLoc++;
			page.drawString("Armor:", Space(2 + sizeMod), (20 + xLoc * 15));
			xLoc++;
			page.drawString("Hit % :", Space(2 + sizeMod), (20 + xLoc * 15));
			for(int x = 0; x < stats.length; x++) {
				page.drawString("" + stats[x], Space(2 + sizeMod) + 40, (20 + (x + 1) * 15));
			}
		}
		sizeMod = 0;

	}

	public void EnvString(Graphics page, String Env) {
		int def = 0, avoid = 0;
		if(Env.equals("Grass")) {
			def = 1;
			avoid = 10;
		}
		/*
		 * if(Env.equals("Water")) {def=0;avoid=0;}
		 */
		if(Env.equals("Mud")) {
			def = 0;
			avoid = 20;
		}
		if(Env.equals("Bridge")) {
			def = 2;
			avoid = 0;
		}
		int xLoc = 1;
		page.drawImage(Generator.WhiteTextBg, Space(6) - 1, 27, 54, 48, this);
		page.drawString(Env, Space(6), (25 + xLoc * 15));
		xLoc++;
		page.drawString("Armor: " + def, Space(6), (25 + xLoc * 15));
		xLoc++;
		page.drawString("Avoid : " + avoid, Space(6), (25 + xLoc * 15));
	}

	public int Space(int spaces) {
		spaces--;
		return spaces * 75 + (spaces + 1) * 25;
	}
}
