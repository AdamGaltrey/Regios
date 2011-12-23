package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Data.MODE;
import couk.Adamki11s.Regios.Mutable.MutableModes;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class ModeCommands extends PermissionsCore {
	
	MutableModes mutable = new MutableModes();
	
	public void setProtectionMode(Region r, String region, String input, Player p){
		MODE m = MODE.toMode(input);
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(m == null){
				p.sendMessage(ChatColor.RED + "[Regios] The mode must either be Blacklist or Whitelist");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Protection mode updated for region " + ChatColor.BLUE + region);
		}
		mutable.editProtectionMode(r, m);
	}
	
	public void setPreventEntryMode(Region r, String region, String input, Player p){
		MODE m = MODE.toMode(input);
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(m == null){
				p.sendMessage(ChatColor.RED + "[Regios] The mode must either be Blacklist or Whitelist");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Prevent Entry mode updated for region " + ChatColor.BLUE + region);
		}
		mutable.editPreventEntryMode(r, m);
	}
	
	public void setPreventExitMode(Region r, String region, String input, Player p){
		MODE m = MODE.toMode(input);
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(m == null){
				p.sendMessage(ChatColor.RED + "[Regios] The mode must either be Blacklist or Whitelist");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Prevent Exit mode updated for region " + ChatColor.BLUE + region);
		}
		mutable.editPreventExitMode(r, m);
	}
	
	public void setItemControlMode(Region r, String region, String input, Player p){
		MODE m = MODE.toMode(input);
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(m == null){
				p.sendMessage(ChatColor.RED + "[Regios] The mode must either be Blacklist or Whitelist");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Item Control mode updated for region " + ChatColor.BLUE + region);
		}
		mutable.editItemControlMode(r, m);
	}

}
