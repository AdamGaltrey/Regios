package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Mutable.MutableProtection;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class ProtectionCommands extends PermissionsCore {
	
	MutableProtection mutable = new MutableProtection();
	
	public void setProtected(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] General Protection enabled for region " + ChatColor.BLUE + region);
		}
		mutable.editProtect(r);
	}
	
	public void setProtectedAll(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] All Protection enabled for region " + ChatColor.BLUE + region);
		}
		mutable.editProtect(r);
		mutable.editProtectBB(r);
		mutable.editProtectBP(r);
	}
	
	public void setProtectedBB(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Block Break Protection enabled for region " + ChatColor.BLUE + region);
		}
		mutable.editProtectBB(r);
	}
	
	public void setProtectedBP(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Block Place Protection enabled for region " + ChatColor.BLUE + region);
		}
		mutable.editProtectBP(r);
	}
	
	public void setUnProtectedBP(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Block Place Protection disabled for region " + ChatColor.BLUE + region);
		}
		mutable.editUnProtectBP(r);
	}
	
	public void setUnProtectedBB(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Block Break Protection disabled for region " + ChatColor.BLUE + region);
		}
		mutable.editUnProtectBB(r);
	}
	
	public void setUnProtected(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] General Protection disabled for region " + ChatColor.BLUE + region);
		}
		mutable.editUnprotect(r);
	}
	
	public void setUnProtectAll(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] All Protection disabled for region " + ChatColor.BLUE + region);
		}
		mutable.editUnprotect(r);
		mutable.editUnProtectBB(r);
		mutable.editUnProtectBP(r);
	}
	
	public void setPreventEntry(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Prevent entry enabled for region " + ChatColor.BLUE + region);
		}
		mutable.editPreventEntry(r);
	}
	
	public void setAllowEntry(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Prevent entry disabled for region " + ChatColor.BLUE + region);
		}
		mutable.editAllowEntry(r);
	}
	
	public void setPreventExit(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Prevent exit enabled for region " + ChatColor.BLUE + region);
		}
		mutable.editPreventExit(r);
	}
	
	public void setAllowExit(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Prevent exit disabled for region " + ChatColor.BLUE + region);
		}
		mutable.editAllowExit(r);
	}

}
