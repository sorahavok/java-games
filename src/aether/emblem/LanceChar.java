package aether.emblem;

import java.awt.*;

public class LanceChar extends Character {
	Image CharacterImage = Generator.lanceImg;

	LanceChar(String title, int hp, int attack, int def, int skill, Weapon wep, int xLocation,
			int yLocation, int team) {
		super(title, hp, attack, def, skill, wep, xLocation, yLocation, team);
		// int gender = (int)(Math.random()*2);
		// if(gender==0)
		// {CharacterFace = Generator.FacePikemanMale;}
		// else
		// {CharacterFace = Generator.FacePikemanFemale;}
	}

	@Override
	public Image getImage() {
		if(!alive) {
			return Generator.remove;
		}

		if(this.getTeam() == 1) {
			CharacterFace = Generator.FacePikemanFemale;
			if(this.getMoved() == false)
				CharacterImage = Generator.lanceImg;
			else {
				CharacterImage = Generator.graylanceImg;
			}
		}
		if(this.getTeam() == 2) {
			CharacterFace = Generator.FacePikemanMale;
			if(this.getMoved() == false)
				CharacterImage = Generator.redlanceImg;
			else {
				CharacterImage = Generator.grayredlanceImg;
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
