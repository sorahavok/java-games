package aether.emblem;

public class Battle {
	// --------------------------------------------------------
	// this creates a Battle, takes Attacking and Defending Characters
	// --------------------------------------------------------

	public static void fight(Character attacker, Character defender) {
		String attackText = "";
		String defenceText = "";
		String attackerName = attacker.getName();
		String defenderName = defender.getName();
		if(attacker.getTeam() == 1) {
			attackerName = "Blue " + attackerName;
			defenderName = "Red " + defenderName;
		} else {
			attackerName = "Red " + attackerName;
			defenderName = "Blue " + defenderName;
		}

		attackText += "\nBattle " + attackerName + " vs. " + defenderName + "\n";
		int hitChance = attacker.getHitChance() + attacker.getHitPercent() * 2;
		if(hitChance >= Math.random() * 100) {
			// this tests if the attack damage will be greater than 0
			if(attacker.getAttack() - defender.getDefence() > 0) {
				int damage = attacker.getAttack() - defender.getDefence();
				defender.setHp(defender.getHp() - damage);
				attackText += attackerName + " Deals " + damage + " to " + defenderName + " reducing "
						+ defenderName + "'s Hit Points to " + defender.getHp();
			} else {
				attackText += attackerName + "'s Attack Deals No Damage";
			}
		} else {
			attackText += attackerName + "'s Attack Missed";
		}
		if(defender.getHp() > 0) {
			int REHitChance = defender.getHitChance() + defender.getHitPercent() * 2;
			if(REHitChance >= Math.random() * 100) {
				if(defender.getAttack() - attacker.getDefence() > 0) {
					int damage = defender.getAttack() - attacker.getDefence();
					attacker.setHp(attacker.getHp() - damage);
					defenceText += "In Response " + defenderName + " Deals " + damage + " to "
							+ attackerName + " reducing " + attackerName + "'s Hit Points to "
							+ attacker.getHp();
					if(attacker.getHp() <= 0)
						defenceText += "\n" + attackerName + " Has Been Killed!";
				} else {
					defenceText += defenderName + "'s Attack Deals No Damage";
				}
			} else {
				defenceText += defenderName + "'s Attack Missed";
			}

		} else {
			defenceText += defenderName + " Has Been Killed!";
		}
		System.out.println(attackText);
		System.out.println(defenceText);
	}
}
