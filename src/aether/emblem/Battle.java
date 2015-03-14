package aether.emblem;

public class Battle
{
    public Character Attacker, Defender;
    public String Outcome= new String("");
    public String REOutcome= new String("");
    //--------------------------------------------------------
    //this creates a Battle, takes Attacking and Defending Caracters 
    //--------------------------------------------------------
    public Battle(Character Atta, Character Defend)
    {
        Attacker = Atta;
        Defender = Defend;
        this.attack();        
    }
    public void attack()
    {
        String AttackName=Attacker.getName(),DefenderName=Defender.getName();
        if(Attacker.getTeam()==1)
            {
                  AttackName= "Blue "+ AttackName;
                  DefenderName = "Red "+ DefenderName;
            }else{
                 AttackName= "Red "+ AttackName;
                  DefenderName = "Blue "+ DefenderName;
            }
        
        Outcome+="\nBattle "+AttackName+" vs. "+DefenderName+"\n";
        int HitChance = Attacker.getWeapon().getHitChance()+Attacker.getHitPercent()*2;
        if(HitChance>=Math.random()*100)
        {
        //this tests if the attack damage will be greater than 0
        if(Attacker.getAttack()-Defender.getDefence()>0)
            {
                int damage = Attacker.getAttack()-Defender.getDefence();
                Defender.setHp( Defender.getHp()-damage);
                Outcome+=AttackName+" Deals "+damage+" to "+DefenderName+" reducing "+
                         DefenderName+"'s Hit Points to "+Defender.getHp();
            }
        else
            {
                Outcome+=AttackName+"'s Attack Deals No Damage";
            }
        }
        else{
                Outcome+=AttackName+"'s Attack Missed";
             }
        if(Defender.getHp()>0)
            {   int REHitChance = Defender.getWeapon().getHitChance()+Defender.getHitPercent()*2;
                if(REHitChance>=Math.random()*100)
                {
                    if(Defender.getAttack()-Attacker.getDefence()>0)
                    {
                        int damage = Defender.getAttack()-Attacker.getDefence();
                        Attacker.setHp( Attacker.getHp()-damage);
                        REOutcome+="In Response "+DefenderName+" Deals "+damage+" to "+AttackName+" reducing "+
                                   AttackName+"'s Hit Points to "+Attacker.getHp();
                        if(Attacker.getHp()<=0)
                            REOutcome+="\n"+AttackName+" Has Been Killed!";
                    }else{
                        REOutcome+=DefenderName+"'s Attack Deals No Damage";
                     }
                }else{
                    REOutcome+=DefenderName+"'s Attack Missed";
                    }
         
            }else{REOutcome += DefenderName+" Has Been Killed!";
                }
        System.out.print(this.toString());
            }
    
    public String toString()
    {
        return Outcome+"\n"+REOutcome;
    }
    public String toFeildString()
    {
        return Outcome+"\n"+REOutcome;
    }
}
