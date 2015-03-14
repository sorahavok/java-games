package aether.emblem;
import java.awt.*;
public abstract class Character
{
    public int HitPoints,Attack,Defence,HitPercent,xLocate;
    public int yLocate,ListPosition,Team;
    public Weapon Weapon;
    public String Name;
    public boolean Moved,Selected,Alive=true;
    //Name, Hit Points, Attack Power, Defense, Hit Chance,
    //Weapon, X-Location, Y-Location, Team (1-blue  2-red)
    public Character(String title,int hp,int attack,int def,int skill,
    		Weapon wep, int xLocation,int yLocation,int team)
    {
        Name      = title;
        HitPoints = hp;
        Attack    = attack;
        Defence   = def;
        Weapon    = wep;
        HitPercent= skill;
        xLocate   = xLocation;
        yLocate   = yLocation;
       if(team==1)
        	{ListPosition = Generator.countteam1++;}
        if(team==2)
        	{ListPosition = Generator.countteam2++;}
        Generator.Map.setCharacters();
        Moved = false;
        Selected = false;
        Team=team;
     }
    public int getHp()
    {return HitPoints;}
    public void setHp(int x)
    {
    	HitPoints=x;
    	if(x<=0)
    	{
    		Alive=false;
    		Generator.Map.setNum(xLocate, yLocate, 1+Team, 0);
    	}
    	
    }
    public String getName()
    {return Name;}
    public int getAttack()
    {return Attack+Weapon.getAttackBonus();}
    public int getDefence()
    {
    	int DefPlus=0;
    	int CurrentTerrain = Generator.Map.getNum(xLocate, yLocate, 0);
    	if(CurrentTerrain==0)
    		DefPlus=1;
    	if(CurrentTerrain==3)
    		DefPlus=2;
    	return Defence+DefPlus;
    }
    public Weapon getWeapon()
    {return Weapon;}
    public void setWeapon(Weapon wep)
    {Weapon=wep;}
    public int getHitPercent()
    {
    	int HitPlus=0;
    	int CurrentTerrain = Generator.Map.getNum(xLocate, yLocate, 0);
    	if(CurrentTerrain==0)
    		HitPlus=10;
    	if(CurrentTerrain==1)
    		HitPlus=20;
    	return HitPercent+HitPlus;}
    public String toString()
    {
    	return "Name: "+Name+"\nHp: "+HitPoints+"\nAttack: "+
    		Attack+"\nDefence: "+Defence+"\nSkill: "+
    		HitPercent+"\n"+Weapon+"\nList Position: "+
    		ListPosition+"\n";
    }
    public int getXLocation()
    {if(Alive==false)
		{return 0;}
    	return xLocate;}
    public void setXLocation(int x)
    {xLocate = x;}
    public int getYLocation()
    {if(Alive==false)
	{return Generator.Map.water();}
    	return yLocate;}
    public void setYLocation(int y)
    {yLocate = y;}
    public int getListPosition()
    {return ListPosition;}
    public boolean getSelected()
    {return Selected;}
    public boolean getMoved()
    {
    	if(Alive==false)
    		{return true;}
    	return Moved;}
    public void setSelected(boolean x)
    {Selected=x;}
    public void setMoved(boolean x)
    {Moved=x;}
    public int getTeam()
    {return Team;}
    public int[] getStats()
    {
    	int[] Stats = new int[4];
    	Stats[0]=HitPoints;
    	Stats[1]=this.getAttack();
    	Stats[2]=Defence;
    	Stats[3]=HitPercent+this.Weapon.getHitChance();
    	return Stats;
    }
    public void Die()
    {Alive=false;}
    
    public abstract int getRange();
    public abstract int getMovement();    
    public abstract Image getImage();
    public abstract Image getFaceImage();
}
