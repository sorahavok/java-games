
package aether.emblem;

/**
 * Write a description of class Weapons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon
{
    public int AttackBonus,HitChance;
    public String Type;
    public Weapon(String type, int Att, int skill)
    {
        AttackBonus = Att;
        Type = type;
        HitChance=skill;
    }
    public int getAttackBonus()
    {return AttackBonus;}
    public int getHitChance()
    {return HitChance;}
    public String getType()
    {return Type;}
    public String toString()
    {return "Weapon: "+Type+"  +"+AttackBonus;}
}