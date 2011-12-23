package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Listeners.RegiosPlayerListener;
import couk.Adamki11s.Regios.Mutable.MutableFun;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class FunCommands extends PermissionsCore {
	
	MutableFun mutable = new MutableFun();
	
	public void setLSPS(Region r, String region, String input, Player p){
		int val;
		try{
			val = Integer.parseInt(input);
		} catch (Exception bfe){
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be an integer!");
			return;
		}
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] LSPS for region " + ChatColor.BLUE + region + ChatColor.GREEN + " set to " + ChatColor.BLUE + val);
			mutable.editLSPS(r, val);
		}
	}
	
	public void setHealthRegen(Region r, String region, String input, Player p){
		int val;
		try{
			val = Integer.parseInt(input);
		} catch (Exception bfe){
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be an integer!");
			return;
		}
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Health Regen for region " + ChatColor.BLUE + region + ChatColor.GREEN + " set to " + ChatColor.BLUE + val);
			mutable.editHealthRegen(r, val);
		}
	}
	
	public void setPvP(Region r, String region, String input, Player p){
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
				p.sendMessage(ChatColor.GREEN + "[Regios] PvP enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] PvP spawns disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editPvPEnabled(r, val);
	}
	
	public void setHealthEnabled(Region r, String region, String input, Player p){
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
				p.sendMessage(ChatColor.GREEN + "[Regios] Health enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Health disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editHealthEnabled(r, val);
	}
	
	public void setVelocityWarp(Region r, String region, String input, Player p){
		int val;
		try{
			val = Integer.parseInt(input);
		} catch (Exception bfe){
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be an integer!");
			return;
		}
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Velocity Warp for region " + ChatColor.BLUE + region + ChatColor.GREEN + " set to " + ChatColor.BLUE + val);
			mutable.editVelocityWarp(r, val);
		}
	}
	
	public void setWarp(Player p){
		Location loc = p.getLocation();
		Region r = null;
		if(RegiosPlayerListener.currentRegion.containsKey(p)){
			r = RegiosPlayerListener.currentRegion.get(p);
		}
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] You are not in a Region!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Warp for region " + ChatColor.BLUE + r.getName() + ChatColor.GREEN + " set to current location");
			mutable.editWarpLocation(r, loc);
		}
	}
	
	public void resetWarp(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Warp Location for region " + ChatColor.BLUE + region + ChatColor.GREEN + " reset.");
			mutable.editRemoveWarpLocation(r);
		}
	}

}
