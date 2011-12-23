package couk.Adamki11s.Regios.Scheduler;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

public class HealthRegeneration {

	public static HashMap<Player, Integer> regenerators = new HashMap<Player, Integer>();

	public static void addRegenerator(Player p, int rate) {
		regenerators.put(p, rate);
	}

	public static void removeRegenerator(Player p) {
		if (regenerators.containsKey(p)) {
			regenerators.remove(p);
		}
	}

	public static boolean isRegenerator(Player p) {
		if (regenerators.containsKey(p)) {
			return regenerators.containsKey(p);
		} else {
			return false;
		}
	}

	public static void loopRegenerators() {
		for (Entry<Player, Integer> entry : regenerators.entrySet()) {
			int damage = entry.getValue();
			if (damage < 0) {
				Player p = entry.getKey();
				if (((p.getHealth() - damage) > 0)) {
					p.damage(-damage);
				} else {
					p.damage(p.getHealth());
				}
			} else {
				Player p = entry.getKey();
				if (p.getHealth() < 20) {
					if (p.getHealth() + damage <= 20) {
						p.setHealth(p.getHealth() + damage);
					} else {
						p.setHealth(20);
					}
				}
			}
		}
	}

}
