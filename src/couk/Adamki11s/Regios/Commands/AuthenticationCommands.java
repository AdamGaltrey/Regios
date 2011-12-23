package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Regions.Region;

public class AuthenticationCommands {
	
	public void sendPassword(Player p, String password, Region r){
		if(r == null){
			p.sendMessage(ChatColor.RED + "[Regios] You must first try to enter a region before authorising yourself!");
			return;
		}
		
		if(r.getAuthentication(password, p)){
			p.sendMessage(ChatColor.GREEN + "[Regios] Authentication successfull! You can now enter/exit region " + ChatColor.BLUE + r.getName());
			return;
		} else {
			p.sendMessage(ChatColor.RED + "[Regios] Invalid password for region " + ChatColor.BLUE + r.getName());
			return;
		}
		
	}

}
