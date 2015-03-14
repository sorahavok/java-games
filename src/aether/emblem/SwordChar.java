package aether.emblem;

import java.awt.*;

public class SwordChar extends Character {
	Image CharacterImage;
	Image CharacterFace;

	SwordChar(String title, int hp, int attack, int def, int skill, Weapon wep, int xLocation,
			int yLocation, int team) {
		super(title, hp, attack, def, skill, wep, xLocation, yLocation, team);
		// int gender = (int)(Math.random()*2);
		// if(gender==0)
		// {CharacterFace = Generator.FaceSwordMale;}
		// else
		// {CharacterFace = Generator.FaceSwordFemale;}
	}

	@Override
	public Image getImage() {
		if(this.Alive == true) {
			if(this.getTeam() == 1) {
				CharacterFace = Generator.FaceSwordMale;
				if(this.getMoved() == false)
					CharacterImage = Generator.swordImg;
				else {
					CharacterImage = Generator.grayswordImg;
				}
			}
			if(this.getTeam() == 2) {
				CharacterFace = Generator.FaceSwordFemale;
				if(this.getMoved() == false)
					CharacterImage = Generator.redswordImg;
				else {
					CharacterImage = Generator.grayredswordImg;
				}
			}
			return CharacterImage;
		} else {
			return Generator.remove;
		}
	}

	@Override
	public int getMovement() {
		return 2;
	}

	@Override
	public int getRange() {
		return 1;
	}

	@Override
	public Image getFaceImage() {
		if(this.Alive == true) {
			return CharacterFace;
		} else {
			return Generator.remove;
		}
	}
}
