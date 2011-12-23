package couk.Adamki11s.Regios.Economy;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class EconomyPending {

	static final File root = new File("plugins" + File.separator + "Regios" + File.separator + "Other" + File.separator + "Pending");

	public static void sendAppropriatePending(String seller, String buyer, String region_name, int price){
		Player p = Bukkit.getServer().getPlayer(seller);
		if(p == null){
			createPending(seller, buyer, region_name, price);
		} else {
			p.sendMessage(ChatColor.GREEN + "[Regios] Player " + ChatColor.BLUE + buyer + ChatColor.GREEN + " bought your region " + ChatColor.BLUE
					+ region_name + ChatColor.GREEN + " for " + ChatColor.BLUE + price);
		}
	}
	
	private static void createPending(String seller, String buyer, String region_name, int price) {
		File pending = new File(root + File.separator + seller + ".pending");
		if (pending.exists()) {
			pending.delete();
			try {
				pending.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				pending.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Configuration c = new Configuration(pending);
		c.setProperty("Buyer", buyer);
		c.setProperty("Region", region_name);
		c.setProperty("Price", price);
		c.save();
	}

	public static boolean isPending(Player p) {
		File pending = new File(root + File.separator + p.getName() + ".pending");
		return pending.exists();
	}

	public static void loadAndSendPending(Player p) {
		File pending = new File(root + File.separator + p.getName() + ".pending");
		Configuration c = new Configuration(pending);
		c.load();
		p.sendMessage(ChatColor.GREEN + "[Regios] Player " + ChatColor.BLUE + c.getString("Buyer", "NULL") + ChatColor.GREEN + " bought your region " + ChatColor.BLUE
				+ c.getString("Region", "NULL") + ChatColor.GREEN + " for " + ChatColor.BLUE + c.getInt("Price", 0));
		pending.delete();
	}

}
