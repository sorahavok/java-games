package aether.emblem;

import java.awt.*;

public class AxeChar extends Character {
	Image CharacterImage = Generator.axeImg;

	AxeChar(String title, int hp, int attack, int def, int skill, Weapon wep, int xLocation,
			int yLocation, int team) {
		super(title, hp, attack, def, skill, wep, xLocation, yLocation, team);
		/*
		 * int gender = (int)(Math.random()*2); if(gender==0) {CharacterFace = Generator.FaceFighterMale;} else
		 * {CharacterFace = Generator.FaceFighterFemale;}
		 */
	}

	@Override
	public Image getImage() {
		if(!this.alive) {
			return Generator.remove;
		}

		if(this.getTeam() == 1) {
			if(this.getMoved() == false) {
				CharacterImage = Generator.axeImg;
			} else {
				CharacterImage = Generator.grayaxeImg;
			}
		}
		if(this.getTeam() == 2) {
			CharacterFace = Generator.FaceFighterFemale;
			if(this.getMoved() == false)
				CharacterImage = Generator.redaxeImg;
			else {
				CharacterImage = Generator.grayredaxeImg;
			}
		}
		return CharacterImage;
	}

	@Override
	public int getMovement() {
		return 2;
	}

	@Override
	public int getRange() {
		return 1;
	}
}
