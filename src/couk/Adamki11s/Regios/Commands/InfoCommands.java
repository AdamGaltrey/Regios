package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Regions.Region;

public class InfoCommands {
	
	public void showInfo(Region r, String region, Player p){
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			p.sendMessage(ChatColor.GREEN + "[Regios] Info for region " + ChatColor.BLUE + region);
			p.sendMessage(ChatColor.BLUE + "Owner : " + ChatColor.GREEN + r.getOwner());
			p.sendMessage(ChatColor.BLUE + "Protected : " + ChatColor.GREEN + r.is_protection());
			p.sendMessage(ChatColor.BLUE + "Prevent-Entry : " + ChatColor.GREEN + r.isPreventEntry());
			p.sendMessage(ChatColor.BLUE + "Prevent-Exit : " + ChatColor.GREEN + r.isPreventExit());
			p.sendMessage(ChatColor.BLUE + "Player Cap : " + ChatColor.GREEN + r.getPlayerCap());
			p.sendMessage(ChatColor.BLUE + "Health : " + ChatColor.GREEN + r.isHealthEnabled());
			p.sendMessage(ChatColor.BLUE + "Health-Regen : " + ChatColor.GREEN + r.getHealthRegen());
			p.sendMessage(ChatColor.BLUE + "PvP : " + ChatColor.GREEN + r.isPvp());
			return;
		}
	}

}
