package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Mutable.MutableInventory;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class InventoryCommands extends PermissionsCore{
	
	MutableInventory mutable = new MutableInventory();
	
	public void setPermWipeOnExit(Region r, String region, String input, Player p){
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
				p.sendMessage(ChatColor.GREEN + "[Regios] Perm wipe on exit enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Perm wipe on exit disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editPermWipeOnExit(r, val);
	}
	
	public void setPermWipeOnEntry(Region r, String region, String input, Player p){
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
				p.sendMessage(ChatColor.GREEN + "[Regios] Perm wipe on entry enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Perm wipe on entry disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editPermWipeOnEnter(r, val);
	}
	
	public void setWipeAndCacheOnExit(Region r, String region, String input, Player p){
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
				p.sendMessage(ChatColor.GREEN + "[Regios] Wipe and cache on exit enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Wipe and cache on exit disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editWipeAndCacheOnExit(r, val);
	}
	
	public void setWipeAndCacheOnEntry(Region r, String region, String input, Player p){
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
				p.sendMessage(ChatColor.GREEN + "[Regios] Wipe and cache on entry enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Wipe and cache on entry disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editWipeAndCacheOnEnter(r, val);
	}

}
