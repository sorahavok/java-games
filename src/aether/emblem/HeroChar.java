package aether.emblem;

import java.awt.Image;

public class HeroChar extends Character {
	private Image CharacterImage = Generator.heroImg;
	private String Type;

	public HeroChar(String title, int hp, int attack, int def, int skill, Weapon wep, int xLocation,
			int yLocation, int team) {
		super(title, hp, attack, def, skill, wep, xLocation, yLocation, team);
		Type = title;
		/*
		 * int gender = (int)(Math.random()*2); if(gender==0) {CharacterFace = Generator.FaceHeroMale;} else
		 * {CharacterFace = Generator.FaceHeroFemale;}
		 */
	}

	@Override
	public Image getImage() {
		if(!alive) {
			return Generator.remove;
		}

		if(this.getTeam() == 1) {
			characterFace = Generator.FaceHeroFemale;
			if(this.getMoved() == false)
				CharacterImage = Generator.heroImg;
			else {
				CharacterImage = Generator.grayheroImg;
			}
		}
		if(this.getTeam() == 2) {
			characterFace = Generator.FaceHeroMale;
			if(this.getMoved() == false)
				CharacterImage = Generator.redheroImg;
			else {
				CharacterImage = Generator.grayredheroImg;
			}
		}
		if(Type == "Blank") {
			CharacterImage = Generator.remove;
			this.Die();
		}
		return CharacterImage;
	}

	@Override
	public int getMovement() {
		return 3;
	}

	@Override
	public int getRange() {
		return 2;
	}
}
