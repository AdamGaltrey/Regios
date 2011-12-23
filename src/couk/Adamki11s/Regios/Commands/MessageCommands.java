package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Mutable.MutableMessages;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class MessageCommands extends PermissionsCore {
	
	MutableMessages mutable = new MutableMessages();
	
	public void setWelcome(Region r, String region, String[] message, Player p){
		String builder = "";
		for(int index = 2; index < message.length; index++){
			builder += message[index] + " ";
		}
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Welcome message updated for region " + ChatColor.BLUE + region);
		}
		mutable.editWelcomeMessage(r, builder);
	}
	
	public void setLeave(Region r, String region, String[] message, Player p){
		String builder = "";
		for(int index = 2; index < message.length; index++){
			builder += message[index] + " ";
		}
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Leave message updated for region " + ChatColor.BLUE + region);
		}
		mutable.editLeaveMessage(r, builder);
	}
	
	public void setPreventEntry(Region r, String region, String[] message, Player p){
		String builder = "";
		for(int index = 2; index < message.length; index++){
			builder += message[index] + " ";
		}
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Prevent-entry message updated for region " + ChatColor.BLUE + region);
		}
		mutable.editPreventEntryMessage(r, builder);
	}
	
	public void setPreventExit(Region r, String region, String[] message, Player p){
		String builder = "";
		for(int index = 2; index < message.length; index++){
			builder += message[index] + " ";
		}
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Prevent-exit message updated for region " + ChatColor.BLUE + region);
		}
		mutable.editPreventExitMessage(r, builder);
	}
	
	public void setProtection(Region r, String region, String[] message, Player p){
		String builder = "";
		for(int index = 2; index < message.length; index++){
			builder += message[index] + " ";
		}
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Protection message updated for region " + ChatColor.BLUE + region);
		}
		mutable.editProtectionMessage(r, builder);
	}
	
	public void setShowWelcome(Region r, String region, String input, Player p){
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
			p.sendMessage(ChatColor.GREEN + "[Regios] Show welcome message : " + ChatColor.BLUE + val);
		}
		mutable.editShowWelcomeMessage(r, val);
	}
	
	public void setShowLeave(Region r, String region, String input, Player p){
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
			p.sendMessage(ChatColor.GREEN + "[Regios] Show leave message : " + ChatColor.BLUE + val);
		}
		mutable.editShowLeaveMessage(r, val);
	}
	
	public void setShowPreventEntry(Region r, String region, String input, Player p){
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
			p.sendMessage(ChatColor.GREEN + "[Regios] Show prevent entry message : " + ChatColor.BLUE + val);
		}
		mutable.editShowPreventEntryMessage(r, val);
	}
	
	public void setShowPreventExit(Region r, String region, String input, Player p){
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
			p.sendMessage(ChatColor.GREEN + "[Regios] Show prevent exit message : " + ChatColor.BLUE + val);
		}
		mutable.editShowPreventExitMessage(r, val);
	}
	
	public void setShowProtection(Region r, String region, String input, Player p){
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
			p.sendMessage(ChatColor.GREEN + "[Regios] Show protection message : " + ChatColor.BLUE + val);
		}
		mutable.editShowProtectionMessage(r, val);
	}
	
	public void setShowPvpWarning(Region r, String region, String input, Player p){
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
			p.sendMessage(ChatColor.GREEN + "[Regios] Show pvp message : " + ChatColor.BLUE + val);
		}
		mutable.editShowPvpWarningMessage(r, val);
	}

}
