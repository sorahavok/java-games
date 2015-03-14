package aether.emblem;

public class CharacterDriver
{
    public static void main(String[] args)
    {
        //Character[] CharacterList = new Character[0];
        //For Character(Name,Hp,Attack,Defence,HitPercent,Weapon,X-location,Y-location)
        //Display Stats
        //For addCharacter(Hp,Attack,Defence,HitPercent,X-location,Y-location)
        
    	Generator.ListBlue = Generator.addHeroChar("Hero",100,25,8,10,Generator.Sword,6,6,1);
        Generator.ListBlue = Generator.addAxeChar(100,37,55,12,7,7,1);
        Generator.ListBlue = Generator.addSwordChar(65,25,10,20,8,8,1);
        Generator.ListBlue = Generator.addLanceChar(80,20,20,15,9,9,1);
    	Generator.ListRed = Generator.addHeroChar("Hero",100,25,8,10,Generator.Sword,1,1,2);
        Generator.ListRed = Generator.addAxeChar(100,37,55,12,2,2,2);
        Generator.ListRed = Generator.addSwordChar(65,25,10,20,3,3,2);
        Generator.ListRed = Generator.addLanceChar(80,20,20,15,4,4,2);
        
        System.out.println("Blue List:"+Generator.ListBlue.length+
        		 			"\n\n"+Generator.toString(Generator.ListBlue));
        System.out.println("Red List:"+Generator.ListRed.length+
        					"\n\n"+Generator.toString(Generator.ListRed));
        
        //System.out.println("\n----------------------------------\n");
        //for(int x=0;x<CharacterList.length;x++)
        //System.out.println(CharacterList[x]+"\n");
        //Creation of battels between characters and Characters attack each other
        /*Battle battle1 = new Battle(CharacterList[1],CharacterList[0]);
        Battle battle2 = new Battle(CharacterList[2],CharacterList[6]);
        Battle battle3 = new Battle(CharacterList[3],CharacterList[4]);
        Battle battle4 = new Battle(CharacterList[4],CharacterList[2]);
        Battle battle5 = new Battle(CharacterList[5],CharacterList[5]);
        Battle battle6 = new Battle(CharacterList[6],CharacterList[1]);
        */
        //Print results
        //System.out.println(battle1+"\n"+battle2+"\n"+battle3);
        //System.out.println(battle4+"\n"+battle5+"\n"+battle6);
        System.out.println(Generator.Map);
    }
}