package couk.Adamki11s.Regios.Inventory;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import couk.Adamki11s.Extras.Inventory.ExtrasInventory;

public class InventoryCacheManager {
	
	public static HashMap<Player, ItemStack[]> inventMain = new HashMap<Player, ItemStack[]>();
	public static HashMap<Player, ItemStack[]> inventArmour = new HashMap<Player, ItemStack[]>();
	
	static ExtrasInventory exi = new ExtrasInventory();
	
	public static boolean doesCacheContain(Player p){
		return inventMain.containsKey(p) && inventArmour.containsKey(p);
	}
	
	public static void cacheInventory(Player p){
		inventMain.put(p, p.getInventory().getContents());
		inventArmour.put(p, p.getInventory().getArmorContents());
	}
	
	@SuppressWarnings("deprecation")
	public static void restoreInventory(Player p){
		exi.wipeInventory(p);
		p.getInventory().setContents(inventMain.get(p));
		p.getInventory().setArmorContents(inventArmour.get(p));
		inventMain.remove(p);
		inventArmour.remove(p);
		p.updateInventory();
	}
	
	public static void wipeInventory(Player p){
		exi.wipeInventory(p);
	}

}
