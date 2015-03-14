package aether.emblem;

import java.awt.*;

public class SwordChar extends Character {
	private Image CharacterImage;

	public SwordChar(String title, int hp, int attack, int def, int skill, Weapon wep, int xLocation,
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
		if(!alive) {
			return Generator.remove;
		}
		
		if(this.getTeam() == 1) {
			characterFace = Generator.FaceSwordMale;
			if(this.getMoved() == false)
				CharacterImage = Generator.swordImg;
			else {
				CharacterImage = Generator.grayswordImg;
			}
		}
		if(this.getTeam() == 2) {
			characterFace = Generator.FaceSwordFemale;
			if(this.getMoved() == false)
				CharacterImage = Generator.redswordImg;
			else {
				CharacterImage = Generator.grayredswordImg;
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
