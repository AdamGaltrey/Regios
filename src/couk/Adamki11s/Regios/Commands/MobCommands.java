package couk.Adamki11s.Regios.Commands;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Mutable.MutableMobs;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class MobCommands extends PermissionsCore {

	MutableMobs mutable = new MutableMobs();
	
	public void setMobSpawn(Region r, String region, String input, Player p){
		boolean val;
		try{
			val = Boolean.parseBoolean(input);
		} catch (Exception bfe){
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be boolean!");
			return;
		}
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(val){
				p.sendMessage(ChatColor.GREEN + "[Regios] Mob spawns enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Mob spawns disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editMobSpawn(r, val);
	}
	
	public void setMonsterSpawn(Region r, String region, String input, Player p){
		boolean val;
		try{
			val = Boolean.parseBoolean(input);
		} catch (Exception bfe){
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be boolean!");
			return;
		}
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(val){
				p.sendMessage(ChatColor.GREEN + "[Regios] Monster spawns enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Monster spawns disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editMonsterSpawn(r, val);
	}
}
