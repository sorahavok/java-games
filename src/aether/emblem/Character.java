package aether.emblem;

import java.awt.*;

public abstract class Character {
	public int hitPoints, attack, defence, hitPercent, xLocate;
	public int yLocate, ListPosition, Team;
	public Weapon weapon;
	public String name;
	protected Image characterFace;
	public boolean Moved, Selected, alive;

	// Name, Hit Points, Attack Power, Defense, Hit Chance,
	// Weapon, X-Location, Y-Location, Team (1-blue 2-red)
	public Character(String title, int hp, int attack, int def, int skill, Weapon wep,
			int xLocation, int yLocation, int team) {
		this.name = title;
		this.hitPoints = hp;
		this.attack = attack;
		this.defence = def;
		this.weapon = wep;
		this.hitPercent = skill;
		this.xLocate = xLocation;
		this.yLocate = yLocation;
		this.alive = true;
		if(team == 1) {
			ListPosition = Generator.countteam1++;
		}
		if(team == 2) {
			ListPosition = Generator.countteam2++;
		}
		Generator.Map.setCharacters();
		Moved = false;
		Selected = false;
		Team = team;
	}

	public int getHp() {
		return hitPoints;
	}

	public void setHp(int x) {
		hitPoints = x;
		if(x <= 0) {
			alive = false;
			Generator.Map.setNum(xLocate, yLocate, 1 + Team, 0);
		}

	}

	public String getName() {
		return name;
	}

	public int getAttack() {
		return attack + weapon.getAttackBonus();
	}

	public int getDefence() {
		int defPlus = 0;
		int currentTerrain = Generator.Map.getNum(xLocate, yLocate, 0);
		if(currentTerrain == 0)
			defPlus = 1;
		if(currentTerrain == 3)
			defPlus = 2;
		return defence + defPlus;
	}

	public int getHitChance() {
		return weapon.getHitChance();
	}
	
	public void setWeapon(Weapon wep) {
		weapon = wep;
	}

	public int getHitPercent() {
		int HitPlus = 0;
		int CurrentTerrain = Generator.Map.getNum(xLocate, yLocate, 0);
		if(CurrentTerrain == 0)
			HitPlus = 10;
		if(CurrentTerrain == 1)
			HitPlus = 20;
		return hitPercent + HitPlus;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nHp: " + hitPoints + "\nAttack: " + attack + "\nDefence: "
				+ defence + "\nSkill: " + hitPercent + "\n" + weapon + "\nList Position: "
				+ ListPosition + "\n";
	}

	public int getXLocation() {
		if(!alive) {
			return 0;
		}
		return xLocate;
	}

	public void setXLocation(int x) {
		xLocate = x;
	}

	public int getYLocation() {
		if(!alive) {
			return Generator.Map.water();
		}
		return yLocate;
	}

	public void setYLocation(int y) {
		yLocate = y;
	}

	public int getListPosition() {
		return ListPosition;
	}

	public boolean getSelected() {
		return Selected;
	}

	public boolean getMoved() {
		if(!alive) {
			return true;
		}
		return Moved;
	}

	public void setSelected(boolean x) {
		Selected = x;
	}

	public void setMoved(boolean x) {
		Moved = x;
	}

	public int getTeam() {
		return Team;
	}

	public int[] getStats() {
		int[] Stats = new int[4];
		Stats[0] = hitPoints;
		Stats[1] = this.getAttack();
		Stats[2] = defence;
		Stats[3] = hitPercent + this.weapon.getHitChance();
		return Stats;
	}

	public void Die() {
		alive = false;
	}

	public abstract int getRange();

	public abstract int getMovement();

	public abstract Image getImage();

	public Image getFaceImage() {
		return alive ? characterFace : Generator.remove;
	}
}
