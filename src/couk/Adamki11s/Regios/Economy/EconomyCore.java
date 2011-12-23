package couk.Adamki11s.Regios.Economy;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.iCo6.iConomy;

import cosine.boseconomy.BOSEconomy;

public class EconomyCore {

	public static Logger log = Logger.getLogger("Minecraft.Regios");

	public static Economy economy = null;

	public static boolean economySupport = false;

	public static iConomy iconomy = null;
	
	public static BOSEconomy boseconomy = null;
	
	private static iConomyManager iconomyManager = new iConomyManager();
	
	private static BoseEconomyManager boseManager = new BoseEconomyManager();

	public static void boseConomySetup() {
		Plugin temp = Bukkit.getServer().getPluginManager().getPlugin("BOSEconomy");
		if (temp != null) {
			boseconomy = (BOSEconomy) temp;
			economySupport = true;
			log.info("[Regios] Hooked into BOSEconomy Successfully!");
			return;
		}	
		log.info("[Regios] Failed to hook into BOSEconomy!");
	}
	
	public static Economy getEconomy(){
		return economy;
	}
	
	public static boolean isEconomySupportEnabled(){
		return economySupport;
	}
	
	public static iConomyManager getiConomyManager(){
		return iconomyManager;
	}
	
	public static BoseEconomyManager getBoseEconomyManager(){
		return boseManager;
	}

}
