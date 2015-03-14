package aether.emblem;

import java.awt.*;
public class HeroChar extends Character
{
	Image CharacterImage=Generator.heroImg;
	Image CharacterFace;
	public String Type;
	HeroChar(String title,int hp,int attack,int def, int skill,Weapon wep,
			 int xLocation,int yLocation,int team)
	{
	    super(title,hp,attack,def,skill,wep,xLocation,yLocation,team);
	    Type=title;
	    /*int gender = (int)(Math.random()*2);
		if(gender==0)
			{CharacterFace = Generator.FaceHeroMale;}
		else
			{CharacterFace = Generator.FaceHeroFemale;}*/
	}
	public Image getImage()
	{
		if(this.Alive==true)
		{
		if(this.getTeam()==1)
		{CharacterFace = Generator.FaceHeroFemale;
		if(this.getMoved()==false)
			CharacterImage = Generator.heroImg;
		else
			{CharacterImage = Generator.grayheroImg;}
		}
		if(this.getTeam()==2)
		{CharacterFace = Generator.FaceHeroMale;
			if(this.getMoved()==false)
				CharacterImage = Generator.redheroImg;
			else
				{CharacterImage = Generator.grayredheroImg;}
		}
		if(Type=="Blank")
			{CharacterImage = Generator.remove;
			this.Die();}
		return CharacterImage;}
		else{return Generator.remove;}
	}
	public int getMovement()
	{
		return 3;
	}
	public int getRange()
	{
		return 2;
	}
	public Image getFaceImage()
	{
		if(this.Alive==true)
		{return CharacterFace;}
		else{return Generator.remove;}
	}
}

