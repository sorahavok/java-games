package aether.emblem;

import java.awt.*;

public class LanceChar extends Character {
	private Image characterImage = Generator.lanceImg;

	public LanceChar(String title, int hp, int attack, int def, int skill, Weapon wep, int xLocation,
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
			characterFace = Generator.FacePikemanFemale;
			if(this.getMoved() == false)
				characterImage = Generator.lanceImg;
			else {
				characterImage = Generator.graylanceImg;
			}
		}
		if(this.getTeam() == 2) {
			characterFace = Generator.FacePikemanMale;
			if(this.getMoved() == false)
				characterImage = Generator.redlanceImg;
			else {
				characterImage = Generator.grayredlanceImg;
			}
		}
		return characterImage;
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
